package io.esoma.cbj.algo;

/**
 * Class for implementing the Heap Sort algorithm. Like selection sort, heapsort divides its input
 * into a sorted and an unsorted region, and it iteratively shrinks the unsorted region by
 * extracting the largest element from it and inserting it into the sorted region. However, heapsort
 * does not waste time with a linear-time scan of the unsorted region; rather, it maintains the
 * unsorted region in a heap data structure to more quickly find the largest element in each step.
 * Heapsort was invented by J. W. J. Williams in 1964.
 *
 * @author Eddy Soma
 */
public class HeapSort {

    private HeapSort() {}

    /**
     * Implements the algorithm by first building a max heap followed by iterative steps of shrinking
     * its size by removing the top node and heapifying the remaining nodes. The array will be sorted
     * once the heap size is 0. The array is sorted in-place.
     *
     * @param array the input array
     * @return the sorted array
     */
    public static int[] sort(int[] array) {
        // Build the max heap from the initial array.
        int size = array.length;
        for (int i = size - 1; i > 0; --i) {
            int ni = array[i];
            // Find the parent node.
            int p = (i - 1) / 2;
            int np = array[p];
            if (ni > np) {
                array[i] = np;
                array[p] = ni;
                siftDown(array, size, i);
            }
        }

        // Repeatedly extracts the largest element and put it at the end of the heap and
        // heapify until the array is fully sorted.
        while (size > 1) {
            // Swap the first and the last element in the heap.
            int max = array[0];
            array[0] = array[size - 1];
            array[size - 1] = max;
            // Reduce the size of the heap and heapify.
            --size;
            heapify(array, size);
        }

        return array;
    }

    /**
     * Performs the sift down procedure from the root node. Assumes the heap is a max heap. Only a
     * partial of the array will be considered as the heap with the given size.
     *
     * @param array the array that contains the heap
     * @param size the size of the heap, only the first n (=size) elements are covered in the heap
     */
    private static void heapify(int[] array, int size) {
        // If the heap size is 0 or 1, it is already in heap structure.
        if (array.length < 2 || size < 2) {
            return;
        }
        siftDown(array, size, 0);
    }

    /**
     * Given a max heap specified by the array and size, starting from a specific node and checks its
     * children nodes and swap them if the parent is less than any of the children. This is repeated
     * until the bottom of the heap is reached.
     *
     * @param array the heap source array
     * @param size the size of the heap
     * @param node the index of the starting node
     */
    private static void siftDown(int[] array, int size, int node) {
        // Find the indices of the children nodes (could be imaginary).
        int il = node * 2 + 1 < size ? node * 2 + 1 : -1;
        int ir = node * 2 + 2 < size ? node * 2 + 2 : -1;

        // Continue only if at least a child exists.
        if (il != -1) {
            int n0 = array[node];
            int nl = array[il];
            if (ir == -1) {
                // Only one left child remains.
                if (n0 < nl) {
                    array[node] = nl;
                    array[il] = n0;
                }
            } else {
                // Both children exist.
                int nr = array[ir];
                if (nl > n0 && nl >= nr) {
                    // Use the left child.
                    array[node] = nl;
                    array[il] = n0;
                    // Repeat with the new child from the original node.
                    siftDown(array, size, il);
                } else if (nr > n0) {
                    // Use the right child.
                    array[node] = nr;
                    array[ir] = n0;
                    siftDown(array, size, ir);
                }
            }
        }
    }
}
