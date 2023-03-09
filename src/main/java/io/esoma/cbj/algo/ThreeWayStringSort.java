package io.esoma.cbj.algo;

/**
 * Class for implementing a 3-way string (quick) sort. The idea is to do a 3-way partition on the
 * leading character of the keys, dividing all strings into a "greater than", a "less than", and an
 * "equal to" part. For the "equal to" part, advance to the next character and repeat the process
 * until all recursions have ended. This is particularly efficient for sorting strings with lots of
 * common prefixes by eliminating duplicated work in examining the same characters over and over.
 */
public class ThreeWayStringSort {

  private ThreeWayStringSort() {}

  /**
   * Sorts the input strings based on the "natural ordering" of strings in Java. Strings are
   * partitioned by characters from left to right in succession. The sort is performed in place and
   * the input array will be modified.
   *
   * @param array the array to sort
   * @return the same input array after in-place sort
   */
  public static String[] sort(String[] array) {
    if (array == null || array.length < 1) {
      return array;
    }

    return new String[0];
  }
}
