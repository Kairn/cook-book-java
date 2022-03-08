package io.esoma.cbj.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for studying the Knapsack problem which is commonly described as the following statement;
 * given a set of items, each with a weight and a value, determine the number of each item to
 * include in a collection so that the total weight is less than or equal to a given limit and the
 * total value is as large as possible. It derives its name from the problem faced by someone who is
 * constrained by a fixed-size knapsack and must fill it with the most valuable items.
 *
 * @author Eddy Soma
 */
public class Knapsack {

  /**
   * Derives the maximum weight/value that can be fitted into the knapsack of the given size with an
   * array of different items. The algorithm used in this method is called "Meet in the middle"
   * which is a search technique that employs divide and conquer for optimization. It can be
   * effective when the input size is relatively small compare to the weights.
   *
   * @param array the array of items to fit
   * @param limit the size of the knapsack
   * @return the maximum weight for the knapsack
   */
  public static int fitKs(int[] array, int limit) {
    int len = array.length;
    // Divide the array into two sub-arrays of equal/similar size.
    int[] la = Arrays.copyOfRange(array, 0, len / 2);
    int[] ra = Arrays.copyOfRange(array, len / 2, len);

    // Create two collections of all sub-sums from both sub-arrays.
    List<Integer> ls = new ArrayList<>();
    List<Integer> rs = new ArrayList<>();
    collect(la, ls);
    collect(ra, rs);

    // Sort one of the arrays for effective binary search.
    rs.sort(null);

    int result = 0;
    // Loop through the unsorted array and search for the best counterpart in the
    // sorted array.
    for (int l : ls) {
      if (l == limit) {
        return l;
      } else if (l > limit) {
        continue;
      }

      int best = limit - l;
      // Find the best subset sum from the other bag.
      int rb = search(rs, best);
      result = Math.max(result, l + rb);

      if (result == limit) {
        return result;
      }
    }

    return result;
  }

  /**
   * A recursive function to generate the sums from all subsets of the given array.
   *
   * @param array the input array
   * @param sums the collection for storing all the sums
   */
  private static void collect(int[] array, List<Integer> sums) {
    if (array.length == 0) {
      return;
    } else if (array.length == 1) {
      sums.add(array[0]);
      return;
    }

    int cur = array[0];
    // Pass the remaining array into the same method for recursion.
    collect(Arrays.copyOfRange(array, 1, array.length), sums);

    // Now all the sums from a smaller array has been collected.
    List<Integer> ns = new ArrayList<>();
    ns.add(cur);
    for (int s : sums) {
      ns.add(cur + s);
    }

    // Combine two lists.
    sums.addAll(ns);
  }

  /**
   * Searches through the sorted list to find the largest element that does not exceed the given
   * limit. Returns 0 if all elements are larger than the limit.
   *
   * @param list the sorted input list
   * @param limit the limit
   * @return the largest element smaller or equal to the limit, or 0 if none is found
   */
  private static int search(List<Integer> list, int limit) {
    if (list.isEmpty()) {
      return 0;
    }

    int bin = 0;
    int end = list.size() - 1;

    while (end - bin > 1) {
      int mid = (bin + end) / 2;
      int me = list.get(mid);
      if (me == limit) {
        // Found the best possible element.
        return me;
      } else if (me > limit) {
        // Exceeded limit.
        end = mid - 1;
      } else {
        // Acceptable, but a better one may exist.
        bin = mid;
      }
    }

    if (bin == end) {
      return list.get(bin) <= limit ? list.get(bin) : 0;
    } else {
      if (list.get(end) <= limit) {
        return list.get(end);
      } else {
        return list.get(bin) <= limit ? list.get(bin) : 0;
      }
    }
  }

  private Knapsack() {}
}
