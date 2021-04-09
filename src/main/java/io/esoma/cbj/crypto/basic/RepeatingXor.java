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
	 * iteration. The resulting cipher will be hex encoded.
	 * 
	 * @param message
	 * @param key
	 * @return
	 */
	public static String encrypt(String message, String key) {
		if (StringUtils.isAnyBlank(message, key)) {
			throw new IllegalArgumentException("Invalid message or key");
		}

		int[] cipherChars = new int[message.length()];
		for (int i = 0; i < message.length(); ++i) {
			cipherChars[i] = message.charAt(i) ^ key.charAt(i % key.length());
		}

		StringBuilder builder = new StringBuilder();
		for (int c : cipherChars) {
			builder.append(HexUtil.byteToHexString(c));
		}

		return builder.toString();
	}
}
