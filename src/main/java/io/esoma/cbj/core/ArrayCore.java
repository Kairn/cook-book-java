package io.esoma.cbj.core;

/**
 * Utility class for providing array helper methods to support other major
 * algorithms and operations.
 * 
 * @author Eddy Soma
 *
 */
public class ArrayCore {

	/**
	 * Iterates through the integer array and compares each element with the target
	 * until it finds a match or reaches the end of the array. Will only search
	 * between the specified bounds.
	 * 
	 * @param array  the array to search
	 * @param target the value to search
	 * @param bin    the left bound
	 * @param end    the right bound
	 * @return the index of the first occurrence of the target in the array, or -1
	 *         if none if found
	 */
	public static int linearSearchInt(int[] array, int target, int bin, int end) {
		for (int i = bin; i <= end; ++i) {
			if (array[i] == target) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Reverses the integer array. Reversal is done in the obvious pair swapping
	 * fashion and is in-place. However, a reference of the array is still returned.
	 * Elements outside of the specified bounds will not be affected.
	 * 
	 * @param array the array to reverse
	 * @param bin   the left bound
	 * @param end   the right bound
	 * @return the array after reversal
	 */
	public static int[] reverseInt(int[] array, int bin, int end) {
		while (end > bin) {
			int ib = array[bin];
			array[bin] = array[end];
			array[end] = ib;
			++bin;
			--end;
		}

		return array;
	}

	/**
	 * Takes the element at the source position and inserts it at the target
	 * position. Moves all elements in between one index left or right depending on
	 * the direction. (Insertion is rather expensive for an array.)
	 * 
	 * @param array  the integer array
	 * @param source the index of the element to move
	 * @param target the new index for the element
	 */
	public static void insertInt(int[] array, int source, int target) {
		if (source == target) {
			// No work needed.
			return;
		}

		int is = array[source];
		if (source > target) {
			// Need to shift elements to the right.
			for (int i = source - 1; i > target - 1; --i) {
				array[i + 1] = array[i];
			}
		} else {
			// Need to shift elements to the left.
			for (int i = source + 1; i < target + 1; ++i) {
				array[i - 1] = array[i];
			}
		}

		array[target] = is;
	}

	/**
	 * Copies the elements within the bounds of the original array into a new array
	 * of that exact length.
	 * 
	 * @param array the source array
	 * @param bin   the left bound
	 * @param end   the right bound
	 * @return the copied array, an empty array will be return for invalid range
	 */
	public static int[] copyInt(int[] array, int bin, int end) {
		if (end < bin) {
			return new int[0];
		}

		int len = end - bin + 1;
		int[] copy = new int[len];

		for (int i = 0; i < len; ++i) {
			copy[i] = array[bin + i];
		}

		return copy;
	}

	/**
	 * Checks if the given integer array of the specified range is in ascending
	 * (non-descending) order. False is returned if the array/range is empty.
	 * 
	 * @param array the input array
	 * @param bin   the left bound
	 * @param end   the right bound
	 * @return
	 */
	public static boolean checkIntAsc(int[] array, int bin, int end) {
		if (end < bin || array.length < 1) {
			return false;
		}

		for (int i = bin + 1; i <= end; ++i) {
			if (array[i] < array[i - 1]) {
				return false;
			}
		}

		return true;
	}

}
