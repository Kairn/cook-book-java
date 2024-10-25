package io.esoma.cbj.pe;

import org.tinylog.Logger;

/**
 * This is a static data encoder whose job is to encode a stream of arbitrary data segments into a
 * single data stream that can be transferred over the network. The decoding logic can parse the
 * encoded stream and restore it into the original data segments.
 */
public class DataTransferEncoder {

    /**
     * The test driver function. It sets up a client (socket) who will read a file of line-separated
     * data interpreted as segments, encodes each line, and streams data in chunks of pre-defined
     * MTU to a server who will decode and then prints the original segments line by line.
     */
    public static void main(String[] args) {
        Logger.info("{} works!", DataTransferEncoder.class.getName());
    }
}
