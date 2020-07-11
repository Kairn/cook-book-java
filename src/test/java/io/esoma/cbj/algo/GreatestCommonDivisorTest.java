package io.esoma.cbj.algo;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.esoma.cbj.util.Banner;

public class GreatestCommonDivisorTest {

	private static final String START = "GCD Test Start";
	private static final String END = "GCD Test End";

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
	public void testFindGcd() throws Exception {
		int[] array = new int[] { 5, 10, 15 };
		int expected = 5;
		int actual = GreatestCommonDivisor.findGcd(array);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testBestGcd() throws Exception {
		int[] array = new int[] { 4, 6, 5, 10, 15, 12 };
		int expected = 5;
		int actual = GreatestCommonDivisor.bestGcd(array, 3);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

}
