package io.esoma.cbj.crypto.block;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.esoma.cbj.util.Banner;

public class PKCS7PaddingTest {

	private static final String START = "PKCS#7 Test Start";
	private static final String END = "PKCS#7 Test End";

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
	public void testPerformStringInt() throws Exception {
		final String input = "YELLOW SUBMARINE";
		final String expected = "YELLOW SUBMARINE\u0004\u0004\u0004\u0004";
		String actual = PKCS7Padding.perform(input, 20);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

}
