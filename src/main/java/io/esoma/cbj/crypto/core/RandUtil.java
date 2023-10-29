package io.esoma.cbj.crypto.core;

import java.security.SecureRandom;

/**
 * Class for providing utility functions that manage the generation of random data and support other
 * related operations.
 *
 * @author Eddy Soma
 */
public class RandUtil {

    private static final SecureRandom PRNG = new SecureRandom();

    private RandUtil() {}

    public static byte[] randBytes(int size) {
        if (size > 0) {
            byte[] randBytes = new byte[size];
            PRNG.nextBytes(randBytes);
            return randBytes;
        }

        return new byte[0];
    }

    public static boolean randBool() {
        return PRNG.nextBoolean();
    }

    public static int randInt(int min, int max) {
        if (max <= min) {
            throw new IllegalArgumentException("Max must be larger than min");
        }

        int bound = max - min + 1;
        return min + PRNG.nextInt(bound);
    }
}
