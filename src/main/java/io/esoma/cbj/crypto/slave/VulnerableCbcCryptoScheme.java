package io.esoma.cbj.crypto.slave;

import io.esoma.cbj.crypto.block.AesInCbc;

/**
 * An AES-128 crypto scheme that uses CBC mode, but it has an extra function
 * that returns if the cipher text has valid padding after decryption for the
 * given IV, which makes it vulnerable to padding oracle attacks.
 * 
 * @author Eddy Soma
 *
 */
public class VulnerableCbcCryptoScheme implements CryptoScheme {

	private static final int BLOCK_SIZE = 16;
	private static final String SECRET_KEY = "TOMATO SUBMARINE";

	@Override
	public byte[] encrypt(byte[] buffer) {
		return encrypt(buffer, new byte[BLOCK_SIZE]);
	}

	public byte[] encrypt(byte[] buffer, byte[] iv) {
		return AesInCbc.encrypt(buffer, SECRET_KEY, iv, true);
	}

	@Override
	public byte[] decrypt(byte[] buffer) {
		return decrypt(buffer, new byte[BLOCK_SIZE]);
	}

	public byte[] decrypt(byte[] buffer, byte[] iv) {
		return AesInCbc.decrypt(buffer, SECRET_KEY, iv);
	}

	public boolean hasValidPadding(byte[] cipherBytes, byte[] iv) {
		try {
			decrypt(cipherBytes, iv);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

}
