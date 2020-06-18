package io.esoma.cbj.algo;

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
		int total = 0;
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
				total += (j + 1) * array[j];
				total %= M;
			}
		}

		return total;
	}

	/**
	 * ?
	 * 
	 * @param array the input array
	 * @return the total of all sorted sums
	 */
	public static int optimalSum(int[] array) {
		return 0;
	}

}
