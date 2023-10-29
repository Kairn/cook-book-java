package io.esoma.cbj.algo;

/**
 * Class for implementing the Quickselect algorithm. The algorithm is designed to find the kth
 * smallest element in an unordered array efficiently. This approach has various similarities to the
 * Quicksort algorithm.
 */
public class QuickSelect {

    private QuickSelect() {}

    /**
     * Finds and returns the kth smallest element in an integer array. This is a divide and conquer
     * implementation that has optimal average case runtime. Exception is thrown if inputs are
     * invalid.
     *
     * @param array the input array
     * @param k the position of the element to return when sorted, must not be larger than the length
     *     of the input array
     * @return the kth smallest element
     */
    public static int findKthSmallest(int[] array, int k) {
        if (k <= 0 || k > array.length) {
            throw new IllegalArgumentException("Invalid k");
        }
        return searchKth(array, 0, array.length, k);
    }

    private static int searchKth(int[] array, int start, int end, int k) {
        int len = end - start;
        if (len == 1) {
            return array[start];
        } else if (len == 2) {
            if (k == start + 1) {
                return Math.min(array[start], array[start + 1]);
            } else {
                return Math.max(array[start], array[start + 1]);
            }
        }

        int pivot = array[end - 1];
        int i = start;
        int j = end - 1;

        while (i < j) {
            int ai = array[i];
            int aj = array[j];
            if (ai > pivot && aj <= pivot) {
                array[i] = aj;
                array[j] = ai;
                ++i;
                --j;
            } else if (ai > pivot) {
                --j;
            } else if (aj <= pivot) {
                ++i;
            } else {
                ++i;
                --j;
            }
        }

        // Move i to the starting index of the right partition.
        if (array[i] < pivot) {
            ++i;
        }

        if (k < i + 1) {
            // Answer is on the left.
            return searchKth(array, start, i, k);
        } else {
            return searchKth(array, i, end, k);
        }
    }
}
