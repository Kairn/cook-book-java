package io.esoma.cbj.algo;

/**
 * Class for implementing the Merge Sort algorithm. Merge sort is an efficient, general-purpose,
 * comparison-based sorting algorithm. Most implementations produce a stable sort. Merge sort is a
 * divide and conquer algorithm that was invented by John von Neumann in 1945.
 *
 * @author Eddy Soma
 */
public class MergeSort {

  private MergeSort() {}

  /**
   * Performs sorting on an initially unsorted array. Order is ascending.
   *
   * @param array the input array
   * @return the sorted array
   */
  public static int[] sort(int[] array) {
    if (array == null || array.length == 0) {
      return new int[0];
    }

    return process(array, 0, array.length - 1);
  }

  /**
   * Divides the array and processes sorting after. It is used internally for the recursive call.
   *
   * @param array the array to divide and conquer
   * @param bin the beginning index
   * @param end the ending index
   * @return the processed (sorted) array
   */
  private static int[] process(int[] array, int bin, int end) {
    int len = end - bin + 1;

    // If sub-array has length of 1, then return it.
    if (len == 1) {
      return new int[] {array[bin]};
    }

    // Divide the array into equal (left part with one extra element if length is
    // odd) sized partitions.
    // Find the middle index.
    int mid = (end + bin) / 2;
    // Recursively invoke this process until all sub-arrays have only one element.
    int[] left = process(array, bin, mid);
    int[] right = process(array, mid + 1, end);

    // Merge the left and right sub-arrays given they were already sorted.
    int[] sorted = new int[len];
    // All indices for tracking sub-arrays
    int si = 0;
    int li = 0;
    int le = left.length - 1;
    int ri = 0;
    int re = right.length - 1;

    // Load left or right element one by one by doing comparisons repeatedly.
    while (li <= le || ri <= re) {
      if (li <= le && ri <= re) {
        if (left[li] <= right[ri]) {
          sorted[si] = left[li];
          ++li;
        } else {
          sorted[si] = right[ri];
          ++ri;
        }
      } else if (li <= le) {
        sorted[si] = left[li];
        ++li;
      } else {
        sorted[si] = right[ri];
        ++ri;
      }
      ++si;
    }

    return sorted;
  }
}
