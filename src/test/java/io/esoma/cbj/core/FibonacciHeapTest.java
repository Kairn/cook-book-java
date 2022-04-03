package io.esoma.cbj.core;

import io.esoma.cbj.util.Banner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FibonacciHeapTest {

  private static final String START = "Fibonacci Heap Test Start";
  private static final String END = "Fibonacci Heap Test End";

  // Hold the heap used for testing.
  private static FibonacciHeap<Integer> tfh;

  @BeforeClass
  public static void setUpBeforeClass() {
    System.out.println(Banner.getTitleBanner(START, 3));
    tfh = new FibonacciHeap<>();
  }

  @AfterClass
  public static void tearDownAfterClass() {
    tfh = null;
    System.out.println(Banner.getTitleBanner(END, 3));
    System.out.println();
  }

  @Test
  /*
   * Aggregate test of 20 batches of operations.
   */
  public void testFh() {
    int expected = 0;
    int actual = tfh.getSize();
    System.out.println(tfh);
    assertEquals(expected, actual);

    tfh.push(35);
    tfh.push(5);
    tfh.push(15);
    tfh.push(4);
    tfh.push(1);
    expected = 5;
    actual = tfh.getSize();
    System.out.println(tfh);
    assertEquals(expected, actual);

    expected = 1;
    actual = tfh.peekMin();
    System.out.println(tfh);
    assertEquals(expected, actual);

    // Expected is still 1.
    actual = tfh.popMin();
    System.out.println(tfh);
    assertEquals(expected, actual);

    tfh.push(25);
    tfh.push(18);
    tfh.push(2009);
    tfh.push(11);
    tfh.push(56);
    tfh.push(77);
    tfh.push(29);
    expected = 11;
    actual = tfh.getSize();
    System.out.println(tfh);
    assertEquals(expected, actual);

    expected = 4;
    actual = tfh.popMin();
    System.out.println(tfh);
    assertEquals(expected, actual);

    expected = 5;
    actual = tfh.popMin();
    System.out.println(tfh);
    assertEquals(expected, actual);

    expected = 9;
    actual = tfh.getSize();
    System.out.println(tfh);
    assertEquals(expected, actual);

    expected = 11;
    actual = tfh.peekMin();
    System.out.println(tfh);
    assertEquals(expected, actual);

    tfh.push(3);
    tfh.push(30);
    tfh.push(300);
    tfh.push(3000);
    tfh.push(30000);
    expected = 3;
    actual = tfh.popMin();
    System.out.println(tfh);
    assertEquals(expected, actual);

    tfh.popMin();
    expected = 15;
    actual = tfh.popMin();
    System.out.println(tfh);
    assertEquals(expected, actual);

    tfh.push(43);
    tfh.push(7);
    tfh.push(79);
    expected = 14;
    actual = tfh.getSize();
    System.out.println(tfh);
    assertEquals(expected, actual);

    expected = 7;
    actual = tfh.peekMin();
    System.out.println(tfh);
    assertEquals(expected, actual);

    tfh.push(2);
    tfh.push(27);
    tfh.push(440);
    tfh.push(0);
    tfh.push(248);
    tfh.push(90);
    expected = 0;
    actual = tfh.popMin();
    System.out.println(tfh);
    assertEquals(expected, actual);

    expected = 2;
    actual = tfh.popMin();
    System.out.println(tfh);
    assertEquals(expected, actual);

    expected = 18;
    actual = tfh.getSize();
    System.out.println(tfh);
    assertEquals(expected, actual);

    tfh.popMin();
    tfh.popMin();
    tfh.popMin();
    expected = 27;
    actual = tfh.popMin();
    System.out.println(tfh);
    assertEquals(expected, actual);

    expected = 29;
    actual = tfh.popMin();
    System.out.println(tfh);
    assertEquals(expected, actual);

    tfh.popMin();
    tfh.push(28);
    expected = 28;
    actual = tfh.popMin();
    System.out.println(tfh);
    assertEquals(expected, actual);

    expected = 12;
    actual = tfh.getSize();
    System.out.println(tfh);
    assertEquals(expected, actual);
  }
}
