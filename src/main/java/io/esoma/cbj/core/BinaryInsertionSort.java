package io.esoma.cbj.core;

/**
 * Class for implementing the binary insertion sort algorithm. Insertion sort is a simple sorting
 * algorithm that builds the final sorted array one item at a time. It iterates, consuming one input
 * element each repetition, and growing a sorted output list. At each iteration, insertion sort
 * removes one element from the input data, finds the location it belongs within the sorted list,
 * and inserts it there. It repeats until no input elements remain. Binary insertion sort employs a
 * binary search to determine the correct location to insert new elements. It is efficient for small
 * data sets and thus typically leveraged in more complex or hybrid sorting algorithms.
 *
 * @author Eddy Soma
 */
public class BinaryInsertionSort {

    private BinaryInsertionSort() {}

    /**
     * Performs in-place insertion of elements starting from the next unsorted index given to the
     * method. It will not waste time sorting the entire region specified by the bounds if there is a
     * sorted left portion.
     *
     * @param array the source array to perform sorting
     * @param bin the left bound
     * @param end the right bound
     * @param next the first unsorted index, anything between it and the left bound is assumed to be
     *     sorted
     */
    public static void sortOnline(int[] array, int bin, int end, int next) {
        // The first element is obviously sorted.
        if (next <= bin) {
            next = bin + 1;
        }

        // Looping through all elements that need to be inserted (sorted).
        for (int i = next; i <= end; ++i) {
            int it = array[i];

            // Perform binary search to find the insertion spot.
            // To maintain stability, we insert at the rightmost index.
            int target = BinarySearch.searchIntRight(array, it, bin, i - 1);

            // Insert the element into the sorted region.
            ArrayCore.insertInt(array, i, target);
        }
    }
}
