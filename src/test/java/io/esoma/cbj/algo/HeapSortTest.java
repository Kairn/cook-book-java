package io.esoma.cbj.algo;

import io.esoma.cbj.util.Banner;
import io.esoma.cbj.util.Printer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class HeapSortTest {

  private static final String START = "Heap Sort Test Start";
  private static final String END = "Heap Sort Test End";

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
  public void testSort1() throws Exception {
    int[] unsorted = new int[] {2, 1, 2, 9, 1, 6, 6, 8, 1, 9};
    int[] sorted = new int[] {1, 1, 1, 2, 2, 6, 6, 8, 9, 9};
    int[] result = HeapSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort2() throws Exception {
    int[] unsorted = new int[] {17, 21, 5, 30, 7, 16, 4, 15, 29, 1, 26, 14, 10, 18, 17};
    int[] sorted = new int[] {1, 4, 5, 7, 10, 14, 15, 16, 17, 17, 18, 21, 26, 29, 30};
    int[] result = HeapSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort3() throws Exception {
    int[] unsorted = new int[] {-19, -26, -18, -5, -18, -3, -11, -11, -12, -14, -22, -21};
    int[] sorted = new int[] {-26, -22, -21, -19, -18, -18, -14, -12, -11, -11, -5, -3};
    int[] result = HeapSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort4() throws Exception {
    int[] unsorted =
        new int[] {
          -57, 64, -3, -96, -4, -68, -99, -62, 26, 8, -54, -49, -34, -80, -5, -56, 96, -69, 55, 55,
          -75, -19, 53, -37, 83, 88, 92, 81, 82, 13, 66
        };
    int[] sorted =
        new int[] {
          -99, -96, -80, -75, -69, -68, -62, -57, -56, -54, -49, -37, -34, -19, -5, -4, -3, 8, 13,
          26, 53, 55, 55, 64, 66, 81, 82, 83, 88, 92, 96
        };
    int[] result = HeapSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort5() throws Exception {
    int[] unsorted = new int[] {14, 13};
    int[] sorted = new int[] {13, 14};
    int[] result = HeapSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort6() throws Exception {
    int[] unsorted =
        new int[] {
          -1, 2, -1, 2, 2, 2, 0, 0, 0, 2, 1, 2, -1, 2, 1, -1, 1, -1, -1, 0, -1, -1, 1, 0, -1, 2, -1
        };
    int[] sorted =
        new int[] {
          -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2
        };
    int[] result = HeapSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort7() throws Exception {
    int[] unsorted = new int[] {63079, 61607, 62859, 63848, 62004, 61060, 60896, 60864};
    int[] sorted = new int[] {60864, 60896, 61060, 61607, 62004, 62859, 63079, 63848};
    int[] result = HeapSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort8() throws Exception {
    int[] unsorted = new int[] {119, 118, 110, 107, 107, 97, 84, 74, 71, 69, 66, 65, 49, 38, 29};
    int[] sorted = new int[] {29, 38, 49, 65, 66, 69, 71, 74, 84, 97, 107, 107, 110, 118, 119};
    int[] result = HeapSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort9() throws Exception {
    int[] unsorted = new int[] {983, 642, 818, 788, 887, 682, 610, 583, 590, 1006};
    int[] sorted = new int[] {583, 590, 610, 642, 682, 788, 818, 887, 983, 1006};
    int[] result = HeapSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort10() throws Exception {
    int[] unsorted =
        new int[] {
          11, 16, 12, 16, 17, 13, 10, 15, 17, 10, 17, 16, 12, 13, 17, 10, 14, 13, 12, 12, 12, 14, 15
        };
    int[] sorted =
        new int[] {
          10, 10, 10, 11, 12, 12, 12, 12, 12, 13, 13, 13, 14, 14, 15, 15, 16, 16, 16, 17, 17, 17, 17
        };
    int[] result = HeapSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }
}
