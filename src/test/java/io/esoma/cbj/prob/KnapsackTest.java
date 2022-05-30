package io.esoma.cbj.prob;

import org.junit.Test;
import org.tinylog.Logger;

import static org.junit.Assert.assertEquals;

public class KnapsackTest {

  @Test
  public void testFitKs1() {
    int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int limit = 1;
    int expected = 1;
    int actual = Knapsack.fitKs(array, limit);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFitKs2() {
    int[] array = new int[] {5, 10, 80, 95, 11, 57};
    int limit = 29;
    int expected = 26;
    int actual = Knapsack.fitKs(array, limit);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFitKs3() {
    int[] array = new int[] {45, 34, 4, 12, 5, 2};
    int limit = 42;
    int expected = 41;
    int actual = Knapsack.fitKs(array, limit);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFitKs4() {
    int[] array = new int[] {3, 34, 4, 12, 5, 2};
    int limit = 10;
    int expected = 10;
    int actual = Knapsack.fitKs(array, limit);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFitKs5() {
    int[] array = new int[] {99, 273, 212, 1800, 3740, 112, 256, 788};
    int limit = 88;
    int expected = 0;
    int actual = Knapsack.fitKs(array, limit);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFitKs6() {
    int[] array = new int[] {1, 1, 2, 4, 12};
    int limit = 15;
    int expected = 15;
    int actual = Knapsack.fitKs(array, limit);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFitKs7() {
    int[] array = new int[] {2350, 734, 54, 312, 115, 224, 77, 895, 9000, 438, 122, 1256, 33, 108};
    int limit = 5000;
    int expected = 4999;
    int actual = Knapsack.fitKs(array, limit);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testFitKs8() {
    int[] array = new int[] {30, 11, 580, 22, 98, 7, 20, 11, 1, 4, 3, 29, 388, 24};
    int limit = 8888;
    int expected = 1228;
    int actual = Knapsack.fitKs(array, limit);
    Logger.debug(actual);
    assertEquals(expected, actual);
  }
}
