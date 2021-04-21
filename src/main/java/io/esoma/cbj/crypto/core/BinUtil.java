package io.esoma.cbj.crypto.core;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility class that contains functions related to basic binary operations.
 * 
 * @author Eddy Soma
 *
 */
public class BinUtil {

	/**
	 * Converts a binary string consists of 0s and 1s into an integer. Negative
	 * numbers are not accounted for. If the bit stream represents a number greater
	 * than {@link Integer#MAX_VALUE}, the result is undefined.
	 * 
	 * @param bits the string containing bits
	 * @return the resulting integer
	 */
	public static int bitStreamToInt(String bits) {
		if (StringUtils.isBlank(bits)) {
			throw new IllegalArgumentException("Invalid bits");
		}

		int result = 0;
		for (int i = 0; i < bits.length(); ++i) {
			char c = bits.charAt(bits.length() - 1 - i);
			if (c == '1') {
				result += 1 << i;
			} else if (c != '0') {
				throw new IllegalArgumentException("Invalid bit found");
			}
		}

		return result;
	}

	/**
	 * Encodes an integer value into a stream of bits. Padding of "0"s will be
	 * applied to ensure the resulting string has a length equal to the specified
	 * number of bits. Negative values are not supported.
	 * 
	 * @param value   the integer
	 * @param numBits the number of bits on the result
	 * @return the encoded bit stream
	 */
	public static String intToBitStream(int value, int numBits) {
		if (value >= (1 << numBits) || value < 0) {
			throw new IllegalArgumentException("Value is too large or negative");
		}

		StringBuilder builder = new StringBuilder();
		for (int i = numBits - 1; i >= 0; --i) {
			if (value >= (1 << i)) {
				builder.append('1');
				value -= 1 << i;
			} else {
				builder.append('0');
			}
		}

		return builder.toString();
	}

	/**
	 * Returns the number of bits that are "1" for a given integer.
	 * 
	 * @param value the integer
	 * @param limit the highest possible bit (exclusive)
	 * @return the number of bits set
	 */
	public static int getBitsSet(int value, int limit) {
		if (value >= (1 << limit) || value < 0) {
			throw new IllegalArgumentException("Invalid value");
		}

		int count = 0;
		for (int i = 0; i < limit; ++i) {
			int test = 1 << i;
			if ((value & test) > 0) {
				++count;
			}
		}

		return count;
	}

	public static byte[] reduceToBytes(int[] intArray) {
		if (intArray == null) {
			throw new IllegalArgumentException("Invalid array");
		}

		byte[] byteArray = new byte[intArray.length];
		for (int i = 0; i < intArray.length; ++i) {
			byteArray[i] = (byte) intArray[i];
		}

		return byteArray;
	}

	public static byte[] bitStreamToBytes(String bits) {
		if (StringUtils.isBlank(bits) || bits.length() % 8 != 0) {
			throw new IllegalArgumentException("Invalid bits");
		}

		byte[] decoded = new byte[bits.length() / 8];
		for (int i = 0; i < bits.length(); i += 8) {
			decoded[i / 8] = (byte) bitStreamToInt(bits.substring(i, i + 8));
		}

		return decoded;
	}

	public static byte next(byte b) {
		if (b == Byte.MAX_VALUE) {
			return Byte.MIN_VALUE;
		} else {
			return ++b;
		}
	}

}
