package io.esoma.cbj.crypto.basic;

import org.junit.Test;
import org.tinylog.Logger;

import static org.junit.Assert.assertEquals;

public class HammingDistanceTest {

  @Test
  public void testComputeStringString() {
    final String s1 = "this is a test";
    final String s2 = "wokka wokka!!!";
    final int expected = 37;
    int actual = HammingDistance.compute(s1, s2);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }
}
