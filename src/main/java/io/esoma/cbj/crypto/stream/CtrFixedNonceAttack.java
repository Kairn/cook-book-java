package io.esoma.cbj.crypto.stream;

import io.esoma.cbj.crypto.core.Base64Util;
import io.esoma.cbj.crypto.core.CharUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for implementing an exploit against fixed-nonce CTR encryption scheme. By evaluating a
 * large number of cipher texts produced by the same CTR encryption of the same nonce, we can make
 * informative guesses on the key stream bytes.
 *
 * @author Eddy Soma
 */
public class CtrFixedNonceAttack {

  public static final String TEST_FILE = "CtrAttackText.txt";
  public static final String TEST_KEY = "ALMOND SUBMARINE";

  static final byte[] TEST_NONCE = new byte[8];

  private CtrFixedNonceAttack() {}

  /**
   * Enumerates the cipher texts and tests each byte on each position to obtain a good guess of the
   * key stream given the ciphers are produced by CTR with a fixed-nonce. The strategy for guessing
   * is based on the assumption that the plain texts are all common English sentences.
   *
   * @param cipherArrays the cipher texts
   * @param length the length of the guessed output
   * @return the guessed key stream
   */
  public static byte[] guessKeyStream(byte[][] cipherArrays, int length) {
    if (cipherArrays == null || cipherArrays.length < 1 || length < 1) {
      throw new IllegalArgumentException("Invalid array input or guess length");
    }

    byte[] guessed = new byte[length];
    for (int i = 0; i < length; ++i) {
      byte bestByte = Byte.MIN_VALUE;
      int bestScore = 0;
      for (byte b = Byte.MIN_VALUE; ; ++b) {
        int score = 0;
        for (byte[] cipher : cipherArrays) {
          byte cbyte = cipher[i];
          byte pbyte = (byte) (cbyte ^ b);
          if (CharUtil.isCommonEnglishChar((char) pbyte)) {
            score++;
          }
        }
        if (score > bestScore) {
          bestScore = score;
          bestByte = b;
        }
        if (b == Byte.MAX_VALUE) {
          break;
        }
      }
      guessed[i] = bestByte;
    }

    return guessed;
  }

  public static byte[] loadAndGuess() {
    byte[][] cipherArrays;
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    try (BufferedReader reader =
        new BufferedReader(new InputStreamReader(loader.getResourceAsStream(TEST_FILE)))) {
      List<String> encodedInputs = reader.lines().collect(Collectors.toList());
      cipherArrays = new byte[encodedInputs.size()][];
      for (int i = 0; i < encodedInputs.size(); ++i) {
        byte[] decodedBytes = Base64Util.decodeToByteArray(encodedInputs.get(i));
        cipherArrays[i] =
            AesInCtr.encrypt(decodedBytes, TEST_KEY, TEST_NONCE, new LeIntegerCounterGenerator());
      }
    } catch (Exception e) {
      throw new IllegalStateException("Unable to read the file");
    }

    return guessKeyStream(cipherArrays, 16);
  }
}
