package io.esoma.cbj.core;

import io.esoma.cbj.util.Banner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArrayCoreTest {

  private static final String START = "Array Core Test Start";
  private static final String END = "Array Core Test End";

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
  public void testLinearSearchInt() {
    int[] array = new int[] {49, 96, 79, 3, 100, 15, 86, 71, 7, 54, 87, 63, 90, 90, 80, 86, 17};
    int target = 7;
    int expected = 8;
    int actual = ArrayCore.linearSearchInt(array, target, 0, array.length - 1);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testLinearSearchInt2() {
    int[] array = new int[] {94, 60, 63, 83, 87, 51, 8};
    int target = 18;
    int expected = -1;
    int actual = ArrayCore.linearSearchInt(array, target, 0, array.length - 1);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testLinearSearchInt3() {
    int[] array =
        new int[] {
          4119, 2927, 4017, 3787, 2025, 2444, 2358, 3845, 2979, 3085, 4030, 2844, 2237, 3656, 3581,
          3581
        };
    int target = 3581;
    int expected = 14;
    int actual = ArrayCore.linearSearchInt(array, target, 0, array.length - 1);
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testReverseInt1() {
    int[] straight =
        new int[] {
          257, 282, 284, 304, 317, 351, 353, 360, 368, 374, 410, 428, 476, 501, 525, 553, 568, 582,
          603, 621, 624, 654
        };
    int[] reverse =
        new int[] {
          654, 624, 621, 603, 582, 568, 553, 525, 501, 476, 428, 410, 374, 368, 360, 353, 351, 317,
          304, 284, 282, 257
        };
    int[] result = ArrayCore.reverseInt(straight, 0, straight.length - 1);
    System.out.println(Arrays.toString(result));
    assertArrayEquals(reverse, result);
  }

  @Test
  public void testReverseInt2() {
    int[] straight = new int[] {1, 1, 0, 1, 1};
    int[] reverse = new int[] {1, 1, 0, 1, 1};
    int[] result = ArrayCore.reverseInt(straight, 0, straight.length - 1);
    System.out.println(Arrays.toString(result));
    assertArrayEquals(reverse, result);
  }

  @Test
  public void testReverseInt3() {
    int[] straight = new int[] {2, 7, 8, 8, 4, 4, 4, -3, -3, -4, -5, -6, -6, -2, 0};
    int[] reverse = new int[] {2, 7, -6, -6, -5, -4, -3, -3, 4, 4, 4, 8, 8, -2, 0};
    int[] result = ArrayCore.reverseInt(straight, 2, straight.length - 3);
    System.out.println(Arrays.toString(result));
    assertArrayEquals(reverse, result);
  }

  @Test
  public void testInsertInt1() {
    int[] old =
        new int[] {
          5, 8, -9, 0, -7, 3, 3, 1, 0, 2, -6, -9, 7, 9, -3, 2, -1, -6, 1, -8, -5, -8, -6, -4, 1, -3
        };
    int[] inserted =
        new int[] {
          5, 8, -9, 0, -7, 3, 3, 0, 2, -6, -9, 7, 1, 9, -3, 2, -1, -6, 1, -8, -5, -8, -6, -4, 1, -3
        };
    ArrayCore.insertInt(old, 7, 12);
    System.out.println(Arrays.toString(old));
    assertArrayEquals(old, inserted);
  }

  @Test
  public void testInsertInt2() {
    int[] old =
        new int[] {
          645, 842, 953, 709, 120, 93, 685, 813, 399, 341, 540, 574, 920, 283, 45, 942, 474
        };
    int[] inserted =
        new int[] {
          645, 842, 953, 942, 709, 120, 93, 685, 813, 399, 341, 540, 574, 920, 283, 45, 474
        };
    ArrayCore.insertInt(old, 15, 3);
    System.out.println(Arrays.toString(old));
    assertArrayEquals(old, inserted);
  }

  @Test
  public void testInsertInt3() {
    int[] old = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
    int[] inserted = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
    ArrayCore.insertInt(old, 2, 0);
    System.out.println(Arrays.toString(old));
    assertArrayEquals(old, inserted);
  }

  @Test
  public void testCheckIntAsc1() {
    int[] array =
        new int[] {
          1, 2, 4, 5, 5, 6, 6, 8, 8, 8, 8, 9, 9, 10, 11, 12, 14, 16, 18, 19, 21, 21, 23, 23, 24, 24,
          25, 26, 27, 28, 28, 28, 31, 32, 34, 35, 36, 37, 37, 37, 39, 40, 40, 42, 43, 44, 45, 46,
          46, 46, 46, 47, 47, 49, 53, 55, 56, 57, 58, 58, 60, 63, 63, 64, 65, 65, 66, 66, 70, 71,
          72, 72, 72, 73, 73, 74, 77, 78, 78, 80, 80, 80, 81, 81, 82, 82, 82, 85, 86, 89, 89, 90,
          92, 94, 95, 95, 96, 96, 98, 100
        };
    assertTrue(ArrayCore.checkIntAsc(array, 0, array.length - 1));
  }

  @Test
  public void testCheckIntAsc2() {
    int[] array =
        new int[] {
          99, 1009, 40, 40, 40, 40, 41, 42, 43, 43, 44, 44, 45, 45, 46, 47, 48, 48, 48, 49, 49, 50,
          5080, 90, 50
        };
    assertTrue(ArrayCore.checkIntAsc(array, 2, array.length - 4));
  }

  @Test
  public void testCheckIntAsc3() {
    int[] array =
        new int[] {
          684, 684, 685, 692, 703, 708, 710, 713, 720, 766, 777, 884, 802, 804, 813, 817, 823, 826,
          878, 902, 902, 906, 908, 909, 932, 934, 936, 942, 987, 996
        };
    assertFalse(ArrayCore.checkIntAsc(array, 0, array.length - 1));
  }
}
