package io.esoma.cbj.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that solves the problem of counting the total number of sub-arrays (of a given array of
 * integers) whose sums are equal to a preset number k. Each subarray must be non-empty.
 */
public class SubarraySumEqualsK {

    private SubarraySumEqualsK() {}

    /**
     * Counts the number of sub-arrays whose sums are equal to k. This implementation uses a
     * prefix-sum hash map (key being the sum, and value being the count) that is built interactively
     * with the main iteration to quickly compute if any sub-array fulfills the condition.
     *
     * @param array the input array
     * @param k the target sum
     * @return the total number of sub-arrays that sum up to k
     */
    public static int count(int[] array, int k) {
        int result = 0;

        Map<Integer, Integer> prefixSums = new HashMap<>();

        int curSum = 0;
        // Add the empty sub-array.
        prefixSums.put(curSum, 1);

        for (int cur : array) {
            curSum += cur;

            int curTarget = curSum - k;
            if (prefixSums.containsKey(curTarget)) {
                result += prefixSums.get(curTarget);
            }

            prefixSums.compute(curSum, (key, value) -> value == null ? 1 : value + 1);
        }

        return result;
    }
}
