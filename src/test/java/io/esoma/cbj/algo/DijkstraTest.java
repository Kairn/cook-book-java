package io.esoma.cbj.algo;

import io.esoma.cbj.util.Banner;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class DijkstraTest {

  private static final String START = "Dijkstra Test Start";
  private static final String END = "Dijkstra Test End";

  // Test input.
  private static final int[][] TEST_GRAPH =
      new int[][] {
        {0, 5, -1, 9, -1, -1, -1, 4, -1, -1, -1, -1, -1, -1, 5, -1, -1, 6, -1, 8},
        {5, 0, 2, 3, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 7, -1, -1},
        {-1, 2, 0, 9, -1, -1, -1, -1, -1, 6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {9, 3, 9, 0, -1, 1, -1, 1, -1, 1, -1, 1, -1, -1, -1, -1, -1, -1, 8, -1},
        {-1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 4, -1, 1, -1, 0, 4, -1, -1, 4, -1, -1, -1, 2, -1, 8, -1, -1, -1, 3},
        {-1, -1, -1, -1, -1, 4, 0, 6, -1, 6, -1, -1, 9, -1, -1, 3, -1, 2, -1, -1},
        {4, -1, -1, 1, -1, -1, 6, 0, 9, 1, -1, 3, -1, -1, 7, -1, 5, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, 9, 0, -1, 2, 4, -1, 6, -1, -1, -1, -1, -1, 5},
        {-1, -1, 6, 1, -1, 4, 6, 1, -1, 0, -1, -1, 7, -1, 8, -1, -1, -1, -1, 3},
        {-1, -1, -1, -1, -1, -1, -1, -1, 2, -1, 0, 1, -1, 8, -1, -1, 6, -1, -1, -1},
        {-1, -1, -1, 1, -1, -1, -1, 3, 4, -1, 1, 0, -1, -1, -1, 6, -1, 5, -1, -1},
        {-1, -1, -1, -1, -1, -1, 9, -1, -1, 7, -1, -1, 0, 2, -1, -1, -1, -1, 9, -1},
        {-1, -1, -1, -1, -1, 2, -1, -1, 6, -1, 8, -1, 2, 0, -1, -1, -1, -1, -1, -1},
        {5, -1, -1, -1, -1, -1, -1, 7, -1, 8, -1, -1, -1, -1, 0, 1, 1, -1, -1, -1},
        {-1, -1, -1, -1, -1, 8, 3, -1, -1, -1, -1, 6, -1, -1, 1, 0, -1, 3, -1, 5},
        {-1, -1, -1, -1, -1, -1, -1, 5, -1, -1, 6, -1, -1, -1, 1, -1, 0, -1, 2, -1},
        {6, 7, -1, -1, -1, -1, 2, -1, -1, -1, -1, 5, -1, -1, -1, 3, -1, 0, -1, 1},
        {-1, -1, -1, 8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 9, -1, -1, 2, -1, 0, -1},
        {8, -1, -1, -1, -1, 3, -1, -1, 5, 3, -1, -1, -1, -1, -1, 5, -1, 1, -1, 0}
      };

  // Test result.
  private static int[] result = null;

  @BeforeClass
  public static void setUpBeforeClass() {
    System.out.println(Banner.getTitleBanner(START, 3));
    // Run test.
    result = Dijkstra.djFind(TEST_GRAPH);
  }

  @AfterClass
  public static void tearDownAfterClass() {
    System.out.println(Banner.getTitleBanner(END, 3));
    System.out.println();
  }

  @Test
  @Parameters({
    "0, 0", "1, 5", "2, 7", "4, -1", "5, 6", "6, 8", "7, 4", "9, 5", "10, 7", "12, 10", "13, 8",
    "16, 6", "17, 6", "8, 9", "19, 7"
  })
  public void testDjFind(int index, int dist) {
    System.out.println(result[index]);
    assertEquals(dist, result[index]);
  }
}
