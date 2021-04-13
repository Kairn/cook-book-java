package io.esoma.cbj.crypto.slave;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;

import io.esoma.cbj.crypto.basic.AesInEcb;
import io.esoma.cbj.crypto.core.Base64Util;

/**
 * Class that implements a naive and insecure AES-128 encryption scheme using
 * ECB mode. An unknown string is always appended to the end of the input buffer
 * before encryption.
 * 
 * @author Eddy Soma
 *
 */
public class SillyEcbEncryptionScheme implements EncryptionScheme {

	private static final int BLOCK_SIZE = 16;
	private static final String INPUT_FILE_NAME = "SillyEcbEncryptionSchemeText.txt";
	private static final String SECRET_KEY;
	private static final byte[] UNKNOWN_TEXT;

	static {
		SECRET_KEY = RandomStringUtils.randomAscii(BLOCK_SIZE);

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(loader.getResourceAsStream(INPUT_FILE_NAME)))) {
			UNKNOWN_TEXT = Base64Util.decodeToByteArray(br.lines().collect(Collectors.joining()));
		} catch (Exception e) {
			throw new IllegalStateException("Failed to read unknown text file");
		}
	}

	@Override
	public byte[] encrypt(byte[] buffer) {
		if (buffer == null) {
			buffer = new byte[0];
		}

		byte[] postInput = ArrayUtils.addAll(buffer, UNKNOWN_TEXT);
		return AesInEcb.encrypt(postInput, SECRET_KEY, true);
	}

}
