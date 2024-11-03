package io.esoma.cbj.pe;

import io.esoma.cbj.util.ResourceLoader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.tinylog.Logger;

/**
 * A simple application that reads Prometheus metrics data from a server, parses it, and aggregates
 * into a summary. The client issues mocked API calls to the server that returns metrics data until
 * it runs out. The metrics data are stored in a plain text file.
 */
public class PrometheusAggregator {

    static final int PORT = 5588;
    static final int BATCH_SIZE = 30;
    static final String EOF = "<<EOF>>";
    static final String[] DATASET = new String[] {"prometheus/cpu.txt", "prometheus/io.txt"};

    public static void main(String[] args) {
        new Thread(PrometheusAggregator::runServer).start();

        try (Socket socket = new Socket((String) null, PORT);
                BufferedReader receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter sender = new PrintWriter(socket.getOutputStream())) {
            String keyData = receiver.readLine();
            String[] keys = keyData.split(",");

            for (String key : keys) {
                Logger.info("Starting collecting data for module: {}.", key);
                AggregatorModule module = new AggregatorModule(key);

                MODULE:
                for (; ; ) {
                    // Send a request.
                    sender.println(key);
                    sender.flush();

                    int count = 0;
                    while (count < BATCH_SIZE) {
                        String data = receiver.readLine();
                        count++;
                        if (EOF.equals(data)) {
                            Logger.info("End of module {}.", key);
                            break MODULE;
                        } else {
                            module.aggregate(data);
                        }
                    }
                }

                module.printResults();
            }

            Logger.info("Finished reading all Prometheus data. Closing connection.");
            sender.println(EOF);
            sender.flush();
        } catch (Exception e) {
            Logger.error("Exception encounter in client program. Abort.");
            Logger.error(e);
        }
    }

    /**
     * Runs the server by accepting a socket connection from a client, loading the dataset files and
     * becoming ready to serve data based on the key. The key is defined by the name of the file
     * without extension. Keys are vended to client upon connection in a CSV string and the client
     * will be responsible for parsing it. Once a file is exhausted, an EOF is sent to signal the
     * client to stop.
     */
    static void runServer() {
        try (ServerSocket server = new ServerSocket(PORT);
                Socket clientSocket = server.accept();
                BufferedReader receiver = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter sender = new PrintWriter(clientSocket.getOutputStream());
                PrometheusIterator pIter = new PrometheusIterator(DATASET)) {
            sender.println(pIter.getKeyData());
            sender.flush();

            String line;
            while (!EOF.equals(line = receiver.readLine())) {
                String[] dataLines = pIter.getData(line);
                if (dataLines.length == 0) {
                    sender.println(EOF);
                } else {
                    for (String data : dataLines) {
                        if (data == null) {
                            sender.println(EOF);
                            break;
                        }
                        sender.println(data);
                    }
                }
                sender.flush();
            }

            Logger.info("Client closed connection. Exiting.");
        } catch (Exception e) {
            Logger.error("Exception encountered in server. Abort.");
            Logger.error(e);
        }
    }
}

/** An iterator of Prometheus data records from multiple files. */
class PrometheusIterator implements AutoCloseable {

    static final Pattern FILE_KEY_GRAB = Pattern.compile(".*?(\\w+)(\\.[a-z]+)?$");

    private final String keyData;
    private final InputStream[] inputStreams;
    private final Map<String, Iterator<String>> dataIterators;

    PrometheusIterator(String[] files) {
        String[] keys = new String[files.length];
        this.inputStreams = new InputStream[files.length];
        this.dataIterators = new HashMap<>(files.length);

        int index = 0;
        for (String resourcePath : files) {
            Matcher keyMatcher = FILE_KEY_GRAB.matcher(resourcePath);
            if (!keyMatcher.matches()) {
                Logger.warn("Ignoring input file {}.", resourcePath);
                continue;
            }
            String key = keyMatcher.group(1);
            InputStream stream = ResourceLoader.getResourceAsReader(resourcePath);
            keys[index] = key;
            inputStreams[index] = stream;
            index++;
            this.dataIterators.put(
                    key,
                    new BufferedReader(new InputStreamReader(stream)).lines().iterator());
        }
        this.keyData = String.join(",", keys);
    }

    String getKeyData() {
        return keyData;
    }

    String[] getData(String key) {
        if (!dataIterators.containsKey(key)) {
            return new String[0];
        }

        Iterator<String> iter = dataIterators.get(key);
        if (!iter.hasNext()) {
            return new String[0];
        }

        String[] buffer = new String[PrometheusAggregator.BATCH_SIZE];
        int count = 0;
        while (iter.hasNext() && count < PrometheusAggregator.BATCH_SIZE) {
            buffer[count++] = iter.next();
        }

        return buffer;
    }

    @Override
    public void close() throws Exception {
        if (inputStreams != null) {
            for (InputStream stream : inputStreams) {
                stream.close();
            }
        }
    }
}

class AggregatorModule {

    static final Pattern DATA_PATTERN = Pattern.compile("^\\w+\\{app=\"(.+?)\",proc=\"(.+?)\",value=(\\d+)}$");

    private final String moduleName;
    private final Set<String> appNames;
    private final Map<String, FloatingMetrics> procMetrics;

    private int count;

    AggregatorModule(String moduleName) {
        this.moduleName = moduleName;
        this.appNames = new HashSet<>();
        this.procMetrics = new HashMap<>();
    }

    void aggregate(String line) {
        count++;
        Matcher matcher = DATA_PATTERN.matcher(line);
        if (!matcher.matches()) {
            Logger.warn("Ignoring bad data line: {}", line);
            return;
        }
        appNames.add(matcher.group(1));
        int value = Integer.parseInt(matcher.group(3));
        procMetrics
                .computeIfAbsent(matcher.group(2), k -> new FloatingMetrics())
                .addMetric(value);
    }

    void printResults() {
        Logger.info("Printing result for module: {} with {} data records aggregated.", moduleName, count);
        Logger.info("Total unique apps: {}.", appNames.size());
        for (Map.Entry<String, FloatingMetrics> entry : procMetrics.entrySet()) {
            Logger.info("Process: {} - {}.", entry.getKey(), entry.getValue());
        }
    }
}

class FloatingMetrics {

    private long max;
    private long min;
    private long tot;
    private long cnt;

    void addMetric(int val) {
        max = Math.max(max, val);
        min = Math.min(min, val);
        tot += val;
        cnt++;
    }

    @Override
    public String toString() {
        return String.format("avg=%d; min=%d; max=%d", tot / cnt, min, max);
    }
}
