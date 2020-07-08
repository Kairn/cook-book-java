package io.esoma.cbj.algo;

import java.util.Arrays;

import io.esoma.cbj.core.ArrayCore;
import io.esoma.cbj.core.BinarySearch;
import io.esoma.cbj.util.Banner;
import io.esoma.cbj.util.Printer;

/**
 * Class for studying the Sorted Sum problem. The task is to efficiently compute
 * the sum of the first i elements multiplied by its index (1 based) after
 * sorting. The function should return the total of such weighted sums for all i
 * (1 <= i <= n). A naive algorithm will simply sort every prefix array, compute
 * the weighted sum, and add it to the total value. Typically, a modulo
 * operation will be performed on the total when the potential value exceeds the
 * maximum of a 32-bit number.
 * 
 * @author Eddy Soma
 *
 */
public class SortedSum {

	// The divisor to be used on the modulo operation.
	private static final int M = 1000000007;

	/**
	 * Process the input array from left to right in a way similar to an insertion
	 * sort. For each element, traverse through the sorted area, adding to the sum
	 * by comparison, and insert the element into the sorted region.
	 * 
	 * @param array the input array
	 * @return the total of all sorted sums
	 */
	public static int naiveInsertion(int[] array) {
		long total = 0;
		int l = array.length;

		// Going through the array.
		for (int i = 0; i < l; ++i) {
			int cur = array[i];
			// Flag to be set true when the current element is inserted.
			boolean inserted = false;
			for (int j = i; j > -1; --j) {
				if (!inserted) {
					// Check if there is elements to compare.
					if (j - 1 > -1) {
						int pre = array[j - 1];
						if (pre > cur) {
							array[j] = pre;
						} else {
							array[j] = cur;
							inserted = true;
						}
					} else {
						// Reached first index.
						array[0] = cur;
					}
				}
				// Add the weight to the total.
				total += (j + 1) * (long) array[j];
				total %= M;
			}
		}

		return (new Long(total)).intValue();
	}

	/**
	 * ?
	 * 
	 * @param array the input array
	 * @return the total of all sorted sums
	 */
	public static int optimalSum(int[] array) {
		final int len = array.length;
		if (len == 0) {
			return 0;
		}

		// Load the first element and setting up.
		int[] sorted = new int[len];
		int[] prefixSum = new int[len];
		int sum = array[0];
		int end = 0;
		int prev = array[0];
		sorted[0] = array[0];
		prefixSum[0] = array[0];

		// The cycle for collecting new elements and computing the updated sum.
		for (int i = 1; i < len; ++i) {
			int e = array[i];
			// Use binary search to find the insertion index.
			int ni = BinarySearch.searchIntRight(sorted, e, 0, end);
			int cul = ni == 0 ? prefixSum[end] : prefixSum[end] - prefixSum[ni - 1];
			prev += e * (ni + 1) + cul;
			prev %= M;
			sum += prev;
			sum %= M;

			if (i < len - 1) {
				// Shift elements to update the sorted array.
				for (int j = end; j >= ni; --j) {
					sorted[j + 1] = sorted[j];
					prefixSum[j + 1] = prefixSum[j] + e;
				}
				sorted[ni] = e;
				prefixSum[ni] = e + (ni == 0 ? 0 : prefixSum[ni - 1]);
//				System.out.println("---");
//				System.out.println(ArrayCore.getString(sorted, ", "));
//				System.out.println(ArrayCore.getString(prefixSum, ", "));
//				System.out.println(sum);
				++end;
			}
		}

		return sum;
	}

}
