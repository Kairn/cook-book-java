package io.esoma.cbj.crypto.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Key;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

import io.esoma.cbj.crypto.core.Base64Util;

/**
 * Class for exploring the AES-128 encryption standard in ECB mode (not secure
 * in practice). It leverages the Java cryptography library to simply the core
 * methods.
 * 
 * @author Eddy Soma
 *
 */
public class AesInEcb {

	private static final String ALGORITHM = "AES";
	private static final String PAD_SCHEME = "AES/ECB/PKCS5Padding";
	private static final String NO_PAD_SCHEME = "AES/ECB/NoPadding";

	public static void main(String[] args) {
		final String secretKey = "YELLOW SUBMARINE";
		final String inputFileName = "AesInEcbText.txt";
		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		String inputText = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(loader.getResourceAsStream(inputFileName)))) {
			inputText = br.lines().collect(Collectors.joining());
		} catch (Exception e) {
			throw new IllegalStateException("Unable to read input file");
		}

		System.out.println("Decrypted message: " + decryptBase64(inputText, secretKey));
	}

	/**
	 * Decrypts an Base64 encoded cipher text with the given key in ECB mode.
	 * 
	 * @param input the cipher text in Base64
	 * @param key   the 16-byte key string
	 * @return the decrypted text
	 */
	public static String decryptBase64(String input, String key) {
		if (StringUtils.isAnyBlank(input, key)) {
			throw new IllegalArgumentException("Invalid input or key");
		}

		byte[] cipherBytes = Base64Util.decodeToByteArray(input);
		return new String(decrypt(cipherBytes, key, true));
	}

	public static byte[] decrypt(byte[] input, String key, boolean padding) {
		if (input == null || StringUtils.isBlank(key)) {
			throw new IllegalArgumentException("Invalid input or key");
		}

		try {
			Key aesKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
			Cipher aesCipher = Cipher.getInstance(padding ? PAD_SCHEME : NO_PAD_SCHEME);
			aesCipher.init(Cipher.DECRYPT_MODE, aesKey);

			return aesCipher.doFinal(input);
		} catch (Exception e) {
			throw new IllegalStateException("Failed to decrypt");
		}
	}

}
