package io.esoma.cbj.crypto.block;

import org.junit.Test;
import org.tinylog.Logger;

import static org.junit.Assert.assertEquals;

public class PKCS7PaddingTest {

  @Test
  public void testPerformStringInt() {
    final String input = "YELLOW SUBMARINE";
    final String expected = "YELLOW SUBMARINE\u0004\u0004\u0004\u0004";
    String actual = PKCS7Padding.perform(input, 20);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testUnpadValid() {
    final String input = "ICE ICE BABY\u0004\u0004\u0004\u0004";
    final String expected = "ICE ICE BABY";
    String actual = new String(PKCS7Padding.unpad(input.getBytes(), false));
    Logger.debug(actual);
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
