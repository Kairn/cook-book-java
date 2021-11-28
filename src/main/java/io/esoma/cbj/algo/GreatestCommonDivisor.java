package io.esoma.cbj.algo;

/**
 * Class for studying the Greatest Common Divisor (GCD) problem. The GCD is defined to be the
 * largest positive integer that fully divides each of the integer in a group of non-zero integers.
 * For example, the GCD of 5, 10, 15 is 5.
 *
 * @author Eddy Soma
 */
public class GreatestCommonDivisor {

  // Integer bound (performance is significantly affected by this constant).
  private static final int MAX_INT = 10001;

  // Static references of the input array and the result cache.
  private static int[] sa;
  private static int[][][] cache;

  /**
   * Calculates the GCD between two integers using the Euclidean algorithm. The Euclidean algorithm
   * is based on the principle that the greatest common divisor of two numbers does not change if
   * the larger number is replaced by its difference with the smaller number. The operation
   * repeatedly replaces the larger integer of the two with the remainder of dividing it by the
   * smaller integer until the smaller integer is a factor of (or equal to) the larger one.
   *
   * @param a the first integer
   * @param b the second integer
   * @return the GCD of the two integers
   */
  private static int egcd(int a, int b) {
    if (a == 0 || b == 0) {
      return 0;
    } else {
      a = Math.abs(a);
      b = Math.abs(b);
      int c = Math.max(a, b);
      b = Math.min(a, b);
      a = c;
    }

    while (true) {
      int temp = a % b;
      if (temp == 0) {
        return b;
      } else {
        a = b;
        b = temp;
      }
    }
  }

  /**
   * Finds the GCD of the given integer array.
   *
   * @param array the input array
   * @return the GCD value
   */
  public static int findGcd(int[] array) {
    int len = array.length;
    if (len < 1) {
      return 0;
    }

    int gcd = array[0];
    for (int i = 1; i < len; ++i) {
      if (gcd == 1) {
        return 1;
      }
      gcd = egcd(gcd, array[i]);
    }

    return gcd;
  }

  /**
   * With the given integer array, finds the largest possible GCD that can be obtained by forming a
   * group of n integers individually picked (without replacement) from the array. For example, if
   * we have integers 4, 5, 10, 15, 6, 12, and n is 3, then we should pick 5, 10, 15 whose GCD is
   * the largest (5) amongst all combinations.
   *
   * @param array the input array
   * @param n the number of integers to select
   * @return the GCD of the chosen group
   */
  public static int bestGcd(int[] array, int n) {
    int len = array.length;
    if (len < 1 || n == 0) {
      return 0;
    }
    if (n > len) {
      n = len;
    }

    // Initialize static arrays.
    for (int i = 0; i < len; ++i) {
      if (array[i] == 0) {
        return 0;
      } else {
        array[i] = Math.abs(array[i]);
      }
    }
    sa = array;

    cache = new int[MAX_INT][len + 1][n + 1];

    return dpGcd(0, len, n);
  }

  /**
   * Computes the largest GCD with the given parameters and a cached array. This method is recursive
   * and built on the principle of dynamic programming. The core logic is to enumerate every element
   * of the array and decide if the current element should be a part of the final combination.
   * Caching is used to quickly look up already computed values. However, this does not scale well
   * with a high integer bound.
   *
   * @param c the retained GCD
   * @param a the number of elements left in the array
   * @param n the number of elements still to be picked
   * @return the desired GCD
   */
  private static int dpGcd(int c, int a, int n) {
    // Termination conditions.
    if (n == 0) {
      return c;
    }
    if (n > a) {
      return -1;
    }
    if (c == 1) {
      return 1;
    }

    if (cache[c][a][n] == 0) {
      // No cache available.
      int head = sa[sa.length - a];
      if (a == 1) {
        // Only one element left.
        if (c == 0) {
          // No GCD constraint.
          cache[c][a][n] = head;
        } else {
          // GCD present.
          cache[c][a][n] = egcd(c, head);
        }
      } else {
        // More than one element left.
        if (c == 0) {
          cache[head][a - 1][n - 1] = dpGcd(head, a - 1, n - 1);
          cache[0][a - 1][n] = dpGcd(0, a - 1, n);
          cache[c][a][n] = Math.max(cache[head][a - 1][n - 1], cache[0][a - 1][n]);
        } else {
          int gh = egcd(c, head);
          cache[gh][a - 1][n - 1] = dpGcd(gh, a - 1, n - 1);
          cache[c][a - 1][n] = dpGcd(c, a - 1, n);
          cache[c][a][n] = Math.max(cache[gh][a - 1][n - 1], cache[c][a - 1][n]);
        }
      }
    }

    return cache[c][a][n];
  }
}
