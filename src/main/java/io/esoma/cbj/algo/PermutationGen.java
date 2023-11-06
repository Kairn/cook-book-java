package io.esoma.cbj.algo;

import io.esoma.cbj.core.ArrayCore;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Class that implements a permutation generator for integers from 1 to n. Permutations are returned
 * in lexicographical order until exhausted.
 */
public class PermutationGen {

    private final int[] state;

    private boolean ended;

    /**
     * Initializes the generator with the given upper bound n.
     *
     * @param n any int larger than 0
     */
    public PermutationGen(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be greater or equal to 1");
        }

        state = IntStream.iterate(1, i -> i + 1).limit(n).toArray();
        ended = false;
    }

    /**
     * Returns if there are more permutations to be generated given the current state.
     *
     * @return true if more permutations can be generated, false otherwise
     */
    public boolean hasNext() {
        return !ended;
    }

    /**
     * Generates the next permutation (lexicographical incrementation from the previous return
     * value) given the current state. The first call simply returns an array of 1 to n sorted in
     * ascending order.
     *
     * @return an array representing the next permutation
     */
    public int[] getNext() {
        if (ended) {
            throw new IllegalStateException("No more permutations to be generated");
        }

        int[] result = Arrays.copyOf(state, state.length);

        // Find the first decrease starting from the end.
        int pivotIndex = -1;
        int prev = Integer.MIN_VALUE;
        for (int i = state.length - 1; i >= 0; --i) {
            int cur = state[i];
            if (cur < prev) {
                pivotIndex = i;
                break;
            }
            prev = cur;
        }

        // State is strictly descending indicating it's the last permutation.
        if (pivotIndex == -1) {
            ended = true;
            return result;
        }

        // Revert the tail to ascending order.
        ArrayCore.reverseInt(state, pivotIndex + 1, state.length - 1);

        // Find the smallest number greater than the pivot to swap the pivot with.
        int pivot = state[pivotIndex];
        int swapIndex = Arrays.binarySearch(state, pivotIndex + 1, state.length, pivot + 1);
        if (swapIndex < 0) {
            swapIndex = (swapIndex + 1) * -1;
        }

        state[pivotIndex] = state[swapIndex];
        state[swapIndex] = pivot;

        return result;
    }
}
