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
	 * Performs the basic binary search but strictly favors finding the leftmost
	 * index of the search target when duplicates exist. Only searches the array
	 * within the bounds specified by the inputs. Regardless of whether the target
	 * value exists in the array, this method returns the leftmost index at which
	 * the target can be inserted so that the array remains sorted.
	 * 
	 * @param array  an integer array of ascending order
	 * @param target the value to search
	 * @param bin    the left bound
	 * @param end    the right bound
	 * @return an imaginary index for the target to be inserted as the leftmost
	 *         element of its duplicates
	 */
	public static int searchIntLeft(int[] array, int target, int bin, int end) {
		return bin;
	}

	/**
	 * Performs the basic binary search but strictly favors finding the rightmost
	 * index of the search target when duplicates exist. Only searches the array
	 * within the bounds specified by the inputs. Regardless of whether the target
	 * value exists in the array, this method returns the rightmost index at which
	 * the target can be inserted so that the array remains sorted.
	 * 
	 * @param array  an integer array of ascending order
	 * @param target the value to search
	 * @param bin    the left bound
	 * @param end    the right bound
	 * @return an imaginary index for the target to be inserted as the rightmost
	 *         element of its duplicates
	 */
	public static int searchIntRight(int[] array, int target, int bin, int end) {
		return end;
	}

}
