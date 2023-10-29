package io.esoma.cbj.crypto.slave;

import io.esoma.cbj.crypto.basic.AesInEcb;
import io.esoma.cbj.crypto.core.Base64Util;
import io.esoma.cbj.crypto.core.RandUtil;
import io.esoma.cbj.util.ResourceLoader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Class that implements a slightly more complex AES-128 encryption scheme. However, it still uses
 * ECB mode, and therefore it is vulnerable to oracle attacks. Besides having an unknown string
 * appended at the end of each plain text it receives, an stream of bytes of unknown size is
 * prepended as well before encryption.
 *
 * @author Eddy Soma
 */
public class LessSillyEcbEncryptionScheme implements EncryptionScheme {

    private static final String INPUT_FILE_NAME = "LessSillyEcbEncryptionSchemeText.txt";
    private static final String SECRET_KEY;
    private static final byte[] UNKNOWN_TEXT;
    private static final byte[] NOISY_BYTES;

    static {
        SECRET_KEY = "NAVAJO SUBMARINE";

        try (BufferedReader br =
                new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsReader(INPUT_FILE_NAME)))) {
            UNKNOWN_TEXT = Base64Util.decodeToByteArray(br.lines().collect(Collectors.joining()));
        } catch (Exception e) {
            throw new IllegalStateException("Failed to read unknown text file");
        }

        NOISY_BYTES = RandUtil.randBytes(RandUtil.randInt(1, 15));
    }

    @Override
    public byte[] encrypt(byte[] buffer) {
        if (buffer == null) {
            buffer = new byte[0];
        }

        byte[] postInput = ArrayUtils.addAll(NOISY_BYTES, buffer);
        postInput = ArrayUtils.addAll(postInput, UNKNOWN_TEXT);
        return AesInEcb.encrypt(postInput, SECRET_KEY, true);
    }
}
