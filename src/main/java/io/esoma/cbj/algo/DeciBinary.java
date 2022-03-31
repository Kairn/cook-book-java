package io.esoma.cbj.algo;

/**
 * Class for studying the Decibinary problem. The Decibinary system is a number system such that
 * each digit ranges from 0 to 9 (like the decimal number system), but the place value of each digit
 * corresponds to the one in the binary number system. For example, the decibinary number 2016
 * represents the decimal number 24 (2 * 8 + 0 * 4 + 1 * 2 * 6 * 1). Decibinary numbers are first
 * ordered by their decimal values. Any two decibinary numbers that evaluate to the same decimal
 * value are ordered by increasing decimal value. Decibinary number 2 is ordered before decibinary
 * number 10 because they have the same decimal value, but 2 is less than 10 in the decimal system.
 * The goal is to find a way to convert a number in the decibinary system into its decimal
 * representation. For example, "7" in the decibinary system corresponds to decibinary number 4
 * since it is the seventh number in the decibinary system according to the rules.
 *
 * @author Eddy Soma
 */
public class DeciBinary {

  // Bases for the Binary and Decimal.
  public static final int B2 = 2;
  public static final int B10 = 10;

  // Bounds for the algorithm.
  private static final int MAX_DIGITS = 21;
  private static final int MAX_DECI = 300001;

  // Cache arrays for the algorithm.
  private static final long[][] COUNTS = new long[MAX_DIGITS][MAX_DECI];
  private static final long[] PRE_COUNTS = new long[MAX_DECI];

  private DeciBinary() {}

  /**
   * Computes the decimal value of a decibinary number in its decimal representation.
   *
   * @param dbNum the decibinary number
   * @return the decimal value
   */
  public static int deciValue(int dbNum) {
    int value = 0;
    int sp = 1;

    while (dbNum > 0) {
      int pv = (Double.valueOf(Math.pow(B2, sp - 1.0))).intValue();
      int dv = B10;
      // Increment the modulo at each position multiplied by their place value.
      value += (dbNum % dv) * pv;
      dbNum /= dv;
      ++sp;
    }

    return value;
  }

  /**
   * Converts a number in the decibinary system into its decimal representation. In other words, if
   * the input is n, the method will returned the nth number in the decibinary system in its decimal
   * format. Implementation uses dynamic programming to search for the decimal value of the target
   * integer, and then computes each digit efficiently. This method is limited by predefined bounds
   * and will not work when the target is too large without increasing the bounds.
   *
   * @param db the 1-based index in the decibinary system
   * @return the corresponding decibinary number
   */
  public static long deciFromDb(long db) {
    if (db == 1L) {
      return 0L;
    }

    // Binary search for the target decimal value.
    int d = -1;
    int s = -1;
    int bin = 0;
    int end = PRE_COUNTS.length - 1;
    while (bin != end) {
      int mid = (bin + end) / 2;
      long cnt = PRE_COUNTS[mid];
      if (cnt > db) {
        end = mid;
      } else if (cnt == db) {
        s = mid;
        break;
      } else {
        bin = mid + 1;
      }
    }
    if (s == -1) {
      s = bin;
    }

    // Calculate how many digits are needed for the desired decimal value.
    long g = db - PRE_COUNTS[s - 1];
    while (count(d, s) < g) {
      ++d;
    }

    // Construct the final decibinary integer.
    StringBuilder sb = new StringBuilder();
    long cur;
    while (d > -1) {
      // Current count of integers.
      cur = 0;
      for (int i = 0; i < 10; ++i) {
        if (s >= i * (1 << d)) {
          cur += count(d - 1, s - i * (1 << d));
        }
        // Check if the count has exceeded the requirement.
        if (cur >= g) {
          sb.append(i);
          g -= cur - count(d - 1, s - i * (1 << d));
          s -= i * (1 << d);
          break;
        }
      }
      --d;
    }

    return Long.parseLong(sb.toString());
  }

  /** Initializes the cache arrays. */
  static void initCache() {
    // Reset all values to -1.
    for (int i = 0; i < MAX_DIGITS; ++i) {
      for (int j = 0; j < MAX_DECI; ++j) {
        COUNTS[i][j] = -1;
      }
    }

    // Get all counts for the given bounds.
    for (int i = 0; i < MAX_DECI; ++i) {
      PRE_COUNTS[i] = count(MAX_DIGITS - 1, i);
    }

    // Compute the prefix sum of all counts.
    for (int i = 1; i < MAX_DECI; ++i) {
      PRE_COUNTS[i] += PRE_COUNTS[i - 1];
    }
  }

  /**
   * Computes how many decibinary numbers can have a particular decimal value up to a certain number
   * of digits. The method is recursive and is fundamental to the DP nature of the main algorithm.
   * With a reasonable bound, it efficiently finds the correct number of decibinary integers without
   * resorting to enumeration.
   *
   * @param d the maximum number of digits
   * @param s the desired decimal value
   * @return the number of decibinary integers
   */
  private static long count(int d, int s) {
    // Termination conditions.
    if (s < 0) {
      return 0L;
    } else if (d == -1) {
      if (s == 0) {
        return 1L;
      } else {
        return 0L;
      }
    } else if (COUNTS[d][s] == -1) {
      // Recur by summing up results from placing 0-9 on the dth digit.
      // Cache the values for further use.
      COUNTS[d][s] = 0;
      for (int i = 0; i < 10 && s >= i * (1 << d); ++i) {
        COUNTS[d][s] += count(d - 1, s - i * (1 << d));
      }
    }

    return COUNTS[d][s];
  }
}
