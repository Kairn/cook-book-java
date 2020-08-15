package io.esoma.cbj.algo;

/**
 * Class for studying the Knapsack problem which is commonly described as the
 * following statement; given a set of items, each with a weight and a value,
 * determine the number of each item to include in a collection so that the
 * total weight is less than or equal to a given limit and the total value is as
 * large as possible. It derives its name from the problem faced by someone who
 * is constrained by a fixed-size knapsack and must fill it with the most
 * valuable items.
 * 
 * @author Eddy Soma
 *
 */
public class Knapsack {

	/**
	 * Derives the maximum weight/value that can be fitted into the knapsack of the
	 * given size with an array of different items. The algorithm used in this
	 * method is called "Meet in the middle" which is a search technique that
	 * employs divide and conquer for optimization. It can be effective when the
	 * input size is relatively small compare to the weights.
	 * 
	 * @param array the array of items to fit
	 * @param limit the size of the knapsack
	 * @return the maximum weight for the knapsack
	 */
	public static int fitKs(int[] array, int limit) {
		return 0;
	}
}
