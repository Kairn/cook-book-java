package io.esoma.cbj.crypto.basic;

import io.esoma.cbj.util.Banner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FixedXorTest {

  private static final String START = "Fixed XOR Test Start";
  private static final String END = "Fixed XOR Test End";

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
  public void testCombine() {
    final String input1 = "1c0111001f010100061a024b53535009181c";
    final String input2 = "686974207468652062756c6c277320657965";
    final String expected = "746865206b696420646f6e277420706c6179";
    String actual = FixedXor.combine(input1, input2);
    System.out.println(actual);
    assertEquals(expected, actual);
  }
}
