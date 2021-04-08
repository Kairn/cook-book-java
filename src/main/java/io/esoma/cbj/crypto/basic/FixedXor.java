package io.esoma.cbj.crypto.basic;

import org.apache.commons.lang3.StringUtils;

import io.esoma.cbj.crypto.core.HexUtil;

/**
 * Class for implementing the function that computes the XOR result of equal
 * length hex encoded strings.
 * 
 * @author Eddy Soma
 *
 */
public class FixedXor {

	public static String combine(String first, String second) {
		if (StringUtils.isAnyBlank(first, second) || first.length() != second.length()) {
			throw new IllegalArgumentException("Invalid string inputs");
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < first.length(); ++i) {
			String bits1 = HexUtil.hexCharToBits(first.charAt(i));
			String bits2 = HexUtil.hexCharToBits(second.charAt(i));
			builder.append(HexUtil.bitsToHexChar(Xor(bits1, bits2)));
		}

		return builder.toString();
	}

	private static String Xor(String first, String second) {
		if (StringUtils.isAnyBlank(first, second) || first.length() != second.length()) {
			throw new IllegalArgumentException("Invalid string inputs");
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < first.length(); ++i) {
			char bit1 = first.charAt(i);
			char bit2 = second.charAt(i);

			if (bit1 == bit2) {
				builder.append('0');
			} else {
				builder.append('1');
			}
		}

		return builder.toString();
	}
}
