package io.esoma.cbj.algo;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.esoma.cbj.util.Banner;

public class KnapsackTest {

	private static final String START = "Knapsack Test Start";
	private static final String END = "Knapsack Test End";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println(Banner.getTitleBanner(START, 3));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println(Banner.getTitleBanner(END, 3));
		System.out.println();
	}

	@Test
	public void testFitKs1() throws Exception {
		int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int limit = 1;
		int expected = 1;
		int actual = Knapsack.fitKs(array, limit);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testFitKs2() throws Exception {
		int[] array = new int[] { 5, 10, 80, 95, 11, 57 };
		int limit = 29;
		int expected = 26;
		int actual = Knapsack.fitKs(array, limit);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testFitKs3() throws Exception {
		int[] array = new int[] { 45, 34, 4, 12, 5, 2 };
		int limit = 42;
		int expected = 41;
		int actual = Knapsack.fitKs(array, limit);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testFitKs4() throws Exception {
		int[] array = new int[] { 3, 34, 4, 12, 5, 2 };
		int limit = 10;
		int expected = 10;
		int actual = Knapsack.fitKs(array, limit);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testFitKs5() throws Exception {
		int[] array = new int[] { 99, 273, 212, 1800, 3740, 112, 256, 788 };
		int limit = 88;
		int expected = 0;
		int actual = Knapsack.fitKs(array, limit);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testFitKs6() throws Exception {
		int[] array = new int[] { 1, 1, 2, 4, 12 };
		int limit = 15;
		int expected = 15;
		int actual = Knapsack.fitKs(array, limit);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testFitKs7() throws Exception {
		int[] array = new int[] { 2350, 734, 54, 312, 115, 224, 77, 895, 9000, 438, 122, 1256, 33, 108 };
		int limit = 5000;
		int expected = 4999;
		int actual = Knapsack.fitKs(array, limit);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testFitKs8() throws Exception {
		int[] array = new int[] { 30, 11, 580, 22, 98, 7, 20, 11, 1, 4, 3, 29, 388, 24 };
		int limit = 8888;
		int expected = 1228;
		int actual = Knapsack.fitKs(array, limit);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

}
