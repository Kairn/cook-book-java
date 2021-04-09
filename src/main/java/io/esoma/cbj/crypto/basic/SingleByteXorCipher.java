package io.esoma.cbj.crypto.basic;

import org.apache.commons.lang3.StringUtils;

import io.esoma.cbj.crypto.core.BinUtil;
import io.esoma.cbj.crypto.core.HexUtil;

/**
 * Class for studying the algorithm to decrypt a hex encoded string that has
 * been XORed by a single-byte character. It will be a brute force algorithm
 * that tries all single-byte character keys and gives the best guess.
 * 
 * @author Eddy Soma
 *
 */
public class SingleByteXorCipher {

	private static final boolean SILENT = true;
	private static final char MAX_CODE = 255;

	public static void main(String[] args) {
		decryptMessage("1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736");
	}

	public static String decryptMessage(String encrypted) {
		if (StringUtils.isBlank(encrypted) || encrypted.length() % 2 != 0) {
			throw new IllegalArgumentException("Invalid encrypted string");
		}

		final int[] rawCodes = new int[encrypted.length() / 2];
		for (int i = 0; i < encrypted.length(); i += 2) {
			String charBits = HexUtil.hexCharToBits(encrypted.charAt(i))
					+ HexUtil.hexCharToBits(encrypted.charAt(i + 1));
			rawCodes[i / 2] = BinUtil.bitStreamToInt(charBits);
		}

		int bestScore = 0;
		int bestCode = -1;
		String bestMessage = null;
		int[] decCodes = new int[encrypted.length() / 2];

		for (int i = 0; i <= MAX_CODE; ++i) {
			int score = 0;
			for (int j = 0; j < rawCodes.length; ++j) {
				int d = i ^ rawCodes[j];
				if (Character.isAlphabetic(d) || Character.isWhitespace(d)) {
					++score;
				}
				decCodes[j] = d;
			}

			if (score > bestScore) {
				bestScore = score;
				bestCode = i;
				bestMessage = codePointsToString(decCodes);
			}
		}

		if (!SILENT) {
			System.out.println("Found the best decryption key: " + (char) bestCode);
			System.out.println("Decrypted message as: " + bestMessage);
		}
		return bestMessage;
	}

	private static String codePointsToString(int[] codes) {
		if (codes == null || codes.length == 0) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		for (int c : codes) {
			builder.append((char) c);
		}

		return builder.toString();
	}
}
