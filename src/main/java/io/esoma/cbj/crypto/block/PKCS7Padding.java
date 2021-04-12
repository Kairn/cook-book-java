package io.esoma.cbj.crypto.block;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

/**
 * Class for implementing the PKCS#7 padding scheme commonly used in
 * cryptography to format irregularly sized blocks of text. Padding is in whole
 * bytes, and the number of padding cannot exceed 255. Padding is produced by
 * appending N bytes of value N to the end of the block, where N is the number
 * of missing bytes.
 * 
 * @author Eddy Soma
 *
 */
public class PKCS7Padding {

	private static final int MAX_PADDING = 255;

	public static String perform(String input, int blockSize) {
		if (StringUtils.isBlank(input) || blockSize < 1 || (blockSize - input.length()) > MAX_PADDING) {
			throw new IllegalArgumentException("Invalid input");
		}

		byte[] padded = perform(input.getBytes(), blockSize);
		return new String(padded);
	}

	public static byte[] perform(byte[] bytes, int blockSize) {
		if (bytes == null || blockSize < 1 || (blockSize - bytes.length) > MAX_PADDING) {
			throw new IllegalArgumentException("Invalid input");
		}

		if (bytes.length >= blockSize) {
			return bytes;
		}

		int padVal = blockSize - bytes.length;
		byte[] padded = new byte[bytes.length + padVal];
		for (int i = 0; i < bytes.length; ++i) {
			padded[i] = bytes[i];
		}

		for (int i = bytes.length; i < padded.length; ++i) {
			padded[i] = (byte) padVal;
		}

		return padded;
	}

	/**
	 * Removes the padding values from a block of bytes based on PKCS#7 assuming the
	 * block contains padding values. If the block does not conform to the padding
	 * rules, it will be returned as is.
	 * 
	 * @param bytes the block containing padding
	 * @return the byte array without the padding
	 */
	public static byte[] unpad(byte[] bytes) {
		if (bytes == null) {
			throw new IllegalArgumentException("Input is null");
		} else if (bytes.length == 0) {
			return bytes;
		}

		int size = bytes.length;
		byte last = bytes[size - 1];
		if (last > size) {
			return bytes;
		}

		for (int i = 1; i <= last; ++i) {
			if (bytes[size - i] != last) {
				// Return the original array if the padding is incorrect.
				return bytes;
			}
		}

		return Arrays.copyOfRange(bytes, 0, size - last);
	}
}
