package io.esoma.cbj.algo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.tinylog.Logger;

class BellmanFordTest {

  // Test inputs for the larger test case(s).
  private static final int LARGE_TEST_N = 15;
  private static final int[][] LARGE_TEST_EDGES =
      new int[][] {
        new int[] {0, 1, 8},
        new int[] {0, 2, 5},
        new int[] {0, 3, 7},
        new int[] {0, 5, 2},
        new int[] {0, 6, 3},
        new int[] {0, 7, 5},
        new int[] {0, 9, 8},
        new int[] {1, 0, 5},
        new int[] {1, 2, 9},
        new int[] {1, 4, 5},
        new int[] {1, 7, 3},
        new int[] {1, 8, 2},
        new int[] {1, 12, 3},
        new int[] {2, 14, 2},
        new int[] {2, 11, 3},
        new int[] {2, 10, 6},
        new int[] {3, 9, 4},
        new int[] {3, 13, 6},
        new int[] {3, 1, 3},
        new int[] {3, 7, 7},
        new int[] {4, 5, 4},
        new int[] {4, 2, 3},
        new int[] {4, 12, 5},
        new int[] {4, 14, 7},
        new int[] {4, 10, 3},
        new int[] {5, 1, 2},
        new int[] {5, 0, 1},
        new int[] {5, 10, 1},
        new int[] {5, 11, 3},
        new int[] {5, 13, 6},
        new int[] {5, 7, 8},
        new int[] {5, 4, 6},
        new int[] {5, 8, 9},
        new int[] {6, 8, 8},
        new int[] {6, 1, 9},
        new int[] {6, 3, 5},
        new int[] {6, 13, 6},
        new int[] {6, 12, 9},
        new int[] {6, 5, 4},
        new int[] {6, 2, 7},
        new int[] {7, 0, 2},
        new int[] {7, 1, 7},
        new int[] {7, 5, 8},
        new int[] {7, 8, 5},
        new int[] {7, 9, 3},
        new int[] {7, 14, 4},
        new int[] {7, 6, 2},
        new int[] {7, 3, 3},
        new int[] {8, 4, 6},
        new int[] {8, 7, 36},
        new int[] {8, 9, 3},
        new int[] {8, 10, 4},
        new int[] {8, 11, 11},
        new int[] {8, 14, 2},
        new int[] {8, 12, 5},
        new int[] {8, 1, 8},
        new int[] {8, 5, 6},
        new int[] {8, 3, 9},
        new int[] {8, 6, 3},
        new int[] {9, 0, 6},
        new int[] {9, 3, 4},
        new int[] {9, 6, 2},
        new int[] {9, 12, 9},
        new int[] {9, 14, 12},
        new int[] {9, 7, 1},
        new int[] {9, 4, 3},
        new int[] {10, 4, 4},
        new int[] {10, 8, 7},
        new int[] {10, 12, 3},
        new int[] {10, 0, 4},
        new int[] {10, 5, 5},
        new int[] {10, 11, 4},
        new int[] {10, 9, 4},
        new int[] {11, 7, 2},
        new int[] {11, 14, 3},
        new int[] {11, 2, 6},
        new int[] {11, 5, 3},
        new int[] {11, 8, 15},
        new int[] {11, 10, 7},
        new int[] {11, 1, 3},
        new int[] {11, 12, 1},
        new int[] {11, 9, 8},
        new int[] {12, 0, 9},
        new int[] {12, 1, 3},
        new int[] {12, 2, 4},
        new int[] {12, 4, 7},
        new int[] {12, 5, 7},
        new int[] {12, 6, 4},
        new int[] {12, 8, 4},
        new int[] {12, 9, 3},
        new int[] {13, 10, 8},
        new int[] {13, 11, 7},
        new int[] {13, 12, 2},
        new int[] {13, 14, 4},
        new int[] {13, 0, 4},
        new int[] {14, 3, 6},
        new int[] {14, 5, 4},
        new int[] {14, 7, 2},
        new int[] {14, 9, 5},
        new int[] {14, 0, 9},
        new int[] {14, 12, 8},
        new int[] {14, 13, 3},
        new int[] {14, 8, 4}
      };

  @Test
  void testBfFindSample1() {
    int n = 4;
    int[][] edges =
        new int[][] {
          new int[] {0, 1, 100},
          new int[] {1, 2, 100},
          new int[] {2, 0, 100},
          new int[] {1, 3, 600},
          new int[] {2, 3, 200}
        };
    int src = 0;
    int dest = 3;
    int k = 1;

    int expected = 700;
    int actual = BellmanFord.bfFind(n, edges, src, dest, k);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  void testBfFindSample2() {
    int n = 3;
    int[][] edges =
        new int[][] {
          new int[] {0, 1, 100},
          new int[] {1, 2, 100},
          new int[] {0, 2, 500}
        };
    int src = 0;
    int dest = 2;
    int k = 1;

    int expected = 200;
    int actual = BellmanFord.bfFind(n, edges, src, dest, k);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource({
    "0, 1, 5, 4",
    "1, 7, 2, 3",
    "3, 4, 3, 7",
    "10, 14, 2, 7",
    "1, 14, 7, 4",
    "8, 12, 2, 5",
    "9, 13, 5, 8",
    "2, 7, 4, 4",
    "3, 8, 2, 5",
    "12, 11, 1, 7",
    "2, 6, 1, -1",
    "2, 6, 2, 6"
  })
  void testBfFindLarge(int src, int dest, int k, int expected) {
    int actual = BellmanFord.bfFind(LARGE_TEST_N, LARGE_TEST_EDGES, src, dest, k);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }
}
