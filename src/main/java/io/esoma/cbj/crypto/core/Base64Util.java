package io.esoma.cbj.crypto.core;

import org.apache.commons.lang3.StringUtils;

/**
 * Class for providing Base64 related utility functions.
 *
 * @author Eddy Soma
 */
public class Base64Util {

    private static final String BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final char PADDING = '=';
    private static final int BLOCK_SIZE = 6;

    private Base64Util() {}

    public static char getChar(int index) {
        if (index > 63 || index < 0) {
            throw new IllegalArgumentException("Invalid index");
        }

        return BASE64_CHARS.charAt(index);
    }

    public static int getIndex(char character) {
        int index = BASE64_CHARS.indexOf(character);
        if (index == -1) {
            throw new IllegalArgumentException("Invalid character");
        }

        return index;
    }

    public static String encodeFromBitStream(String bits) {
        StringBuilder builder64 = new StringBuilder();

        int cursor = 0;
        while (cursor < bits.length()) {
            if (bits.length() - cursor >= BLOCK_SIZE) {
                builder64.append(bitsToBase64Char(bits.substring(cursor, cursor + BLOCK_SIZE)));
                cursor += BLOCK_SIZE;
            } else if (bits.length() - cursor == 2) {
                builder64.append(bitsToBase64Char(bits.substring(cursor) + "0000"));
                builder64.append(PADDING);
                builder64.append(PADDING);
                break;
            } else if (bits.length() - cursor == 4) {
                builder64.append(bitsToBase64Char(bits.substring(cursor) + "00"));
                builder64.append(PADDING);
                break;
            } else {
                throw new IllegalStateException("Invalid bit stream length");
            }
        }

        return builder64.toString();
    }

    public static String encodeFromBytes(byte[] bytes) {
        return encodeFromBitStream(BinUtil.bytesToBitStream(bytes));
    }

    public static byte[] decodeToByteArray(String encoded) {
        if (StringUtils.isBlank(encoded)) {
            return new byte[0];
        }

        int numPads = 0;
        StringBuilder builder = new StringBuilder();
        for (char c : encoded.toCharArray()) {
            if (c == PADDING) {
                ++numPads;
            } else {
                builder.append(BinUtil.intToBitStream(getIndex(c), BLOCK_SIZE));
            }
        }

        String bitStream = numPads == 2
                ? builder.substring(0, builder.length() - 4)
                : numPads == 1 ? builder.substring(0, builder.length() - 2) : builder.toString();

        return BinUtil.bitStreamToBytes(bitStream);
    }

    private static char bitsToBase64Char(String bits) {
        if (bits == null || bits.length() != BLOCK_SIZE) {
            throw new IllegalArgumentException("Invalid bits");
        }

        return getChar(BinUtil.bitStreamToInt(bits));
    }
}
