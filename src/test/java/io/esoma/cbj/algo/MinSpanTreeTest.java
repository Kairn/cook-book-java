package io.esoma.cbj.algo;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.esoma.cbj.util.Banner;

public class MinSpanTreeTest {

	private static final String START = "MST Test Start";
	private static final String END = "MST Test End";

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
	public void testCalcKruskal1() throws Exception {
		int n = 5;
		int[][] edges = new int[][] { { 1, 2, 3 }, { 1, 3, 4 }, { 4, 2, 6 }, { 5, 2, 2 }, { 2, 3, 5 }, { 3, 5, 7 } };
		int expected = 15;
		int actual = MinSpanTree.calcKruskal(n, edges);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testCalcKruskal2() throws Exception {
		int n = 4;
		int[][] edges = new int[][] { { 1, 2, 5 }, { 1, 3, 3 }, { 4, 1, 6 }, { 2, 4, 7 }, { 3, 2, 4 }, { 3, 4, 5 } };
		int expected = 12;
		int actual = MinSpanTree.calcKruskal(n, edges);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testCalcKruskal3() throws Exception {
		int n = 5;
		int[][] edges = new int[][] { { 1, 2, 20 }, { 1, 3, 50 }, { 1, 4, 70 }, { 1, 5, 90 }, { 2, 3, 30 },
				{ 3, 4, 40 }, { 4, 5, 60 } };
		int expected = 150;
		int actual = MinSpanTree.calcKruskal(n, edges);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testCalcKruskal4() throws Exception {
		int n = 10;
		int[][] edges = new int[][] { { 1, 2, 234 }, { 1, 9, 17 }, { 8, 4, 60 }, { 3, 6, 41 }, { 3, 10, 178 },
				{ 2, 6, 38 }, { 1, 8, 99 }, { 4, 7, 54 }, { 5, 7, 29 }, { 1, 5, 160 }, { 2, 10, 280 }, { 4, 6, 18 },
				{ 5, 6, 33 }, { 7, 9, 50 }, { 2, 3, 10 }, { 6, 10, 82 }, { 2, 5, 70 }, { 4, 10, 199 }, { 8, 9, 6 },
				{ 3, 5, 22 } };
		int expected = 267;
		int actual = MinSpanTree.calcKruskal(n, edges);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

}
