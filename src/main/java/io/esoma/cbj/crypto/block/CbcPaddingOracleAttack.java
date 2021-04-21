package io.esoma.cbj.crypto.block;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import io.esoma.cbj.crypto.core.Base64Util;
import io.esoma.cbj.crypto.core.BinUtil;
import io.esoma.cbj.crypto.core.RandUtil;
import io.esoma.cbj.crypto.slave.VulnerableCbcCryptoScheme;

/**
 * Class that studies the famous CBC padding oracle attack. It attempts to
 * decrypt the message by repeatedly sending tampered cipher text to the
 * decryption engine and see if the padding is valid. The expected padding byte
 * is somewhat obvious to the attacker once it is valid, and therefore the plain
 * text byte is revealed by performing an XOR between the padding byte and the
 * cipher text byte.
 * 
 * @author Eddy Soma
 *
 */
public class CbcPaddingOracleAttack {

	private static final int BLOCK_SIZE = 16;
	private static final byte[] DEFAULT_IV = new byte[BLOCK_SIZE];

	public static void main(String[] args) {
		final String inputFileName = "CbcPaddingOracleAttackText.txt";
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		byte[] plainBytes;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(loader.getResourceAsStream(inputFileName)))) {
			List<String> lines = br.lines().collect(Collectors.toList());
			int index = RandUtil.randInt(0, lines.size() - 1);
			plainBytes = Base64Util.decodeToByteArray(lines.get(index));
		} catch (Exception e) {
			throw new IllegalStateException("Unable to read the file");
		}

		final VulnerableCbcCryptoScheme scheme = new VulnerableCbcCryptoScheme();
		byte[] cipherBytes = scheme.encrypt(plainBytes, DEFAULT_IV);
		byte[] decryptedBytes = attackAndReveal(scheme, cipherBytes);

		System.out.println("Uncovered original text: " + new String(decryptedBytes));
	}

	public static byte[] attackAndReveal(VulnerableCbcCryptoScheme scheme, byte[] cipherBytes) {
		if (scheme == null || cipherBytes == null || cipherBytes.length == 0 || cipherBytes.length % BLOCK_SIZE != 0) {
			throw new IllegalArgumentException("Invalid input information");
		}

		byte[] extendedCipher = ArrayUtils.addAll(DEFAULT_IV, cipherBytes);
		byte[] original = new byte[extendedCipher.length];
		byte[] decrypted = new byte[extendedCipher.length];

		for (int a = extendedCipher.length - 1; a >= BLOCK_SIZE; --a) {
			byte[] attackBytes = Arrays.copyOf(extendedCipher, extendedCipher.length);
			byte expectedPad = (byte) (BLOCK_SIZE - (a % BLOCK_SIZE));
			int prev = a - BLOCK_SIZE;
			int last = (BLOCK_SIZE - 1 - (prev % BLOCK_SIZE)) + prev;

			// Ensure previous cipher bytes all produce the same padding byte.
			for (int j = prev + 1; j <= last; ++j) {
				attackBytes[j] = (byte) (expectedPad ^ decrypted[j + BLOCK_SIZE]);
			}

			byte hitByte = 0;
			byte next = attackBytes[prev];
			for (;;) {
				next = BinUtil.next(next);
				attackBytes[prev] = next;
				byte[] iv = Arrays.copyOfRange(attackBytes, 0, BLOCK_SIZE);
				int end = last + BLOCK_SIZE + 1;
				byte[] tampered = Arrays.copyOfRange(attackBytes, BLOCK_SIZE, end);
				if (scheme.hasValidPadding(tampered, iv)) {
					// Valid padding is found.
					hitByte = next;
					break;
				}
			}

			decrypted[a] = (byte) (hitByte ^ expectedPad);
			original[a] = (byte) (decrypted[a] ^ extendedCipher[prev]);
		}

		original = Arrays.copyOfRange(original, BLOCK_SIZE, original.length);
		return PKCS7Padding.unpad(original, false);
	}

}
