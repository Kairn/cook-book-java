package io.esoma.cbj.core;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.esoma.cbj.util.Banner;
import io.esoma.cbj.util.Printer;

public class ArrayCoreTest {

	private static final String START = "Array Core Test Start";
	private static final String END = "Array Core Test End";

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
	public void testLinearSearchInt() throws Exception {
		int[] array = new int[] { 49, 96, 79, 3, 100, 15, 86, 71, 7, 54, 87, 63, 90, 90, 80, 86, 17 };
		int target = 7;
		int expected = 8;
		int actual = ArrayCore.linearSearchInt(array, target, 0, array.length - 1);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testLinearSearchInt2() throws Exception {
		int[] array = new int[] { 94, 60, 63, 83, 87, 51, 8 };
		int target = 18;
		int expected = -1;
		int actual = ArrayCore.linearSearchInt(array, target, 0, array.length - 1);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testLinearSearchInt3() throws Exception {
		int[] array = new int[] { 4119, 2927, 4017, 3787, 2025, 2444, 2358, 3845, 2979, 3085, 4030, 2844, 2237, 3656,
				3581, 3581 };
		int target = 3581;
		int expected = 14;
		int actual = ArrayCore.linearSearchInt(array, target, 0, array.length - 1);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testReverseInt1() throws Exception {
		int[] straight = new int[] { 257, 282, 284, 304, 317, 351, 353, 360, 368, 374, 410, 428, 476, 501, 525, 553,
				568, 582, 603, 621, 624, 654 };
		int[] reverse = new int[] { 654, 624, 621, 603, 582, 568, 553, 525, 501, 476, 428, 410, 374, 368, 360, 353, 351,
				317, 304, 284, 282, 257 };
		int[] result = ArrayCore.reverseInt(straight, 0, straight.length - 1);
		Printer.printIntArray(result, ' ');
		assertArrayEquals(reverse, result);
	}

	@Test
	public void testReverseInt2() throws Exception {
		int[] straight = new int[] { 1, 1, 0, 1, 1 };
		int[] reverse = new int[] { 1, 1, 0, 1, 1 };
		int[] result = ArrayCore.reverseInt(straight, 0, straight.length - 1);
		Printer.printIntArray(result, ' ');
		assertArrayEquals(reverse, result);
	}

	@Test
	public void testReverseInt3() throws Exception {
		int[] straight = new int[] { 2, 7, 8, 8, 4, 4, 4, -3, -3, -4, -5, -6, -6, -2, 0 };
		int[] reverse = new int[] { 2, 7, -6, -6, -5, -4, -3, -3, 4, 4, 4, 8, 8, -2, 0 };
		int[] result = ArrayCore.reverseInt(straight, 2, straight.length - 3);
		Printer.printIntArray(result, ' ');
		assertArrayEquals(reverse, result);
	}

	@Test
	public void testInsertInt1() throws Exception {
		int[] old = new int[] { 5, 8, -9, 0, -7, 3, 3, 1, 0, 2, -6, -9, 7, 9, -3, 2, -1, -6, 1, -8, -5, -8, -6, -4, 1,
				-3 };
		int[] inserted = new int[] { 5, 8, -9, 0, -7, 3, 3, 0, 2, -6, -9, 7, 1, 9, -3, 2, -1, -6, 1, -8, -5, -8, -6, -4,
				1, -3 };
		ArrayCore.insertInt(old, 7, 12);
		Printer.printIntArray(old, ' ');
		assertArrayEquals(old, inserted);
	}

	@Test
	public void testInsertInt2() throws Exception {
		int[] old = new int[] { 645, 842, 953, 709, 120, 93, 685, 813, 399, 341, 540, 574, 920, 283, 45, 942, 474 };
		int[] inserted = new int[] { 645, 842, 953, 942, 709, 120, 93, 685, 813, 399, 341, 540, 574, 920, 283, 45,
				474 };
		ArrayCore.insertInt(old, 15, 3);
		Printer.printIntArray(old, ' ');
		assertArrayEquals(old, inserted);
	}

	@Test
	public void testInsertInt3() throws Exception {
		int[] old = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
		int[] inserted = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
		ArrayCore.insertInt(old, 2, 0);
		Printer.printIntArray(old, ' ');
		assertArrayEquals(old, inserted);
	}

}
