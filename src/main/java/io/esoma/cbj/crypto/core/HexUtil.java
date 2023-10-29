package io.esoma.cbj.crypto.core;

import org.apache.commons.lang3.StringUtils;

/**
 * Class that contains utility methods for hexadecimal related encoding and operations.
 *
 * @author Eddy Soma
 */
public class HexUtil {

    private HexUtil() {}

    public static String hexCharToBits(char c) {
        switch (c) {
            case '0':
                return "0000";
            case '1':
                return "0001";
            case '2':
                return "0010";
            case '3':
                return "0011";
            case '4':
                return "0100";
            case '5':
                return "0101";
            case '6':
                return "0110";
            case '7':
                return "0111";
            case '8':
                return "1000";
            case '9':
                return "1001";
            case 'a':
                return "1010";
            case 'b':
                return "1011";
            case 'c':
                return "1100";
            case 'd':
                return "1101";
            case 'e':
                return "1110";
            case 'f':
                return "1111";
            default:
                throw new IllegalArgumentException("Invalid hex character");
        }
    }

    public static char bitsToHexChar(String bits) {
        switch (bits) {
            case "0000":
                return '0';
            case "0001":
                return '1';
            case "0010":
                return '2';
            case "0011":
                return '3';
            case "0100":
                return '4';
            case "0101":
                return '5';
            case "0110":
                return '6';
            case "0111":
                return '7';
            case "1000":
                return '8';
            case "1001":
                return '9';
            case "1010":
                return 'a';
            case "1011":
                return 'b';
            case "1100":
                return 'c';
            case "1101":
                return 'd';
            case "1110":
                return 'e';
            case "1111":
                return 'f';
            default:
                throw new IllegalArgumentException("Invalid bits");
        }
    }

    /**
     * Converts a byte (as a code point integer) into 2 hex characters.
     *
     * @param code the code point
     * @return the resulting hex encoded string
     */
    public static String byteToHexString(int code) {
        String bits = BinUtil.intToBitStream(code, 8);

        char[] hexParts = new char[2];
        hexParts[0] = bitsToHexChar(bits.substring(0, 4));
        hexParts[1] = bitsToHexChar(bits.substring(4));

        return new String(hexParts);
    }

    /**
     * Decodes a hex encoded string into raw bytes.
     *
     * @param hexString the input string
     * @return the resulting bytes
     */
    public static byte[] stringToRawBytes(String hexString) {
        if (StringUtils.isBlank(hexString)) {
            return new byte[0];
        } else if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException("Invalid hex string");
        }

        StringBuilder builder = new StringBuilder();
        for (char c : hexString.toCharArray()) {
            builder.append(hexCharToBits(c));
        }

        return BinUtil.bitStreamToBytes(builder.toString());
    }
}
