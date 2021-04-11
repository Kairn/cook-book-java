package io.esoma.cbj.crypto.basic;

import org.apache.commons.lang3.StringUtils;

import io.esoma.cbj.crypto.core.HexUtil;

/**
 * Class for implementing the repeating XOR encryption algorithm.
 * 
 * @author Eddy Soma
 *
 */
public class RepeatingXor {

	/**
	 * Encrypts a message with the given key using repeating XOR cipher scheme. Each
	 * byte of the message will be XORed with a byte from the key by cycling
	 * iteration. The resulting cipher can optionally be hex encoded. This can be
	 * used to decipher a non hex encoded cipher string as well.
	 * 
	 * @param message the message to encrypt/decrypt
	 * @param key     the encryption key
	 * @param hex     if the result should be hex encoded
	 * @return the encrypted string
	 */
	public static String create(String message, String key, boolean hex) {
		if (StringUtils.isAnyBlank(message, key)) {
			throw new IllegalArgumentException("Invalid message or key");
		}

		byte[] rawBytes = new byte[message.length()];
		for (int i = 0; i < message.length(); ++i) {
			rawBytes[i] = (byte) message.charAt(i);
		}

		return operate(rawBytes, key, hex);
	}

	public static String create(byte[] bytes, String key, boolean hex) {
		return operate(bytes, key, hex);
	}

	private static String operate(byte[] bytes, String key, boolean hex) {
		int[] cipherChars = new int[bytes.length];
		for (int i = 0; i < bytes.length; ++i) {
			cipherChars[i] = bytes[i] ^ key.charAt(i % key.length());
		}

		StringBuilder builder = new StringBuilder();
		for (int c : cipherChars) {
			if (hex) {
				builder.append(HexUtil.byteToHexString(c));
			} else {
				builder.append((char) c);
			}
		}

		return builder.toString();
	}
}
