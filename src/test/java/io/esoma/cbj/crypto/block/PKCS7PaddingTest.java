package io.esoma.cbj.crypto.block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class PKCS7PaddingTest {

  @Test
  void testPerformStringInt() {
    final String input = "YELLOW SUBMARINE";
    final String expected = "YELLOW SUBMARINE\u0004\u0004\u0004\u0004";
    String actual = PKCS7Padding.perform(input, 20);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testUnpadValid() {
    final String input = "ICE ICE BABY\u0004\u0004\u0004\u0004";
    final String expected = "ICE ICE BABY";
    String actual = new String(PKCS7Padding.unpad(input.getBytes(), false));
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testUnpadInvalid() {
    final String input = "ICE ICE BABY\u0001\u0002\u0003\u0004";
    assertThrowsExactly(
        IllegalStateException.class, () -> PKCS7Padding.unpad(input.getBytes(), false));
  }

  @Test
  void testUnpadInvalid2() {
    final String input = "ICE ICE BABY\u0005\u0005\u0005\u0005";
    assertThrowsExactly(
        IllegalStateException.class, () -> PKCS7Padding.unpad(input.getBytes(), false));
  }
}
