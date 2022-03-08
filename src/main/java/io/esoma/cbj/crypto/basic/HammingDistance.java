package io.esoma.cbj.crypto.basic;

import io.esoma.cbj.crypto.core.BinUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Class for implementing the function that computes the Hamming distance between two equal length
 * strings. The Hamming distance is defined as the number of different bits between two strings.
 *
 * @author Eddy Soma
 */
public class HammingDistance {

  public static int compute(String first, String second) {
    if (StringUtils.isAnyBlank(first, second) || first.length() != second.length()) {
      throw new IllegalArgumentException("Invalid input strings");
    }

    int distance = 0;
    for (int i = 0; i < first.length(); ++i) {
      distance += compute(first.charAt(i), second.charAt(i));
    }

    return distance;
  }

  public static int compute(char c1, char c2) {
    int xor = c1 ^ c2;
    return BinUtil.getBitsSet(xor, 8);
  }

  public static int computeByteArrays(byte[] chunk1, byte[] chunk2) {
    if (chunk1 == null || chunk2 == null || chunk1.length != chunk2.length) {
      throw new IllegalArgumentException("Invalid input arrays");
    }

    int distance = 0;
    for (int i = 0; i < chunk1.length; ++i) {
      distance += compute((char) chunk1[i], (char) chunk2[i]);
    }

    return distance;
  }

  private HammingDistance() {}
}
