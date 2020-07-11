package io.esoma.cbj.algo;

/**
 * Class for studying the Greatest Common Divisor (GCD) problem. The GCD is
 * defined to be the largest positive integer that fully divides each of the
 * integer in a group of non-zero integers. For example, the GCD of 5, 10, 15 is
 * 5.
 * 
 * @author Eddy Soma
 *
 */
public class GreatestCommonDivisor {

	/**
	 * Finds the GCD of the given integer array.
	 * 
	 * @param array the input array
	 * @return the GCD value
	 */
	public static int findGcd(int[] array) {
		int len = array.length;
		if (len < 1) {
			return 0;
		}

		return 1;
	}

	/**
	 * With the given integer array, finds the largest possible GCD that can be
	 * obtained by forming a group of n integers individually picked (without
	 * replacement) from the array. For example, if we have integers 4, 5, 10, 15,
	 * 6, 12, and n is 3, then we should pick 5, 10, 15 whose GCD is the largest (5)
	 * amongst all combinations.
	 * 
	 * @param array the input array
	 * @param n     the number of integers to select
	 * @return the GCD of the chosen group
	 */
	public static int bestGcd(int[] array, int n) {
		int len = array.length;
		if (len < 1) {
			return 0;
		}
		if (n > len) {
			n = len;
		}

		return 1;
	}
}
