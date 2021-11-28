package io.esoma.cbj.algo;

/**
 * Class for implementing the Quick Sort algorithm. Quicksort is an efficient sorting algorithm
 * developed by British computer scientist Tony Hoare in 1959. Quicksort is a divide-and-conquer
 * algorithm. It works by selecting a 'pivot' element from the array and partitioning the other
 * elements into two sub-arrays, according to whether they are less than or greater than the pivot.
 * The sub-arrays are then sorted recursively. Efficient implementations of Quicksort are not a
 * stable sort.
 *
 * @author Eddy Soma
 */
public class QuickSort {

  /**
   * Implements the original "Hoare partition scheme" where two indices are used check inversions
   * and swap elements in the wrong order with the given pivot. The middle element is selected as
   * pivot to increase efficiency for already sorted arrays. The array is sorted in-place.
   *
   * @param array the input array
   * @return the sorted array
   */
  public static int[] sort(int[] array) {
    process(array, 0, array.length - 1);
    return array;
  }

  /**
   * Performs swaps when inversion is detected with the pivot until the beginning index meets the
   * ending index. Then returns the final index and repeats the process with sub-divisions.
   *
   * @param array the sub-array to process
   * @param bin the beginning index
   * @param end the ending index
   */
  private static void process(int[] array, int bin, int end) {
    // No more work is needed when only one element is present in the partition.
    if (bin == end) {
      return;
    }

    // Choose the pivot and start the inversion swap process.
    int p = array[(end + bin) / 2];
    int lo = bin;
    int hi = end;

    while (lo != hi) {
      int l = array[lo];
      int h = array[hi];

      // Scan for inversions.
      // If only partially found, meaning only one element is on an illegal spot, then
      // move the index on the other end and continue the loop.
      if (l >= p && h <= p) {
        // Inversion is detected and hence swapping the elements.
        array[lo] = h;
        array[hi] = l;
      } else if (l > p) {
        --hi;
        continue;
      } else if (h < p) {
        ++lo;
        continue;
      }

      if (hi - lo == 1) {
        // Two indices meet each other.
        break;
      }

      // Move both indices.
      // Placement is either initially correct or fixed with the swap previously.
      ++lo;
      --hi;
    }

    // Final index is the lower bound when the element is less than pivot.
    // The element on the final index will be included in the right partition if
    // greater than the pivot.
    int f = array[lo] <= p ? lo : lo - 1;

    // Recursively apply the process to sub-arrays with the final index as the
    // dividing line.
    process(array, bin, f);
    process(array, f + 1, end);
  }
}
