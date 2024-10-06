package io.esoma.cbj.pe;

import io.esoma.cbj.util.ResourceLoader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import org.tinylog.Logger;

/**
 * A program that listens to many server hosts for request metrics, aggregates them, and reports the
 * time series data to a central cluster. Individual hosts' data is retrieved via file abstraction
 * (streamed). The remote central host is communicated to via an open socket for simulation
 * purposes. Each server's metrics is formatted as follows:
 *
 * <p>hostname,api,status,requestBytes,responseBytes<br>
 * host1,/login,200,50,600<br>
 * host2,/login,200,50,600<br>
 * host3,/login,200,50,600<br>
 * host1,/getAccountName,200,50,600<br>
 * ...
 *
 * <p>Aggregated data is reported every a few seconds.
 */
public class HostLogReporter {

    static final String CLUSTER_1_FILE = "hosts/cluster1.csv";
    static final String CLUSTER_2_FILE = "hosts/cluster2.csv";

    static final int PORT = 4321;
    static final String EOF = "<<EOF>>";

    public static void main(String[] args) {
        // Start the server on a separate thread to simulate a remote receiver
        new Thread(HostLogReporter::startCentralServer).start();

        // Run the client reporter
        try (Socket receiver = new Socket((String) null, PORT);
                PrintWriter sender = new PrintWriter(receiver.getOutputStream(), true);
                BufferedReader metricsFile1 =
                        new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsReader(CLUSTER_1_FILE)));
                BufferedReader metricsFile2 =
                        new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsReader(CLUSTER_2_FILE)))) {
            Iterator<String> metricsIter1 = metricsFile1.lines().iterator();
            Iterator<String> metricsIter2 = metricsFile2.lines().iterator();
            // Skip CSV header
            metricsIter1.next();
            metricsIter2.next();

            Random rand = new Random();
            int timePeriod = 1;

            int i;
            int pollCounter1;
            int pollCounter2;

            do {
                AggregatedStats aggregatedStats = new AggregatedStats(timePeriod++);
                i = 0;
                pollCounter1 = rand.nextInt(20, 51);
                while (i < pollCounter1 && metricsIter1.hasNext()) {
                    aggregatedStats.ingestMetrics(Metrics.fromMetricsLine(metricsIter1.next()));
                    i++;
                }
                i = 0;
                pollCounter2 = rand.nextInt(15, 56);
                while (i < pollCounter2 && metricsIter2.hasNext()) {
                    aggregatedStats.ingestMetrics(Metrics.fromMetricsLine(metricsIter2.next()));
                    i++;
                }

                // Send the aggregated window to the server
                sender.println(aggregatedStats);
            } while (metricsIter1.hasNext() || metricsIter2.hasNext());

            sender.println(EOF);
        } catch (Exception e) {
            Logger.error("Encountered unrecoverable error in the host reporter.");
            Logger.error(e);
            Runtime.getRuntime().exit(1);
        }
    }

    /**
     * Starts a local server that listens to the {@link HostLogReporter#PORT} for connections. It
     * echos out the data received from the client reporter. It can accept only one connection for
     * the purpose of this simulation.
     */
    static void startCentralServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT);
                Socket clientSocket = serverSocket.accept();
                BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            Logger.info("Received connection from client reporter.");
            clientInput.lines().forEach(line -> {
                if (line.contains(EOF)) {
                    // Reaching the end of the input stream
                    Logger.info("EOF reached from client communication, shutting down...");
                    return;
                }
                Logger.info("Received client report: {}.", line);
            });
        } catch (Exception e) {
            Logger.error("Encountered unrecoverable error on the central server.");
            Logger.error(e);
            Runtime.getRuntime().exit(1);
        }
    }
}

record Metrics(String hostname, String api, int status, int requestBytes, int responseBytes) {

    static Metrics fromMetricsLine(String line) {
        String[] data = line.split(",");
        return new Metrics(
                data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]));
    }
}

class AggregatedStats {

    private final Set<String> hostnames;
    private final int timePeriod;

    private int dataPoints;
    private int successes;
    private int totalRequestBytes;
    private int totalResponseBytes;

    AggregatedStats(int timePeriod) {
        this.hostnames = new HashSet<>();
        this.timePeriod = timePeriod;
    }

    void ingestMetrics(Metrics metrics) {
        hostnames.add(metrics.hostname());
        dataPoints++;
        successes += metrics.status() == 200 ? 1 : 0;
        totalRequestBytes += metrics.requestBytes();
        totalResponseBytes += metrics.responseBytes();
    }

    @Override
    public String toString() {
        double availability = dataPoints == 0 ? 0.0 : successes / (double) dataPoints;
        return String.format(
                "Time period: %d; Data points: %d; Unique hosts: %d; Availability: %.2f; Total request bytes: %d; Total response bytes: %d",
                timePeriod, dataPoints, hostnames.size(), availability, totalRequestBytes, totalResponseBytes);
    }
}
