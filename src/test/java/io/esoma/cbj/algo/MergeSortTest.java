package io.esoma.cbj.algo;

import io.esoma.cbj.util.Banner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class MergeSortTest {

  private static final String START = "Merge Sort Test Start";
  private static final String END = "Merge Sort Test End";

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
    int[] unsorted = new int[] {2, 5, 3, 1, 6, 9, 10, 7, 8, 4};
    int[] sorted = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int[] result = MergeSort.sort(unsorted);
    System.out.println(Arrays.toString(unsorted));
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort2() throws Exception {
    int[] unsorted = new int[] {2, 4, 6, 1, 4, 2, 1, 7, 8, 7, 3, 5, 5, 7, 8, 6, 9, 3, 8};
    int[] sorted = new int[] {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 7, 8, 8, 8, 9};
    int[] result = MergeSort.sort(unsorted);
    System.out.println(Arrays.toString(unsorted));
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort3() throws Exception {
    int[] unsorted = new int[] {7, -2, -1, -8, 6, 10, 22, 190, -9, 5};
    int[] sorted = new int[] {-9, -8, -2, -1, 5, 6, 7, 10, 22, 190};
    int[] result = MergeSort.sort(unsorted);
    System.out.println(Arrays.toString(unsorted));
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort4() throws Exception {
    int[] unsorted = new int[] {100, 999, 800, 3, -408, 59, 290, 10, 5};
    int[] sorted = new int[] {-408, 3, 5, 10, 59, 100, 290, 800, 999};
    int[] result = MergeSort.sort(unsorted);
    System.out.println(Arrays.toString(unsorted));
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort5() throws Exception {
    int[] unsorted =
        new int[] {
          -54, 49, 43, 18, -42, -94, -68, -67, -83, 31, -10, -46, -29, 17, -31, -57, -86, 69, -43
        };
    int[] sorted =
        new int[] {
          -94, -86, -83, -68, -67, -57, -54, -46, -43, -42, -31, -29, -10, 17, 18, 31, 43, 49, 69
        };
    int[] result = MergeSort.sort(unsorted);
    System.out.println(Arrays.toString(unsorted));
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort6() throws Exception {
    int[] unsorted = new int[] {85, 19, -96, 32, 41, -75, -71, 83, -95, 34, 40, 21, 65};
    int[] sorted = new int[] {-96, -95, -75, -71, 19, 21, 32, 34, 40, 41, 65, 83, 85};
    int[] result = MergeSort.sort(unsorted);
    System.out.println(Arrays.toString(unsorted));
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort7() throws Exception {
    int[] unsorted = new int[] {14, -82, 2, 4, 58, -47, -16, -1, 70, -89, -21, 8};
    int[] sorted = new int[] {-89, -82, -47, -21, -16, -1, 2, 4, 8, 14, 58, 70};
    int[] result = MergeSort.sort(unsorted);
    System.out.println(Arrays.toString(unsorted));
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort8() throws Exception {
    int[] unsorted = new int[] {11, -85, 13, 28, 6, 88, 46, -2, 47, 24, 54, -88, -20, -78, 42, -74};
    int[] sorted = new int[] {-88, -85, -78, -74, -20, -2, 6, 11, 13, 24, 28, 42, 46, 47, 54, 88};
    int[] result = MergeSort.sort(unsorted);
    System.out.println(Arrays.toString(unsorted));
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort9() throws Exception {
    int[] unsorted =
        new int[] {-482, -430, -424, -344, -311, -286, -238, -221, -210, -145, -105, -13, -9};
    int[] sorted =
        new int[] {-482, -430, -424, -344, -311, -286, -238, -221, -210, -145, -105, -13, -9};
    int[] result = MergeSort.sort(unsorted);
    System.out.println(Arrays.toString(unsorted));
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort10() throws Exception {
    int[] unsorted =
        new int[] {
          7457339, 7460627, 7458844, 7458590, 7463229, 7458481, 7461115, 7461846, 7456149, 7463483,
          7458673, 7459154, 7461161, 7457257, 7460370, 7459580, 7458513, 7458656, 7457518
        };
    int[] sorted =
        new int[] {
          7456149, 7457257, 7457339, 7457518, 7458481, 7458513, 7458590, 7458656, 7458673, 7458844,
          7459154, 7459580, 7460370, 7460627, 7461115, 7461161, 7461846, 7463229, 7463483
        };
    int[] result = MergeSort.sort(unsorted);
    System.out.println(Arrays.toString(unsorted));
    assertArrayEquals(sorted, result);
  }

  @Test
  public void testSort11() throws Exception {
    int[] unsorted = new int[] {0, 2, 3, 4, 5, 6, 7, 8, 9, 11};
    int[] sorted = new int[] {0, 2, 3, 4, 5, 6, 7, 8, 9, 11};
    int[] result = MergeSort.sort(unsorted);
    System.out.println(Arrays.toString(unsorted));
    assertArrayEquals(sorted, result);
  }
}
