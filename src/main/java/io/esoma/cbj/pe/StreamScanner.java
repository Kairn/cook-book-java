package io.esoma.cbj.pe;

import org.tinylog.Logger;

/**
 * This stream scanner reads from a continuous data stream and looks for chunks that match a
 * particular pattern and record their starting and ending indices. Pattern can contain alphabet
 * characters or "*" (matches all characters). Matching should be done in a case insensitive way.
 * New-line characters are ignored when encountered.
 */
public class StreamScanner {

    public static void main(String[] args) {
        Logger.info("{} works!", StreamScanner.class.getName());
    }
}
