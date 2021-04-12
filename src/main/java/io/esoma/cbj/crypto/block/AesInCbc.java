package io.esoma.cbj.crypto.block;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import io.esoma.cbj.crypto.basic.AesInEcb;
import io.esoma.cbj.crypto.basic.FixedXor;
import io.esoma.cbj.crypto.core.Base64Util;

/**
 * Class for exploring the AES-128 encryption scheme in CBC mode. This is pseudo
 * hand-written implementation of the algorithm, as it leverages the
 * {@link AesInEcb} class to do the low-level operations.
 * 
 * @author Eddy Soma
 *
 */
public class AesInCbc {

	private static final int BLOCK_SIZE = 16;

	public static void main(String[] args) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		final String inputFileName = "AesInCbcText.txt";

		byte[] cipherBytes = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(loader.getResourceAsStream(inputFileName)))) {
			String text = br.lines().collect(Collectors.joining());
			cipherBytes = Base64Util.decodeToByteArray(text);
		} catch (Exception e) {
			throw new IllegalStateException("Failed to read file");
		}

		// Null initialization vector.
		final byte[] iv = new byte[BLOCK_SIZE];
		final String key = "YELLOW SUBMARINE";
		String originalText = new String(decrypt(cipherBytes, key, iv));

		System.out.println("Decrypted message: " + originalText);
	}

	/**
	 * Decrypts a stream of cipher bytes with AES-128 in CBC mode when the key is
	 * known.
	 * 
	 * @param cipherBytes the encrypted bytes
	 * @param key         the key string
	 * @param iv          the initialization vector
	 * @return the decrypted bytes
	 */
	public static byte[] decrypt(byte[] cipherBytes, String key, byte[] iv) {
		if (cipherBytes == null || StringUtils.isBlank(key) || iv == null) {
			throw new IllegalArgumentException("Insufficient information");
		} else if (cipherBytes.length % BLOCK_SIZE != 0 || iv.length != BLOCK_SIZE) {
			throw new IllegalArgumentException("Incorrect cipher format");
		}

		int numBlocks = cipherBytes.length / BLOCK_SIZE;
		byte[] piv = iv;
		byte[][] finalized = new byte[numBlocks][];

		for (int i = 0; i < numBlocks; ++i) {
			byte[] encrypted = Arrays.copyOfRange(cipherBytes, i * BLOCK_SIZE, (i + 1) * BLOCK_SIZE);
			byte[] decrypted = AesInEcb.decrypt(encrypted, key, false);
			finalized[i] = FixedXor.combine(decrypted, piv);
			piv = encrypted;
		}

		byte[] lastBlock = PKCS7Padding.unpad(finalized[finalized.length - 1]);
		finalized[finalized.length - 1] = lastBlock;
		byte[] finalBuffer = new byte[cipherBytes.length - BLOCK_SIZE + lastBlock.length];
		for (int i = 0; i < finalized.length; ++i) {
			byte[] block = finalized[i];
			for (int j = 0; j < block.length; ++j) {
				finalBuffer[i * BLOCK_SIZE + j] = block[j];
			}
		}

		return finalBuffer;
	}

}
