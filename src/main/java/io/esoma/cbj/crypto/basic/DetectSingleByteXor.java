package io.esoma.cbj.crypto.basic;

import io.esoma.cbj.util.ResourceLoader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.commons.lang3.StringUtils;
import org.tinylog.Logger;

/**
 * Class for implementing the function to detect messages that have been encrypted by the
 * single-byte XOR cipher algorithm. It leverages the {@link
 * SingleByteXorCipher#decryptMessage(String)} method as the base logic for attempting to decrypt
 * the cipher assuming it is encrypted in the same fashion.
 *
 * @author Eddy Soma
 */
public class DetectSingleByteXor {

    // Decryption is considered successful if at least <THRESHOLD>% of the
    // characters of the resulting string are valid English characters.
    private static final int THRESHOLD = 95;

    public static void main(String[] args) {
        String inputFileName = "DetectSingleByteXorTestCases.txt";
        try (BufferedReader br =
                new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsReader(inputFileName)))) {
            br.lines().forEach(encrypted -> {
                String decrypted = SingleByteXorCipher.decryptMessage(encrypted).getDecrypted();
                if (isGoodMessage(decrypted)) {
                    Logger.info("Detected cipher <{}> which yields: {}", encrypted, decrypted);
                }
            });
        } catch (Exception e) {
            throw new IllegalStateException("Unable to read file", e);
        }
    }

    private static boolean isGoodMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return false;
        }

        int passScore = message.length() * THRESHOLD;
        int score = 0;
        for (char c : message.toCharArray()) {
            if (Character.isLowerCase(c) || Character.isUpperCase(c) || Character.isWhitespace(c)) {
                score += 100;
            }
        }

        return score >= passScore;
    }
}
