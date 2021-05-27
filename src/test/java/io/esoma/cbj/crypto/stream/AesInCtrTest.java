package io.esoma.cbj.crypto.stream;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.esoma.cbj.crypto.core.Base64Util;
import io.esoma.cbj.util.Banner;

public class AesInCtrTest {

	private static final String START = "AES CTR Test Start";
	private static final String END = "AES CTR Test End";

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
		final String key = "YELLOW SUBMARINE";
		final byte[] nonce = new byte[8];
		final CounterGenerator counterGenerator = new LeIntegerCounterGenerator();
		final String plainText = "Yo, VIP Let's kick it Ice, Ice, baby Ice, Ice, baby ";
		final String expected = "L77na/nrFsKvynd6HzOoG7GHTLXsTVu9qvY/2syLXzhPweyyMTJULu/6/kXX0KSvoOLSFQ==";
		String actual = Base64Util.encodeFromBytes(
				AesInCtr.decrypt(plainText.getBytes(StandardCharsets.US_ASCII), key, nonce, counterGenerator));
		System.out.println(actual);
		assertEquals(expected, actual);
	}

}
