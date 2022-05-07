package io.esoma.cbj.crypto.basic;

import org.junit.Test;
import org.tinylog.Logger;

import static org.junit.Assert.assertEquals;

public class FixedXorTest {

  @Test
  public void testCombine() {
    final String input1 = "1c0111001f010100061a024b53535009181c";
    final String input2 = "686974207468652062756c6c277320657965";
    final String expected = "746865206b696420646f6e277420706c6179";
    String actual = FixedXor.combine(input1, input2);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }
}
