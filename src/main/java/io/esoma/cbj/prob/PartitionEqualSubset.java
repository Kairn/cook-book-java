package io.esoma.cbj.prob;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for solving the problem where for a given positive-only integer (also less or equal to 100)
 * array, figure out if it can be partitioned into two subsets (not necessarily sub-arrays, meaning
 * elements don't have to be contiguous) such that their sums are equal.
 */
public class PartitionEqualSubset {

  private PartitionEqualSubset() {}

  /**
   * Determines if the input array can be partitioned into two subsets with equal sums. This
   * algorithm iteratively computes and stores all possible sums that can be derived from subsets of
   * the input array, and the decision is arrived once the target sum (half of the total array sum)
   * is found or the entire array is traversed without seeing the target.
   *
   * @param array the input array
   * @return true if partitioning is possible, or false otherwise
   */
  public static boolean judge(int[] array) {
    int total = Arrays.stream(array).sum();
    if (total % 2 == 1) {
      // Odd total means not possible to partition.
      return false;
    }

    int target = total / 2;
    Set<Integer> sums = new HashSet<>();
    Set<Integer> temp = new HashSet<>();

    for (int num : array) {
      if (num == target) {
        return true;
      }
      temp.add(num);

      for (int sum : sums) {
        int newSum = num + sum;
        if (newSum == target) {
          return true;
        }
        temp.add(newSum);
      }

      sums.addAll(temp);
      temp.clear();
    }

    return false;
  }
}
