package io.esoma.cbj.core;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.esoma.cbj.util.Banner;

public class BinarySearchTest {

	private static final String START = "Binary Search Test Start";
	private static final String END = "Binary Search Test End";

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
	public void testSearchInt1() throws Exception {
		int[] sorted = new int[] { 4, 5, 8, 10, 13, 15, 16, 19, 20, 31, 33, 41, 45, 48, 57, 59, 69, 71, 77, 82, 84, 87,
				88, 89, 91, 92 };
		int target = 84;
		int expected = 20;
		int actual = BinarySearch.searchInt(sorted, target);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchInt2() throws Exception {
		int[] sorted = new int[] { 1, 11, 22, 23, 25, 28, 32, 34, 42, 44, 61, 64, 67, 72, 74, 79, 80, 85, 98 };
		int target = 11;
		int expected = 1;
		int actual = BinarySearch.searchInt(sorted, target);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchInt3() throws Exception {
		int[] sorted = new int[] { -1, 2, 5, 7, 11, 13, 17, 20 };
		int target = 2;
		int expected = 1;
		int actual = BinarySearch.searchInt(sorted, target);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchInt4() throws Exception {
		int[] sorted = new int[] { 3, 9, 17, 18, 30, 35, 40, 54, 63, 83, 86, 93, 94, 96, 97 };
		int target = 97;
		int expected = 14;
		int actual = BinarySearch.searchInt(sorted, target);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchInt5() throws Exception {
		int[] sorted = new int[] { 3 };
		int target = 0;
		int expected = -1;
		int actual = BinarySearch.searchInt(sorted, target);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchIntLeft1() throws Exception {
		int[] sorted = new int[] { 5, 5, 5, 5, 5, 6, 6, 6, 7, 7, 8, 8, 9, 9, 9 };
		int target = 5;
		int expected = 0;
		int actual = BinarySearch.searchIntLeft(sorted, target, 0, sorted.length - 1);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchIntLeft2() throws Exception {
		int[] sorted = new int[] { 12, 12, 14, 22, 25, 29, 33, 34, 37, 38, 40, 43 };
		int target = 34;
		int expected = 7;
		int actual = BinarySearch.searchIntLeft(sorted, target, 0, sorted.length - 1);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchIntLeft3() throws Exception {
		int[] sorted = new int[] { -10, -10, -9, -9, -9, -8, -8, -8, -7, -7, -7, -6, -5, -5, -5, -4, -3, -2, 0, 0, 1 };
		int target = 0;
		int expected = 18;
		int actual = BinarySearch.searchIntLeft(sorted, target, 0, sorted.length - 1);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchIntLeft4() throws Exception {
		int[] sorted = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int target = 99;
		int expected = 10;
		int actual = BinarySearch.searchIntLeft(sorted, target, 0, sorted.length - 1);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchIntLeft5() throws Exception {
		int[] sorted = new int[] { -4, -1, 5, 14, 21, 29, 32, 36, 45, 46, 49, 53, 54, 54, 55, 55, 56, 58 };
		int target = 55;
		int expected = 14;
		int actual = BinarySearch.searchIntLeft(sorted, target, 0, sorted.length - 1);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchIntRight1() throws Exception {
		int[] sorted = new int[] { -7, -4, 2, 4, 7, 7, 8, 9, 21, 27, 27, 31, 33, 33, 37, 43, 44, 46, 48, 48, 54, 56, 56,
				56, 59, 60 };
		int target = 27;
		int expected = 11;
		int actual = BinarySearch.searchIntRight(sorted, target, 0, sorted.length - 1);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchIntRight2() throws Exception {
		int[] sorted = new int[] { -7, 6, 9, 23, 29, 33, 33, 37, 37, 43, 46, 48, 54, 56, 60 };
		int target = -7;
		int expected = 1;
		int actual = BinarySearch.searchIntRight(sorted, target, 0, sorted.length - 1);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchIntRight3() throws Exception {
		int[] sorted = new int[] { 890, 890, 891, 892, 899, 904, 906, 906, 907, 908, 908, 914, 914 };
		int target = 998;
		int expected = 13;
		int actual = BinarySearch.searchIntRight(sorted, target, 0, sorted.length - 1);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchIntRight4() throws Exception {
		int[] sorted = new int[] { 5 };
		int target = 0;
		int expected = 0;
		int actual = BinarySearch.searchIntRight(sorted, target, 0, sorted.length - 1);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testSearchIntRight5() throws Exception {
		int[] sorted = new int[] { 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 7, 7, 7, 8,
				9, 9 };
		int target = 4;
		int expected = 17;
		int actual = BinarySearch.searchIntRight(sorted, target, 0, sorted.length - 1);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

}
