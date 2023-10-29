package io.esoma.cbj.crypto.block;

import io.esoma.cbj.crypto.basic.AesInEcb;
import io.esoma.cbj.crypto.core.RandUtil;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.tinylog.Logger;

public class EcbVsCbcOracle {

    private static final int LENGTH = 50;
    private static final int BLOCK_SIZE = 16;
    private static final int ROUNDS = 100;
    private static final char TEST_CHAR = 'F';

    public static void main(String[] args) {
        byte[] inputBytes = String.valueOf(TEST_CHAR).repeat(LENGTH).getBytes();

        int correctCount = 0;
        for (int i = 0; i < ROUNDS; ++i) {
            boolean isCbc = RandUtil.randBool();
            byte[] encrypted = randEncrypt(inputBytes, isCbc);
            boolean detectCbc = detectIfCbc(encrypted);
            if (isCbc == detectCbc) {
                ++correctCount;
            }
        }

        Logger.info("Detection oracle got {} out of {} cases correct", correctCount, ROUNDS);
    }

    private static byte[] randEncrypt(byte[] inputBytes, boolean isCbc) {
        if (inputBytes == null || inputBytes.length == 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        final String keyString = RandomStringUtils.randomAscii(BLOCK_SIZE);

        int prepNum = RandUtil.randInt(5, 10);
        int appeNum = RandUtil.randInt(5, 10);
        byte[] prep = RandUtil.randBytes(prepNum);
        byte[] appe = RandUtil.randBytes(appeNum);

        byte[] postBytes = ArrayUtils.addAll(prep, inputBytes);
        postBytes = ArrayUtils.addAll(postBytes, appe);

        if (isCbc) {
            // Use CBC mode.
            final byte[] iv = RandUtil.randBytes(BLOCK_SIZE);
            return AesInCbc.encrypt(postBytes, keyString, iv, true);
        } else {
            // Use ECB mode.
            return AesInEcb.encrypt(postBytes, keyString, true);
        }
    }

    private static boolean detectIfCbc(byte[] cipherBytes) {
        byte[] block2 = Arrays.copyOfRange(cipherBytes, BLOCK_SIZE, BLOCK_SIZE * 2);
        byte[] block3 = Arrays.copyOfRange(cipherBytes, BLOCK_SIZE * 2, BLOCK_SIZE * 3);
        return !Arrays.equals(block2, block3);
    }
}
