package io.esoma.cbj.crypto.basic;

import io.esoma.cbj.crypto.core.HexUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Class for implementing the function that computes the XOR result of equal length hex encoded
 * strings or bytes.
 *
 * @author Eddy Soma
 */
public class FixedXor {

  public static String combine(String first, String second) {
    if (StringUtils.isAnyBlank(first, second) || first.length() != second.length()) {
      throw new IllegalArgumentException("Invalid string inputs");
    }

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < first.length(); ++i) {
      String bits1 = HexUtil.hexCharToBits(first.charAt(i));
      String bits2 = HexUtil.hexCharToBits(second.charAt(i));
      builder.append(HexUtil.bitsToHexChar(xor(bits1, bits2)));
    }

    return builder.toString();
  }

  public static byte[] combine(byte[] block1, byte[] block2) {
    if (block1 == null || block2 == null || block1.length != block2.length) {
      throw new IllegalArgumentException("Invalid blocks");
    }

    byte[] result = new byte[block1.length];
    for (int i = 0; i < block1.length; ++i) {
      result[i] = (byte) (block1[i] ^ block2[i]);
    }

    return result;
  }

  private static String xor(String first, String second) {
    if (StringUtils.isAnyBlank(first, second) || first.length() != second.length()) {
      throw new IllegalArgumentException("Invalid string inputs");
    }

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < first.length(); ++i) {
      char bit1 = first.charAt(i);
      char bit2 = second.charAt(i);

      if (bit1 == bit2) {
        builder.append('0');
      } else {
        builder.append('1');
      }
    }

    return builder.toString();
  }

  private FixedXor() {}
}
