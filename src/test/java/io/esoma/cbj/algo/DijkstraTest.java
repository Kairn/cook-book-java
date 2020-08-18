package io.esoma.cbj.algo;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.esoma.cbj.util.Banner;

public class DijkstraTest {

	private static final String START = "Dijkstra Test Start";
	private static final String END = "Dijkstra Test End";

	// Test input.
	private static final int[][] TEST_GRAPH = new int[][] {
			{ 0, 5, -1, 9, -1, -1, -1, 4, -1, -1, -1, -1, -1, -1, 5, -1, -1, 6, -1, 8 },
			{ 5, 0, 2, 3, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 7, -1, -1 },
			{ -1, 2, 0, 9, -1, -1, -1, -1, -1, 6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ 9, 3, 9, 0, -1, 1, -1, 1, -1, 1, -1, 1, -1, -1, -1, -1, -1, -1, 8, -1 },
			{ -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, 4, -1, 1, -1, 0, 4, -1, -1, 4, -1, -1, -1, 2, -1, 8, -1, -1, -1, 3 },
			{ -1, -1, -1, -1, -1, 4, 0, 6, -1, 6, -1, -1, 9, -1, -1, 3, -1, 2, -1, -1 },
			{ 4, -1, -1, 1, -1, -1, 6, 0, 9, 1, -1, 3, -1, -1, 7, -1, 5, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, 9, 0, -1, 2, 4, -1, 6, -1, -1, -1, -1, -1, 5 },
			{ -1, -1, 6, 1, -1, 4, 6, 1, -1, 0, -1, -1, 7, -1, 8, -1, -1, -1, -1, 3 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, 2, -1, 0, 1, -1, 8, -1, -1, 6, -1, -1, -1 },
			{ -1, -1, -1, 1, -1, -1, -1, 3, 4, -1, 1, 0, -1, -1, -1, 6, -1, 5, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, 9, -1, -1, 7, -1, -1, 0, 2, -1, -1, -1, -1, 9, -1 },
			{ -1, -1, -1, -1, -1, 2, -1, -1, 6, -1, 8, -1, 2, 0, -1, -1, -1, -1, -1, -1 },
			{ 5, -1, -1, -1, -1, -1, -1, 7, -1, 8, -1, -1, -1, -1, 0, 1, 1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, 8, 3, -1, -1, -1, -1, 6, -1, -1, 1, 0, -1, 3, -1, 5 },
			{ -1, -1, -1, -1, -1, -1, -1, 5, -1, -1, 6, -1, -1, -1, 1, -1, 0, -1, 2, -1 },
			{ 6, 7, -1, -1, -1, -1, 2, -1, -1, -1, -1, 5, -1, -1, -1, 3, -1, 0, -1, 1 },
			{ -1, -1, -1, 8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 9, -1, -1, 2, -1, 0, -1 },
			{ 8, -1, -1, -1, -1, 3, -1, -1, 5, 3, -1, -1, -1, -1, -1, 5, -1, 1, -1, 0 } };

	// Test result.
	private static int[] result = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println(Banner.getTitleBanner(START, 3));
		// Run test.
		result = Dijkstra.djFind(TEST_GRAPH);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println(Banner.getTitleBanner(END, 3));
		System.out.println();
	}

	@Test
	public void testDjFind1() throws Exception {
		int index = 0;
		System.out.println(result[index]);
		assertEquals(0, result[index]);
	}

	@Test
	public void testDjFind2() throws Exception {
		int index = 1;
		System.out.println(result[index]);
		assertEquals(5, result[index]);
	}

	@Test
	public void testDjFind3() throws Exception {
		int index = 2;
		System.out.println(result[index]);
		assertEquals(7, result[index]);
	}

	@Test
	public void testDjFind4() throws Exception {
		int index = 4;
		System.out.println(result[index]);
		assertEquals(-1, result[index]);
	}

	@Test
	public void testDjFind5() throws Exception {
		int index = 5;
		System.out.println(result[index]);
		assertEquals(6, result[index]);
	}

	@Test
	public void testDjFind6() throws Exception {
		int index = 6;
		System.out.println(result[index]);
		assertEquals(8, result[index]);
	}

	@Test
	public void testDjFind7() throws Exception {
		int index = 7;
		System.out.println(result[index]);
		assertEquals(4, result[index]);
	}

	@Test
	public void testDjFind8() throws Exception {
		int index = 9;
		System.out.println(result[index]);
		assertEquals(5, result[index]);
	}

	@Test
	public void testDjFind9() throws Exception {
		int index = 10;
		System.out.println(result[index]);
		assertEquals(7, result[index]);
	}

	@Test
	public void testDjFind10() throws Exception {
		int index = 12;
		System.out.println(result[index]);
		assertEquals(10, result[index]);
	}

	@Test
	public void testDjFind11() throws Exception {
		int index = 13;
		System.out.println(result[index]);
		assertEquals(8, result[index]);
	}

	@Test
	public void testDjFind12() throws Exception {
		int index = 16;
		System.out.println(result[index]);
		assertEquals(6, result[index]);
	}

	@Test
	public void testDjFind13() throws Exception {
		int index = 17;
		System.out.println(result[index]);
		assertEquals(6, result[index]);
	}

	@Test
	public void testDjFind14() throws Exception {
		int index = 8;
		System.out.println(result[index]);
		assertEquals(9, result[index]);
	}

	@Test
	public void testDjFind15() throws Exception {
		int index = 19;
		System.out.println(result[index]);
		assertEquals(7, result[index]);
	}

}
