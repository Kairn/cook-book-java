package io.esoma.cbj.crypto.basic;

import org.apache.commons.lang3.StringUtils;

/**
 * Class for implementing the function that converts a hex encoded string into a
 * base64 encoded string.
 * 
 * @author Eddy Soma
 *
 */
public class HexToBase64 {

	private static final String BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	private static final char PADDING = '=';
	private static final int BLOCK_SIZE = 6;

	public static String perform(String hexString) {
		if (StringUtils.isBlank(hexString)) {
			return "";
		} else {
			hexString = hexString.toLowerCase();
		}

		StringBuilder builder = new StringBuilder();
		for (char c : hexString.toCharArray()) {
			builder.append(hexCharToBits(c));
		}
		String bitStream = builder.toString();

		StringBuilder builder64 = new StringBuilder();
		int cursor = 0;
		while (cursor < bitStream.length()) {
			if (bitStream.length() - cursor >= BLOCK_SIZE) {
				builder64.append(bitsToBase64Char(bitStream.substring(cursor, cursor + BLOCK_SIZE)));
				cursor += BLOCK_SIZE;
			} else if (bitStream.length() - cursor == 2) {
				builder64.append(bitsToBase64Char(bitStream.substring(cursor) + "0000"));
				builder64.append(PADDING);
				builder64.append(PADDING);
				break;
			} else if (bitStream.length() - cursor == 4) {
				builder64.append(bitsToBase64Char(bitStream.substring(cursor) + "00"));
				builder64.append(PADDING);
				break;
			} else {
				throw new IllegalStateException("Invalid bit stream length");
			}
		}

		return builder64.toString();
	}

	private static String hexCharToBits(char c) {
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

	private static char bitsToBase64Char(String bits) {
		if (bits == null || bits.length() != BLOCK_SIZE) {
			throw new IllegalArgumentException("Invalid bits");
		}

		int index = 0;
		for (int i = 0; i < BLOCK_SIZE; ++i) {
			if (bits.charAt(i) == '1') {
				index += 1 << (BLOCK_SIZE - 1 - i);
			}
		}

		return BASE64_CHARS.charAt(index);
	}
}
