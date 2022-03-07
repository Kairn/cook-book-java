package io.esoma.cbj.crypto.basic;

import io.esoma.cbj.util.Banner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HammingDistanceTest {

  private static final String START = "Hamming distance Test Start";
  private static final String END = "Hamming distance Test End";

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
  public void testComputeStringString() {
    final String s1 = "this is a test";
    final String s2 = "wokka wokka!!!";
    final int expected = 37;
    int actual = HammingDistance.compute(s1, s2);
    System.out.println(actual);
    assertEquals(expected, actual);
  }
}
