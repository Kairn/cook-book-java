package io.esoma.cbj.algo;

import io.esoma.cbj.util.Banner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubArrayImbalanceTest {

  private static final String START = "SubArrayImbalance Test Start";
  private static final String END = "SubArrayImbalance Test End";

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
  public void testGetTotalImbalance1() {
    int[] array = new int[] {988};
    long expected = 0L;
    long actual = SubArrayImbalance.getTotalImbalance(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testGetTotalImbalance2() {
    int[] array = new int[] {1, 2, 3};
    long expected = 4L;
    long actual = SubArrayImbalance.getTotalImbalance(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testGetTotalImbalance3() {
    int[] array = new int[] {10, 9, 8, 7};
    long expected = 10L;
    long actual = SubArrayImbalance.getTotalImbalance(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testGetTotalImbalance4() {
    int[] array = new int[] {5, 2, 3, 7, 9};
    long expected = 43L;
    long actual = SubArrayImbalance.getTotalImbalance(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testGetTotalImbalance5() {
    int[] array =
        new int[] {
          1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
          1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
        };
    long expected = 0L;
    long actual = SubArrayImbalance.getTotalImbalance(array);
    System.out.println(actual);
    assertEquals(expected, actual);
  }
}
