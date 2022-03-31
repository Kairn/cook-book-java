package io.esoma.cbj.algo;

/**
 * Class for studying the Longest Common Subsequence (LCS) problem. A subsequence is a sequence that
 * can be derived from another sequence by deleting some elements without changing the order of the
 * remaining elements. LCS of 2 sequences is a subsequence, with maximal length, which is common to
 * both the sequences.
 *
 * @author Eddy Soma
 */
public class LongestCommonSubseq {

  private LongestCommonSubseq() {}

  /**
   * Computes the length of the LCS between the two given strings. It utilizes dynamic programming
   * with a cache table for optimal performance. It will return 0 if no common character is found.
   *
   * @param s1 the first string
   * @param s2 the second string
   * @return the length of the LCS
   */
  public static int findLcs(String s1, String s2) {
    int l1 = s1.length();
    int l2 = s2.length();
    int[][] cache = new int[l1 + 1][l2 + 1];

    for (int i = 0; i < l1; ++i) {
      int c1 = s1.charAt(i);
      for (int j = 0; j < l2; ++j) {
        int c2 = s2.charAt(j);
        if (c1 == c2) {
          // Consume the character when both strings have it.
          cache[i + 1][j + 1] = cache[i][j] + 1;
        } else {
          // Otherwise, record the longest existing LCS length up to this point.
          cache[i + 1][j + 1] = Math.max(cache[i][j + 1], cache[i + 1][j]);
        }
      }
    }

    return cache[l1][l2];
  }
}
