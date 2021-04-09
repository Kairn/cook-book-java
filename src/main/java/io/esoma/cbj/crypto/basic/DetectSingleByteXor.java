package io.esoma.cbj.crypto.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

/**
 * Class for implementing the function to detect messages that have been
 * encrypted by the single-byte XOR cipher algorithm. It leverages the
 * {@link SingleByteXorCipher#decryptMessage(String)} method as the base logic
 * for attempting to decrypt the cipher assuming it is encrypted in the same
 * fashion.
 * 
 * @author Eddy Soma
 *
 */
public class DetectSingleByteXor {

	// Decryption is considered successful if at least <THRESHOLD>% of the
	// characters of the resulting string are valid English characters.
	private static final int THRESHOLD = 95;

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("DetectSingleByteXorTestCases.txt")));
		reader.lines().forEach(encrypted -> {
			String decrypted = SingleByteXorCipher.decryptMessage(encrypted);
			if (isGoodMessage(decrypted)) {
				System.out.format("Detected cipher <%s> which yields: %s", encrypted, decrypted);
			}
		});
	}

	private static boolean isGoodMessage(String message) {
		if (StringUtils.isBlank(message)) {
			return false;
		}

		int passScore = message.length() * THRESHOLD;
		int score = 0;
		for (char c : message.toCharArray()) {
			if (Character.isLowerCase(c) || Character.isUpperCase(c) || Character.isWhitespace(c)) {
				score += 100;
			}
		}

		return score >= passScore;
	}

}
