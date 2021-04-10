package io.esoma.cbj.crypto.basic;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.esoma.cbj.util.Banner;

public class RepeatingXorTest {

	private static final String START = "Repeating XOR Test Start";
	private static final String END = "Repeating XOR Test End";

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
	public void testEncrypt() throws Exception {
		final String message = "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal";
		final String key = "ICE";
		final String expected = "0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f";
		String actual = RepeatingXor.create(message, key, true);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testDecrypt() throws Exception {
		final String message = "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal";
		final String key = "ICE";
		final String encrypted = RepeatingXor.create(message, key, false);
		String actual = RepeatingXor.create(encrypted, key, false);
		System.out.println(actual);
		assertEquals(message, actual);
	}

}
