package io.esoma.cbj.crypto.basic;

import org.apache.commons.lang3.StringUtils;

import io.esoma.cbj.crypto.core.BinUtil;
import io.esoma.cbj.crypto.core.HexUtil;

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
			builder.append(HexUtil.hexCharToBits(c));
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

	private static char bitsToBase64Char(String bits) {
		if (bits == null || bits.length() != BLOCK_SIZE) {
			throw new IllegalArgumentException("Invalid bits");
		}

		return BASE64_CHARS.charAt(BinUtil.bitStreamToInt(bits));
	}
}
