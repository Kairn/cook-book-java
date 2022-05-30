package io.esoma.cbj.prob;

/**
 * Class for solving the Interleaving String problem where given two source strings, the task is to
 * determine if they can be interleaved together to form a third string. The interleaving process is
 * completed by merging the source strings together without changing the character order in each
 * string. For example, string "abc" can be interleaved with string "xyz" to produce "axbycz",
 * "abxyzc", or "xyzabc", etc., but "cbaxyz" is not legal because the character order of the first
 * source string is not preserved in the final string.
 */
public class InterleavingString {

  private InterleavingString() {}

  public static boolean judge(String src1, String src2, String dest) {
    return false;
  }
}
