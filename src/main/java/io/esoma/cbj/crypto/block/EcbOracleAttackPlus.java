package io.esoma.cbj.crypto.block;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import io.esoma.cbj.crypto.core.ByteMemory;
import io.esoma.cbj.crypto.slave.EncryptionScheme;
import io.esoma.cbj.crypto.slave.LessSillyEcbEncryptionScheme;

/**
 * Class that focuses on cracking the unknown secret text behind a less silly
 * ECB encryption oracle which prepends some unknown bytes to the text before
 * encrypting.
 * 
 * @author Eddy Soma
 *
 */
public class EcbOracleAttackPlus {

	private static final int BLOCK_SIZE = 16;
	private static final byte[] ATTACK_REPO = new byte[BLOCK_SIZE * 4 - 1];

	public static void main(String[] args) {
		EncryptionScheme targetScheme = new LessSillyEcbEncryptionScheme();

		int len = findAttackPrefixLength(targetScheme);
		System.out.println("Found the attack prefix length: " + len);
		byte[] unknownText = attackAndExtract(targetScheme, len);
		System.out.println("Uncovered unknown text: " + new String(unknownText));
	}

	public static byte[] attackAndExtract(EncryptionScheme scheme, int len) {
		if (scheme == null) {
			throw new IllegalArgumentException("Scheme is required");
		}

		byte[] prefixBlock = new byte[len];

		int textLen = scheme.encrypt(prefixBlock).length;
		byte[] knowledge = new byte[textLen];
		int cursor = BLOCK_SIZE;
		int attackPos = BLOCK_SIZE * 2 - 1;

		while (cursor < textLen) {
			int start = Math.max(BLOCK_SIZE, cursor - (BLOCK_SIZE - 1));
			byte[] attackBlock = Arrays.copyOfRange(knowledge, start, cursor + 1);
			if (attackBlock.length < BLOCK_SIZE) {
				int missing = BLOCK_SIZE - attackBlock.length;
				attackBlock = ArrayUtils.addAll(new byte[missing], attackBlock);
			}
			attackBlock = ArrayUtils.addAll(prefixBlock, attackBlock);

			Map<ByteMemory, Byte> table = new HashMap<>();
			for (int b = 0; b <= Byte.MAX_VALUE; ++b) {
				// Always brute force the last byte of the attack block.
				attackBlock[attackBlock.length - 1] = (byte) b;
				byte[] output = scheme.encrypt(attackBlock);
				table.put(new ByteMemory(output[attackPos], output[attackPos - 1], output[attackPos - 2]), (byte) b);
			}

			int pushOffset = (BLOCK_SIZE - 1) - (cursor % BLOCK_SIZE);
			byte[] pushBlock = ArrayUtils.addAll(prefixBlock, new byte[pushOffset]);

			int hackPos = cursor + pushOffset;
			byte[] oracleBlock = scheme.encrypt(pushBlock);
			ByteMemory footPrint = new ByteMemory(oracleBlock[hackPos], oracleBlock[hackPos - 1],
					oracleBlock[hackPos - 2]);
			byte gist = table.get(footPrint);
			if (gist == 1) {
				break;
			} else {
				knowledge[cursor] = gist;
			}

			++cursor;
		}

		return Arrays.copyOfRange(knowledge, BLOCK_SIZE, cursor);
	}

	public static int findAttackPrefixLength(EncryptionScheme scheme) {
		if (scheme == null) {
			throw new IllegalArgumentException("Scheme is required");
		}

		// The encrypted cipher of all zero bytes.
		byte[] zeroCipher = Arrays.copyOfRange(scheme.encrypt(ATTACK_REPO), BLOCK_SIZE * 2, BLOCK_SIZE * 3);

		for (int i = 0; i < BLOCK_SIZE; ++i) {
			byte[] attackBlock = new byte[BLOCK_SIZE + i];
			byte[] refBlock = Arrays.copyOfRange(scheme.encrypt(attackBlock), BLOCK_SIZE, BLOCK_SIZE * 2);
			if (Arrays.equals(refBlock, zeroCipher)) {
				return i;
			}
		}

		throw new IllegalStateException("Can't find the attack prefix length");
	}

}
