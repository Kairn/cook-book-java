package io.esoma.cbj.crypto.core;

/**
 * Utility class for providing character related functionalities.
 *
 * @author Eddy Soma
 */
public class CharUtil {

  public static boolean isCommonEnglishChar(char c) {
    if (Character.isUpperCase(c)) {
      return true;
    } else if (Character.isLowerCase(c)) {
      return true;
    } else if (Character.isWhitespace(c)) {
      return true;
    } else if (c == ',' || c == '.') {
      return true;
    }

    return false;
  }
}
