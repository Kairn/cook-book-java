package io.esoma.cbj.crypto.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import io.esoma.cbj.crypto.core.HexUtil;

/**
 * Class for studying how to detect a cipher encrypted by AES in ECB mode. AES
 * uses block size of 16 bytes, and in ECB mode, the same plain text will
 * produce the same cipher text. A simple detection mechanism is to count the
 * repeated bytes on each block of the same offset, and if there is enough
 * repetition, it is a strong indication that the cipher is produced in ECB
 * mode.
 * 
 * @author Eddy Soma
 *
 */
public class DetectAesInEcb {

	private static final boolean SILENT = true;
	// An arbitrarily chosen prime number to be added to the score for each
	// repetition.
	private static final int REPEAT_BOUNS = 251;
	private static final int BLOCK_SIZE = 16;

	public static void main(String[] args) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		final String inputFileName = "DetectAesInEcbTestCases.txt";
		int lineNum = 1;
		int bestLine = 0;
		double bestScore = 0.0;
		String ecbCipher = null;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(loader.getResourceAsStream(inputFileName)))) {
			for (String text : br.lines().collect(Collectors.toList())) {
				double score = calcScore(HexUtil.stringToRawBytes(text));
				if (score > bestScore) {
					bestScore = score;
					bestLine = lineNum;
					ecbCipher = text;
				}
				++lineNum;
			}
		} catch (Exception e) {
			throw new IllegalStateException("Failed to process file");
		}

		System.out.format("Detected ECB cipher <%s> on line <%d> with score of <%.2f>", ecbCipher, bestLine, bestScore);
	}

	/**
	 * Calculates a normalized score for a stream of cipher bytes. The higher the
	 * score, the more frequently repetition occurs, and thus the more likely the
	 * cipher is encrypted using ECB mode.
	 * 
	 * @param cipherBytes the cipher text in bytes
	 * @return the resulting score
	 */
	public static double calcScore(byte[] cipherBytes) {
		if (cipherBytes == null || cipherBytes.length <= BLOCK_SIZE) {
			throw new IllegalArgumentException("Insufficient number of bytes");
		}

		int numBlocks = cipherBytes.length / BLOCK_SIZE;
		numBlocks = cipherBytes.length % BLOCK_SIZE == 0 ? numBlocks : numBlocks + 1;
		byte[][] blocks = new byte[numBlocks][];

		int cursor = 0;
		while (cursor < cipherBytes.length) {
			if (cipherBytes.length - cursor > BLOCK_SIZE) {
				blocks[cursor / BLOCK_SIZE] = Arrays.copyOfRange(cipherBytes, cursor, cursor + BLOCK_SIZE);
				cursor += BLOCK_SIZE;
			} else {
				blocks[cursor / BLOCK_SIZE] = Arrays.copyOfRange(cipherBytes, cursor, cipherBytes.length);
				break;
			}
		}

		double score = 0.0;
		for (int i = 0; i < BLOCK_SIZE; ++i) {
			int total = 0;
			Set<Byte> uniques = new HashSet<>();
			for (byte[] block : blocks) {
				if (block.length > i) {
					++total;
					uniques.add(block[i]);
				}
			}

			int repeats = total - uniques.size();
			score += REPEAT_BOUNS * repeats;
		}

		score /= cipherBytes.length;
		if (!SILENT) {
			System.out.println("Got score: " + score);
		}
		return score;
	}

}
