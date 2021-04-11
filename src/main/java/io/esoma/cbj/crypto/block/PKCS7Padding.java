package io.esoma.cbj.crypto.block;

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
}
