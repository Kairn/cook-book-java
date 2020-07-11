package io.esoma.cbj.algo;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.esoma.cbj.util.Banner;

public class DeciBinaryTest {

	private static final String START = "Decibinary Test Start";
	private static final String END = "Decibinary Test End";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DeciBinary.initCache();
		System.out.println(Banner.getTitleBanner(START, 3));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println(Banner.getTitleBanner(END, 3));
		System.out.println();
	}

	@Test
	public void testDeciValue1() throws Exception {
		int dbNum = 0;
		int expected = 0;
		int actual = DeciBinary.deciValue(dbNum);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciValue2() throws Exception {
		int dbNum = 1;
		int expected = 1;
		int actual = DeciBinary.deciValue(dbNum);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciValue3() throws Exception {
		int dbNum = 10;
		int expected = 2;
		int actual = DeciBinary.deciValue(dbNum);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciValue4() throws Exception {
		int dbNum = 2016;
		int expected = 24;
		int actual = DeciBinary.deciValue(dbNum);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciValue5() throws Exception {
		int dbNum = 2008;
		int expected = 24;
		int actual = DeciBinary.deciValue(dbNum);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciValue6() throws Exception {
		int dbNum = 75;
		int expected = 19;
		int actual = DeciBinary.deciValue(dbNum);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciValue7() throws Exception {
		int dbNum = 108;
		int expected = 12;
		int actual = DeciBinary.deciValue(dbNum);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciValue8() throws Exception {
		int dbNum = 7422485;
		int expected = 661;
		int actual = DeciBinary.deciValue(dbNum);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciFromDb1() throws Exception {
		long db = 7L;
		long expected = 4L;
		long actual = DeciBinary.deciFromDb(db);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciFromDb2() throws Exception {
		long db = 10L;
		long expected = 100L;
		long actual = DeciBinary.deciFromDb(db);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciFromDb3() throws Exception {
		long db = 3L;
		long expected = 2L;
		long actual = DeciBinary.deciFromDb(db);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciFromDb4() throws Exception {
		long db = 6L;
		long expected = 11L;
		long actual = DeciBinary.deciFromDb(db);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciFromDb5() throws Exception {
		long db = 26L;
		long expected = 111L;
		long actual = DeciBinary.deciFromDb(db);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciFromDb6() throws Exception {
		long db = 19L;
		long expected = 102L;
		long actual = DeciBinary.deciFromDb(db);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciFromDb7() throws Exception {
		long db = 30L;
		long expected = 32L;
		long actual = DeciBinary.deciFromDb(db);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciFromDb8() throws Exception {
		long db = 1104L;
		long expected = 11111L;
		long actual = DeciBinary.deciFromDb(db);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciFromDb9() throws Exception {
		long db = 1963L;
		long expected = 10406L;
		long actual = DeciBinary.deciFromDb(db);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciFromDb10() throws Exception {
		long db = 20L;
		long expected = 110L;
		long actual = DeciBinary.deciFromDb(db);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

	@Test
	public void testDeciFromDb11() throws Exception {
		long db = 1L;
		long expected = 0L;
		long actual = DeciBinary.deciFromDb(db);
		System.out.println(actual);
		assertTrue(expected == actual);
	}

}
