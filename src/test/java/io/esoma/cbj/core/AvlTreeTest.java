package io.esoma.cbj.core;

import io.esoma.cbj.util.Banner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AvlTreeTest {

  private static final String START = "AVL Tree Test Start";
  private static final String END = "AVL Tree Test End";

  // Hold the tree used for testing.
  private static AvlTree<Integer> avt;

  @BeforeClass
  public static void setUpBeforeClass() {
    System.out.println(Banner.getTitleBanner(START, 3));
    avt = new AvlTree<>();
  }

  @AfterClass
  public static void tearDownAfterClass() {
    avt = null;
    System.out.println(Banner.getTitleBanner(END, 3));
    System.out.println();
  }

  @Test
  /*
   * Aggregate test of 20 batches of operations.
   */
  public void testAvl() {
    int expected = 0;
    int actual = avt.getSize();
    System.out.println(avt);
    assertEquals(expected, actual);

    avt.insert(67);
    avt.insert(12);
    avt.insert(33);
    avt.insert(10);
    avt.insert(10);
    avt.insert(23);
    avt.insert(1);
    expected = 6;
    actual = avt.getSize();
    System.out.println(avt);
    assertEquals(expected, actual);

    expected = 20;
    actual = avt.insert(20);
    System.out.println(avt);
    assertEquals(expected, actual);

    expected = 1;
    actual = avt.contains(10) ? 1 : 0;
    System.out.println(avt);
    assertEquals(expected, actual);

    expected = 0;
    actual = avt.contains(104) ? 1 : 0;
    System.out.println(avt);
    assertEquals(expected, actual);

    // Expected is still 0.
    actual = avt.delete(800) == null ? 0 : 1;
    System.out.println(avt);
    assertEquals(expected, actual);

    expected = 1;
    actual = avt.delete(67) == null ? 0 : 1;
    System.out.println(avt);
    assertEquals(expected, actual);

    expected = 6;
    actual = avt.getSize();
    System.out.println(avt);
    assertEquals(expected, actual);

    avt.insert(307);
    avt.insert(32);
    avt.insert(72);
    avt.insert(17);
    avt.insert(709);
    avt.insert(123);
    avt.insert(550);
    expected = 13;
    actual = avt.getSize();
    System.out.println(avt);
    assertEquals(expected, actual);

    expected = 1;
    actual = avt.toSortedList().get(0);
    System.out.println(avt);
    assertEquals(expected, actual);

    avt.delete(1);
    avt.delete(33);
    avt.delete(499);
    expected = 11;
    actual = avt.getSize();
    System.out.println(avt);
    assertEquals(expected, actual);

    expected = 0;
    actual = avt.contains(689) ? 1 : 0;
    System.out.println(avt);
    assertEquals(expected, actual);

    expected = 1;
    actual = avt.contains(709) ? 1 : 0;
    System.out.println(avt);
    assertEquals(expected, actual);

    avt.insert(9999);
    avt.insert(99);
    avt.insert(9);
    avt.insert(111);
    avt.insert(432);
    avt.insert(5678);
    avt.insert(7777);
    avt.insert(111);
    expected = 18;
    actual = avt.getSize();
    System.out.println(avt);
    assertEquals(expected, actual);

    expected = 1;
    actual = avt.contains(7777) ? 1 : 0;
    System.out.println(avt);
    assertEquals(expected, actual);

    avt.delete(550);
    expected = 17;
    actual = avt.getSize();
    System.out.println(avt);
    assertEquals(expected, actual);

    expected = 0;
    actual = avt.contains(550) ? 1 : 0;
    System.out.println(avt);
    assertEquals(expected, actual);

    avt.delete(66);
    avt.delete(61);
    avt.delete(63);
    avt.delete(67);
    avt.delete(111);
    expected = 16;
    actual = avt.getSize();
    System.out.println(avt);
    assertEquals(expected, actual);

    expected = 9999;
    actual = avt.toSortedList().get(avt.getSize() - 1);
    System.out.println(avt);
    assertEquals(expected, actual);

    avt.insert(398);
    avt.delete(398);
    avt.delete(9999);
    avt.delete(9);
    expected = 12;
    actual = avt.toSortedList().get(1);
    System.out.println(avt);
    assertEquals(expected, actual);
  }

  @Test
  public void testToSortedList() {
    AvlTree<Integer> tree = new AvlTree<>();
    int[] array =
        new int[] {
          56, 94, 80, 88, 23, 5, 0, 68, 49, 60, 15, 66, 12, 35, -3, 69, 89, 12, 46, 11, 24, -5, 86,
          5, 33, 5, 86, 52, 48, 76, 4, 55, 15, 51, 102, 67, 8, 103, 92, 84, 92, 38, 48, 57, 13, 15,
          49, 87, 13, 24, 104, 19, 41, 73, -3, 4, 5, 79, 15, 82, 62, 92, -2, 83, 40, 33, 101, 104,
          73, 46, 47, 8, 62, 29, 90, 9, 85, 89, 85, 31, 16, 100, 34, 4, 5, 74, 42, 40, 37, 49, 89,
          32, 5, 96, 36, 73, 31, 63, 42, 68
        };
    for (int a : array) {
      tree.insert(a);
    }

    // Duplicates are not included.
    int[] expected =
        new int[] {
          -5, -3, -2, 0, 4, 5, 8, 9, 11, 12, 13, 15, 16, 19, 23, 24, 29, 31, 32, 33, 34, 35, 36, 37,
          38, 40, 41, 42, 46, 47, 48, 49, 51, 52, 55, 56, 57, 60, 62, 63, 66, 67, 68, 69, 73, 74,
          76, 79, 80, 82, 83, 84, 85, 86, 87, 88, 89, 90, 92, 94, 96, 100, 101, 102, 103, 104
        };
    int i = 0;
    for (int e : tree.toSortedList()) {
      assertEquals(expected[i], e);
      ++i;
    }
  }
}
