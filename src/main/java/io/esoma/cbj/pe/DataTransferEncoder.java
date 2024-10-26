package io.esoma.cbj.pe;

import io.esoma.cbj.util.ResourceLoader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import org.tinylog.Logger;

/**
 * This is a static data encoder whose job is to encode a stream of arbitrary data segments into a
 * single data stream that can be transferred over the network. The decoding logic can parse the
 * encoded stream and restore it into the original data segments.
 *
 * <p>Encode format - For each segment, use the first 2 bytes to denote the length of the segment,
 * followed exactly this many characters for the actual data. Two null bytes are sent when no more
 * segments are to be sent.
 */
public class DataTransferEncoder {

    static final int MTU = 70;
    static final int PORT = 4332;

    /**
     * The test driver function. It sets up a client (socket) who will read a file of line-separated
     * data interpreted as segments, encodes each line, and streams data in chunks of pre-defined
     * MTU to a server who will decode and then prints the original segments line by line.
     */
    public static void main(String[] args) {
        new Thread(DataTransferEncoder::runServer).start();

        try (Socket receiver = new Socket((String) null, PORT);
                BufferedOutputStream sender = new BufferedOutputStream(receiver.getOutputStream());
                BufferedReader dataChunkStream = new BufferedReader(
                        new InputStreamReader(ResourceLoader.getResourceAsReader("example/dataChunks.txt")))) {
            Iterator<String> lines = dataChunkStream.lines().iterator();
            int curBufSize = 0;
            while (lines.hasNext()) {
                byte[] bytes = lines.next().getBytes(StandardCharsets.UTF_8);
                int size = bytes.length;
                if (size == 0) {
                    Logger.warn("Empty line skipped in client encoder.");
                    continue;
                }
                sender.write(size & 0x00FF); // Send low byte.
                ++curBufSize;
                if (curBufSize >= MTU) {
                    sender.flush();
                    curBufSize = 0;
                }
                sender.write((size & 0xFF00) >> 2); // Send high byte.
                ++curBufSize;
                if (curBufSize >= MTU) {
                    sender.flush();
                    curBufSize = 0;
                }
                for (byte b : bytes) {
                    sender.write(b);
                    ++curBufSize;
                    if (curBufSize >= MTU) {
                        sender.flush();
                        curBufSize = 0;
                    }
                }
            }
            sender.write(0);
            sender.write(0);
            sender.flush();
        } catch (Exception e) {
            Logger.error("Encountered unrecoverable error in the data encoder client.");
            Logger.error(e);
            Runtime.getRuntime().exit(1);
        }
    }

    /**
     * Starts a server that accepts a client connection and decodes the data sent from the client.
     * Decoded string chunks will be printed to STDOUT line by line.
     */
    static void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT);
                Socket clientSocket = serverSocket.accept();
                BufferedInputStream clientInput = new BufferedInputStream(clientSocket.getInputStream())) {
            Logger.info("Received connection from client. Ready to receive data transmission.");
            for (; ; ) {
                int lowByte = clientInput.read();
                int size = lowByte | (clientInput.read() << 2);
                if (size == 0) {
                    break;
                }
                byte[] buf = new byte[size];
                for (int i = 0; i < size; ++i) {
                    buf[i] = (byte) clientInput.read();
                }
                Logger.info("Decoded string chunk: {}.", new String(buf, StandardCharsets.UTF_8));
            }
            Logger.info("Client completed transmission. Connection closed.");
        } catch (Exception e) {
            Logger.error("Encountered unrecoverable error on the server.");
            Logger.error(e);
            Runtime.getRuntime().exit(1);
        }
    }
}
