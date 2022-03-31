package io.esoma.cbj.crypto.basic;

import io.esoma.cbj.crypto.core.Base64Util;
import io.esoma.cbj.crypto.core.HexUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Class for implementing the function that converts a hex encoded string into a base64 encoded
 * string.
 *
 * @author Eddy Soma
 */
public class HexToBase64 {

  private HexToBase64() {}

  public static String perform(String hexString) {
    if (StringUtils.isBlank(hexString)) {
      return "";
    } else {
      hexString = hexString.toLowerCase();
    }

    StringBuilder builder = new StringBuilder();
    for (char c : hexString.toCharArray()) {
      builder.append(HexUtil.hexCharToBits(c));
    }

    String bitStream = builder.toString();
    return Base64Util.encodeFromBitStream(bitStream);
  }
}
