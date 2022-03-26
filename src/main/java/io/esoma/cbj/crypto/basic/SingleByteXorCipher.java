package io.esoma.cbj.crypto.basic;

import io.esoma.cbj.crypto.core.BinUtil;
import io.esoma.cbj.crypto.core.HexUtil;
import io.esoma.cbj.crypto.core.SimpleCipherOutput;
import org.apache.commons.lang3.StringUtils;
import org.tinylog.Logger;

/**
 * Class for studying the algorithm to decrypt a hex encoded string that has been XORed by a
 * single-byte character. It will be a brute force algorithm that tries all single-byte character
 * keys and gives the best guess.
 *
 * @author Eddy Soma
 */
public class SingleByteXorCipher {

  private static final boolean SILENT = true;
  private static final char MAX_CODE = 255;

  public static void main(String[] args) {
    Logger.info(
        decryptMessage("1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"));
  }

  public static SimpleCipherOutput<Character> decryptMessage(String encrypted) {
    if (StringUtils.isBlank(encrypted) || encrypted.length() % 2 != 0) {
      throw new IllegalArgumentException("Invalid encrypted string");
    }

    final int[] rawCodes = new int[encrypted.length() / 2];
    for (int i = 0; i < encrypted.length(); i += 2) {
      String charBits =
          HexUtil.hexCharToBits(encrypted.charAt(i))
              + HexUtil.hexCharToBits(encrypted.charAt(i + 1));
      rawCodes[i / 2] = BinUtil.bitStreamToInt(charBits);
    }

    SimpleCipherOutput<Character> output = decryptBytes(rawCodes);
    output.setEncrypted(encrypted);
    return output;
  }

  public static SimpleCipherOutput<Character> decryptBytes(int[] bytes) {
    if (bytes == null || bytes.length == 0) {
      throw new IllegalArgumentException("Invalid bytes");
    }

    int bestScore = 0;
    int bestCode = -1;
    String bestMessage = null;
    int[] decCodes = new int[bytes.length];

    for (int i = 0; i <= MAX_CODE; ++i) {
      int score = 0;
      for (int j = 0; j < bytes.length; ++j) {
        int d = i ^ bytes[j];
        if (Character.isLetter(d) || Character.isWhitespace(d)) {
          ++score;
        }
        decCodes[j] = d;
      }

      if (score > bestScore) {
        bestScore = score;
        bestCode = i;
        bestMessage = codePointsToString(decCodes);
      }
    }

    if (!SILENT) {
      Logger.info("Found the best decryption key: " + (char) bestCode);
      Logger.info("Decrypted message as: " + bestMessage);
    }
    return new SimpleCipherOutput<>(null, bestMessage, (char) bestCode);
  }

  private static String codePointsToString(int[] codes) {
    if (codes == null || codes.length == 0) {
      return "";
    }

    StringBuilder builder = new StringBuilder();
    for (int c : codes) {
      builder.append((char) c);
    }

    return builder.toString();
  }
}
