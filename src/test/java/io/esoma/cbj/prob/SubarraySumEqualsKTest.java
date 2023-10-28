package io.esoma.cbj.prob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class SubarraySumEqualsKTest {

  @Test
  void testCount1() {
    int[] array = new int[] {1909};
    long expected = 1L;
    long actual = SubarraySumEqualsK.count(array, 1909);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testCount2() {
    int[] array = new int[] {1, 2, 3};
    long expected = 2L;
    long actual = SubarraySumEqualsK.count(array, 3);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testCount3() {
    int[] array = new int[] {1, 1, 1, 1};
    long expected = 3L;
    long actual = SubarraySumEqualsK.count(array, 2);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testCount4() {
    int[] array =
        new int[] {2, 2, 3, 1, 4, 2, 3, 2, 5, 5, 5, 5, 2, 3, 1, 3, 4, 3, 5, 5, 2, 1, 2, 4, 2};
    long expected = 11L;
    long actual = SubarraySumEqualsK.count(array, 10);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testCount5() {
    int[] array =
        new int[] {-2, -90, -142, 180, -185, 175, -11, 26, -105, 148, 36, -31, 54, -171, 157};
    long expected = 1L;
    long actual = SubarraySumEqualsK.count(array, 157);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testCount6() {
    int[] array =
        new int[] {
          1, 2, 0, -2, -2, 2, -1, 2, 0, -2, 0, 2, -2, 0, 2, -2, -1, 0, 1, 1, 1, -2, -2, -1, -1, 2,
          -1, 2, -1, -1, -2, 1, 2, 2, -2, -1, -1, -2, 2, -2, 2, 1, 2, 0, 2, 1, 1, 0, 2, 0, -1, 2, 1,
          -2, 2, -1, -2, -2, -2, 2, 1, -2, 2, 1, -1, 1, -1, 1, 1, 0, 1, -2, 0, -2, -2, 2, -2, 1, -1,
          -1, 0, 0, 1, 0, -2, 0, 1, 0, -2, -2, -1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 2, 1, 1, 0, 2, 2, 1,
          -1, 1, -2, 1, 0, -1, -2, 0, 1, 0, 2, -2, 0, -2, 2, -2, 2, 1, 1, -1, -2, 1, -2, -2, 1, -2,
          1, 0, 2, -1, -2, 0, -2, -1, -2, -1, -2, 2, 1, -2, -2, 0, -1, -2, 2, 1, 1, 0, 1, 0, -1, 1,
          2, 0, 1, 1, -1, 0, -1, -1, -1, -2, -2, 1, -1, -2, 1, 2, 1, 2, -1, 1, -1, -2, 2, 1, 2, 0,
          -2, 1, 2, -2, 2, -1, 0, -1, -2, -1, -2, -2, 1, 2, -1
        };
    long expected = 1024L;
    long actual = SubarraySumEqualsK.count(array, 0);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testCount7() {
    int[] array = new int[] {-989, -997, -994, -997, -998, -990, -995, -996, -994, -993};
    long expected = 1L;
    long actual = SubarraySumEqualsK.count(array, -1995);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testCount8() {
    int[] array =
        new int[] {
          8, -4, -4, -5, -2, -5, -3, 9, 0, -6, -6, 2, 9, -2, -3, 2, -3, -2, -6, -3, 2, 7, -4, -5,
          -1, -7, -2, 3, 0, -8
        };
    long expected = 6L;
    long actual = SubarraySumEqualsK.count(array, 1);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testCount9() {
    int[] array =
        new int[] {
          3, 1, 3, 1, 3, 5, 3, 3, 2, 5, 5, 2, 1, 4, 5, 2, 5, 1, 4, 2, 1, 4, 3, 4, 3, 1, 1, 3, 4, 3,
          5, 5, 2, 2, 1, 1, 2, 4, 4, 1, 4, 2, 2, 2, 1, 2, 4, 5, 2, 5, 5, 1, 3, 2, 4, 1, 5, 3, 4, 3,
          5, 2, 2, 2, 3, 3, 1, 2, 1, 5, 3, 3, 2, 3, 1
        };
    long expected = 28L;
    long actual = SubarraySumEqualsK.count(array, 29);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testCount10() {
    int[] array =
        new int[] {
          98, 74, 71, 82, 51, 6, 49, 79, 16, 11, 20, 68, 58, 30, 75, 63, 31, 37, 52, 6, 9, 70, 49,
          38, 86, 84, 73, 69, 58, 26, 61, 62, 64, 73, 88, 41, 74, 92, 19, 12, 33, 4, 33, 42, 60, 38,
          78, 77, 99, 48, 60, 47, 59, 88, 93, 13, 88, 87, 11, 32, 84, 74, 17, 35, 36, 30, 24, 54,
          42, 22, 81, 30, 6, 93, 85, 5, 64, 26, 27, 67, 26, 39, 63, 1, 29, 73, 11, 80, 60, 65, 37,
          8, 76, 66, 72, 31, 30, 48, 30, 25
        };
    long expected = 1L;
    long actual = SubarraySumEqualsK.count(array, 230);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testCount11() {
    int[] array =
        new int[] {
          1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
        };
    long expected = 23L;
    long actual = SubarraySumEqualsK.count(array, 6);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testCount12() {
    int[] array =
        new int[] {
          -36, -33, 81, -49, -86, 25, -38, -55, -49, -85, -51, 35, 68, 23, -74, 9, -83, -40, 2, -48,
          75, -87, -91, 76, 53, -16, 44, 59, 96, 6, 76, -64, 33, -26, 53, -44, -6, 93, -81, -4
        };
    long expected = 0L;
    long actual = SubarraySumEqualsK.count(array, 1000);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }
}
