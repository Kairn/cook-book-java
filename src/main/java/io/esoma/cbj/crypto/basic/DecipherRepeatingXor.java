package io.esoma.cbj.crypto.basic;

import io.esoma.cbj.crypto.core.Base64Util;
import io.esoma.cbj.crypto.core.SimpleCipherOutput;
import io.esoma.cbj.util.ResourceLoader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.tinylog.Logger;

/**
 * Class for studying how to decrypt a cipher encrypted by a repeating XOR algorithm.
 *
 * @author Eddy Soma
 */
public class DecipherRepeatingXor {

    private static final int MIN_KEY_SIZE = 2;
    private static final int MAX_KEY_SIZE = 40;

    public static void main(String[] args) {
        String input;
        String inputFileName = "DecipherRepeatingXorText.txt";
        try (BufferedReader br =
                new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsReader(inputFileName)))) {
            input = br.lines().collect(Collectors.joining());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to read file", e);
        }

        byte[] cipherBytes = Base64Util.decodeToByteArray(input);
        int keySize = guessKeySize(cipherBytes);
        String key = hackForKey(cipherBytes, keySize);

        Logger.info("Decrypted message as:\n" + RepeatingXor.create(cipherBytes, key, false));
    }

    private static int guessKeySize(byte[] bytes) {
        int bestKeySize = 0;
        double bestNhd = Double.MAX_VALUE;

        for (int i = MIN_KEY_SIZE; i <= MAX_KEY_SIZE; ++i) {
            if (i * 4 > bytes.length) {
                break;
            }

            double hd = 0.0;
            byte[] chunk1 = Arrays.copyOfRange(bytes, 0, i);
            byte[] chunk2 = Arrays.copyOfRange(bytes, i, i * 2);
            byte[] chunk3 = Arrays.copyOfRange(bytes, i * 2, i * 3);
            byte[] chunk4 = Arrays.copyOfRange(bytes, i * 3, i * 4);
            hd += HammingDistance.computeByteArrays(chunk1, chunk2);
            hd += HammingDistance.computeByteArrays(chunk1, chunk3);
            hd += HammingDistance.computeByteArrays(chunk1, chunk4);
            hd += HammingDistance.computeByteArrays(chunk2, chunk3);
            hd += HammingDistance.computeByteArrays(chunk2, chunk4);
            hd += HammingDistance.computeByteArrays(chunk3, chunk4);

            double nhd = hd / i;
            if (nhd < bestNhd) {
                bestNhd = nhd;
                bestKeySize = i;
            }
        }

        Logger.info("Best key size is: " + bestKeySize);
        return bestKeySize;
    }

    private static String hackForKey(byte[] bytes, int keySize) {
        if (bytes == null || bytes.length < keySize) {
            throw new IllegalArgumentException("Invalid bytes or key size");
        }

        int[][] blocks = new int[keySize][];
        int reminder = bytes.length % keySize;
        int size = bytes.length / keySize;
        for (int i = 0; i < reminder; ++i) {
            blocks[i] = new int[size + 1];
        }
        for (int i = reminder; i < keySize; ++i) {
            blocks[i] = new int[size];
        }

        for (int i = 0; i < bytes.length; ++i) {
            blocks[i % keySize][i / keySize] = bytes[i];
        }

        List<SimpleCipherOutput<Character>> outputs = new ArrayList<>();
        for (int[] block : blocks) {
            outputs.add(SingleByteXorCipher.decryptBytes(block));
        }

        StringBuilder keyBuilder = new StringBuilder();
        outputs.forEach(output -> keyBuilder.append(output.getKey()));
        String key = keyBuilder.toString();

        Logger.info("Hacked key: " + key);
        return key;
    }
}
