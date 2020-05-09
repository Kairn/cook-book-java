package io.esoma.cbj.util;

/**
 * Utility class for printing data and texts into the console for debugging or
 * logging purposes.
 * 
 * @author Eddy Soma
 *
 */
public class Printer {

	private static final String TYPE_FAIL = "Printing failed due to unexpected data type.";

	/**
	 * Prints all elements of an array on a single line separated by the specified
	 * delimiter. The elements are expected to be able to be converted into a
	 * string, or the method will fail.
	 * 
	 * @param <T>   the element type
	 * @param array the underlying array
	 * @param deli  the delimiter
	 */
	public static <T> void printArray(T[] array, char deli) {
		try {
			StringBuilder sa = new StringBuilder();

			for (int i = 0; i < array.length; ++i) {
				T t = array[i];
				sa.append(t);
				if (i != array.length - 1) {
					sa.append(deli);
				}
			}

			System.out.println(sa.toString());
		} catch (Exception e) {
			System.out.println(TYPE_FAIL);
		}
	}

	/**
	 * Prints all elements of an integer array on a single line separated by the
	 * specified delimiter.
	 * 
	 * @param array the integer array
	 * @param deli  the delimiter
	 */
	public static void printIntArray(int[] array, char deli) {
		StringBuilder sa = new StringBuilder();

		for (int i = 0; i < array.length; ++i) {
			sa.append(array[i]);
			if (i != array.length - 1) {
				sa.append(deli);
			}
		}

		System.out.println(sa.toString());
	}

}
