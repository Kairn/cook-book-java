package io.esoma.cbj.core;

/**
 * Class for implementing the binary search algorithm. Binary search is a search
 * algorithm that finds the position of a target value within a sorted array.
 * Binary search compares the target value to the middle element of the array.
 * If they are not equal, the half in which the target cannot lie is eliminated
 * and the search continues on the remaining half, again taking the middle
 * element to compare to the target value, and repeating this until the target
 * value is found. If the search ends with the remaining half being empty, the
 * target is not in the array.
 * 
 * @author Eddy Soma
 *
 */
public class BinarySearch {

	/**
	 * Performs the basic binary search and returns the index of the search target
	 * in a sorted array. If not found, -1 is returned. The return value is not
	 * guaranteed to be the leftmost or rightmost index if multiple targets exist.
	 * 
	 * @param array  the integer array to search, ascending order is assumed
	 * @param target the value to search
	 * @return the target's index or -1 if not existent
	 */
	public static int searchInt(int[] array, int target) {
		return -1;
	}

	/**
	 * Performs the basic binary search but instead of returning -1 when a value is
	 * not found, the appropriate index for inserting the value into the array so
	 * that the array remains sorted is returned. This is leveraged in the binary
	 * insertion sort.
	 * 
	 * @param array  the integer array to search, ascending order is assumed
	 * @param target the value to search
	 * @return the index for inserting the target into the array after which it
	 *         remains sorted
	 */
	public static int getIndexInt(int[] array, int target) {
		return 0;
	}
}
