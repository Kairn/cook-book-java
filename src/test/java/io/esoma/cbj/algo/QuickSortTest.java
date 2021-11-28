package io.esoma.cbj.algo;

import io.esoma.cbj.util.Banner;
import io.esoma.cbj.util.Printer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class QuickSortTest {

  private static final String START = "Quick Sort Test Start";
  private static final String END = "Quick Sort Test End";

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
    int[] unsorted =
        new int[] {19, 3, 14, 7, 18, 13, 4, 15, 5, 8, 16, 12, 9, 10, 6, 2, 17, 11, 1, 20};
    int[] sorted =
        new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    int[] result = QuickSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort2() throws Exception {
    int[] unsorted =
        new int[] {1001, 1011, 1004, 1008, 1002, 1010, 1003, 1000, 1007, 1005, 1006, 1012, 1009};
    int[] sorted =
        new int[] {1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010, 1011, 1012};
    int[] result = QuickSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort3() throws Exception {
    int[] unsorted =
        new int[] {
          326, -3, 21, 253, -159, 226, -286, -254, -191, 379, 242, -178, 171, 66, 28, 74, 329, -230
        };
    int[] sorted =
        new int[] {
          -286, -254, -230, -191, -178, -159, -3, 21, 28, 66, 74, 171, 226, 242, 253, 326, 329, 379
        };
    int[] result = QuickSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort4() throws Exception {
    int[] unsorted =
        new int[] {29, 49, 51, 12, 64, 83, 85, 57, 1, 97, 41, 7, 43, 62, 89, 44, 5, 45, 80, 13, 86};
    int[] sorted =
        new int[] {1, 5, 7, 12, 13, 29, 41, 43, 44, 45, 49, 51, 57, 62, 64, 80, 83, 85, 86, 89, 97};
    int[] result = QuickSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort5() throws Exception {
    int[] unsorted = new int[] {0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0};
    int[] sorted = new int[] {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
    int[] result = QuickSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort6() throws Exception {
    int[] unsorted = new int[] {13};
    int[] sorted = new int[] {13};
    int[] result = QuickSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort7() throws Exception {
    int[] unsorted = new int[] {1, -3, 0, 3, 4, -1, -2, 5, -2, -3, 1, 1, 5, -3, 0, -3, 5, 4, 1};
    int[] sorted = new int[] {-3, -3, -3, -3, -2, -2, -1, 0, 0, 1, 1, 1, 1, 3, 4, 4, 5, 5, 5};
    int[] result = QuickSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort8() throws Exception {
    int[] unsorted = new int[] {63610, 67402, 67133, 49306, 53000, 69525, 49882, 59535, 63516};
    int[] sorted = new int[] {49306, 49882, 53000, 59535, 63516, 63610, 67133, 67402, 69525};
    int[] result = QuickSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort9() throws Exception {
    int[] unsorted = new int[] {4, 9, 4, 12, 9, 9, 3, 7, 12, 9, 10, 12, 2, 10};
    int[] sorted = new int[] {2, 3, 4, 4, 7, 9, 9, 9, 9, 10, 10, 12, 12, 12};
    int[] result = QuickSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort10() throws Exception {
    int[] unsorted = new int[] {31, 48, 12, 18, 38, 32, 45, 39, 42, 6};
    int[] sorted = new int[] {6, 12, 18, 31, 32, 38, 39, 42, 45, 48};
    int[] result = QuickSort.sort(unsorted);
    Printer.printIntArray(result, ' ');
    assertArrayEquals(sorted, result);
  }
}
