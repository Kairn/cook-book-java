package io.esoma.cbj.crypto.block;

import io.esoma.cbj.crypto.slave.EncryptionScheme;
import io.esoma.cbj.crypto.slave.SillyEcbEncryptionScheme;

/**
 * Class for implementing an attack function that evaluates the outputs of a
 * particular ECB encryption scheme and uncovers the secret unknown text
 * appended by the scheme.
 * 
 * @author Eddy Soma
 *
 */
public class EcbOracleAttack {

	private static final int BLOCK_SIZE = 16;

	public static void main(String[] args) {
		String unknownText = attackAndExtract(new SillyEcbEncryptionScheme());
		System.out.println("Uncovered unknown text: " + unknownText);
	}

	public static String attackAndExtract(EncryptionScheme scheme) {
		if (scheme == null) {
			throw new IllegalArgumentException("Scheme is required");
		}

		// FIXME Implement the attack function.
		return null;
	}

}
