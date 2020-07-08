package io.esoma.cbj.core;

import io.esoma.cbj.algo.DeciBinary;
import io.esoma.cbj.util.Banner;

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
		int start = 0;
		int end = 10010;
		int step = 10;
		for (int i = start; i < end; i += step) {
			int mid = i + 10;
			System.out.println(Banner.getSpecialBanner(i + "_" + (mid - 1), 5));
			for (int j = i; j < mid; ++j) {
				System.out.print(DeciBinary.deciValue(j) + " ");
			}
			System.out.println();
		}
	}

}
