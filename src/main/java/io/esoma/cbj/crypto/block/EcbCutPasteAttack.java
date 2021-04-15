package io.esoma.cbj.crypto.block;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

import io.esoma.cbj.crypto.slave.SillyUserProfileRegistrar;
import io.esoma.cbj.crypto.slave.SimpleUserProfile;

/**
 * Class for implementing the cut-and-paste attack on ECB encryption. The idea
 * is to have the encryption scheme encrypt a carefully crafted piece of data
 * which is then substituted for another part of a cipher to manipulate data at
 * will.
 * 
 * @author Eddy Soma
 *
 */
public class EcbCutPasteAttack {

	private static final int BLOCK_SIZE = 16;
	private static final SillyUserProfileRegistrar REGISTRAR = new SillyUserProfileRegistrar();

	public static void main(String[] args) {
		// The attack strings are designed to force the cut-and-paste information to be
		// on separate encryption blocks in order to do easy substitution. The filler
		// content does not matter, but the length must be precise.
		String attackEmail = "lololol@a.com";
		String attackString = "ffffffffffADMIN";
		byte[] attackPadding = new byte[11];
		Arrays.fill(attackPadding, (byte) 11);
		byte[] attackBytes = ArrayUtils.addAll(attackString.getBytes(), attackPadding);
		attackString = new String(attackBytes);

		byte[] adminBytes = Arrays.copyOfRange(REGISTRAR.register(attackString), BLOCK_SIZE, BLOCK_SIZE * 2);
		byte[] credentials = REGISTRAR.register(attackEmail);
		byte[] adminCredentials = ArrayUtils.addAll(Arrays.copyOfRange(credentials, 0, BLOCK_SIZE * 2), adminBytes);

		SimpleUserProfile myProfile = REGISTRAR.login(adminCredentials);
		if (myProfile.isAdmin()) {
			System.out.format("Successfully created a user with <%s> role", myProfile.getRole());
		}
	}

}
