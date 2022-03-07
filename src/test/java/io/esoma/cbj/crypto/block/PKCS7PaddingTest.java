package io.esoma.cbj.crypto.block;

import io.esoma.cbj.util.Banner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
  public void testPerformStringInt() {
    final String input = "YELLOW SUBMARINE";
    final String expected = "YELLOW SUBMARINE\u0004\u0004\u0004\u0004";
    String actual = PKCS7Padding.perform(input, 20);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testUnpadValid() {
    final String input = "ICE ICE BABY\u0004\u0004\u0004\u0004";
    final String expected = "ICE ICE BABY";
    String actual = new String(PKCS7Padding.unpad(input.getBytes(), false));
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test(expected = IllegalStateException.class)
  public void testUnpadInvalid() {
    final String input = "ICE ICE BABY\u0001\u0002\u0003\u0004";
    PKCS7Padding.unpad(input.getBytes(), false);
  }

  @Test(expected = IllegalStateException.class)
  public void testUnpadInvalid2() {
    final String input = "ICE ICE BABY\u0005\u0005\u0005\u0005";
    PKCS7Padding.unpad(input.getBytes(), false);
  }
}
