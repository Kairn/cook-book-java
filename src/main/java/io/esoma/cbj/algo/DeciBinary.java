package io.esoma.cbj.algo;

/**
 * Class for studying the Decibinary problem. The Decibinary system is a number
 * system such that each digit ranges from 0 to 9 (like the decimal number
 * system), but the place value of each digit corresponds to the one in the
 * binary number system. For example, the decibinary number 2016 represents the
 * decimal number 24 (2 * 8 + 0 * 4 + 1 * 2 * 6 * 1). Decibinary numbers are
 * first ordered by their decimal values. Any two decibinary numbers that
 * evaluate to the same decimal value are ordered by increasing decimal value.
 * Decibinary number 2 is ordered before decibinary number 10 because they have
 * the same decimal value, but 2 is less than 10 in the decimal system. The goal
 * is to find a way to convert a number in the decibinary system into its
 * decimal representation. For example, "7" in the decibinary system corresponds
 * to decibinary number 4 since it is the seventh number in the decibinary
 * system according to the rules.
 * 
 * @author Eddy Soma
 *
 */
public class DeciBinary {

	// Bases for the Binary and Decimal
	public static final int B2 = 2;
	public static final int B10 = 10;

	/**
	 * Computes the decimal value of a decibinary number in its decimal
	 * representation.
	 * 
	 * @param dbNum the decibinary number
	 * @return the decimal value
	 */
	public static int deciValue(int dbNum) {
		int value = 0;
		int sp = 1;

		while (dbNum > 0) {
			int pv = (new Double(Math.pow((double) B2, (double) (sp - 1)))).intValue();
			int dv = B10;
			// Increment the modulo at each position multiplied by their place value.
			value += (dbNum % dv) * pv;
			dbNum /= dv;
			++sp;
		}

		return value;
	}

	/**
	 * Converts a number in the decibinary system into its decimal representation.
	 * In other words, if the input is n, the method will returned the nth number in
	 * the decibinary system in its decimal format.
	 * 
	 * @param db the 1-based index in the decibinary system
	 * @return the corresponding decibinary number
	 */
	public static long deciFromDb(long db) {
		return 0L;
	}

}
