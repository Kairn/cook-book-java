package io.esoma.cbj.core;

import io.esoma.cbj.algo.SortedSum;

/**
 * Dummy class for testing the project setup. No practical use. It contains a
 * driver "Main" method for testing static methods.
 * 
 * @author Eddy Soma
 *
 */
public class HelloWorld {

	/**
	 * Tests the project build flow and JUnit connectivity by printing out a test
	 * string to the debug console and returning the same for the unit test.
	 * 
	 * @return a test string
	 */
	public static String runTest() {
		String test = "Hello world from Cook Book Java.";
		System.out.println("Hello, World!");
		return test;
	}

	public static void main(String[] args) {
		int[] array = new int[] { 1 };
		System.out.println(SortedSum.naiveInsertion(array));
	}

}
