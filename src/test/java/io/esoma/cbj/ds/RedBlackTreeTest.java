package io.esoma.cbj.ds;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tinylog.Logger;

import java.util.List;

import static org.junit.Assert.*;

public class RedBlackTreeTest {

  // Hold the tree used for testing.
  private static RedBlackTree<Integer> rbTree;

  @Before
  public void setUp() {
    rbTree = new RedBlackTree<>();
  }

  @After
  public void tearDown() {
    rbTree = null;
  }

  @Test
  public void testInsertSimple() {
    assertTrue(rbTree.insert(2));
    assertFalse(rbTree.insert(2));
    assertTrue(rbTree.insert(1));
    assertTrue(rbTree.insert(4));
    assertTrue(rbTree.insert(3));
    assertTrue(rbTree.insert(5));
    assertTrue(rbTree.insert(16));
    assertTrue(rbTree.insert(11));
    assertTrue(rbTree.insert(13));
    assertTrue(rbTree.insert(15));
    assertTrue(rbTree.insert(6));
    assertTrue(rbTree.insert(7));
    assertTrue(rbTree.insert(8));
    assertTrue(rbTree.insert(9));
    assertTrue(rbTree.insert(10));
    assertTrue(rbTree.insert(12));
    assertTrue(rbTree.insert(14));
    assertFalse(rbTree.insert(14));

    assertEquals(16, rbTree.size());
    assertTrue(rbTree.contains(1));
    assertTrue(rbTree.contains(16));
    assertTrue(rbTree.contains(8));
    assertFalse(rbTree.contains(-1));

    assertTrue(rbTree.insert(-1));
    assertTrue(rbTree.contains(-1));
    assertEquals(17, rbTree.size());
    assertEquals(
        List.of(-1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16), rbTree.toSortedList());

    Logger.debug(rbTree);
  }

  @Test
  public void testInserts() {
    for (int i = -900; i < 900; ++i) {
      assertTrue(rbTree.insert(i));
    }
    assertEquals(1800, rbTree.size());

    for (int i = 200000; i < 200100; ++i) {
      assertTrue(rbTree.insert(i));
    }
    assertEquals(1900, rbTree.size());

    assertTrue(rbTree.contains(0));
    assertTrue(rbTree.contains(-900));
    assertTrue(rbTree.contains(899));
    assertTrue(rbTree.contains(200099));
    assertTrue(rbTree.contains(200060));
    assertTrue(rbTree.contains(-100));
    assertFalse(rbTree.contains(-9000));
    assertFalse(rbTree.contains(200100));

    assertTrue(rbTree.insert(Integer.MAX_VALUE));
    assertTrue(rbTree.insert(Integer.MIN_VALUE));
    assertEquals(1902, rbTree.size());
    assertTrue(rbTree.contains(Integer.MIN_VALUE));
    assertTrue(rbTree.contains(Integer.MAX_VALUE));
  }

  @Test
  public void testDeleteSimple() {
    for (int i = -8; i < 8; ++i) {
      assertTrue(rbTree.insert(i));
    }
    assertEquals(16, rbTree.size());

    assertFalse(rbTree.delete(17));
    assertFalse(rbTree.delete(-15));
    assertTrue(rbTree.delete(-8));
    assertTrue(rbTree.delete(-7));
    assertTrue(rbTree.delete(7));
    assertTrue(rbTree.delete(0));
    assertTrue(rbTree.delete(1));
    assertFalse(rbTree.delete(1));
    assertFalse(rbTree.delete(1));
    assertTrue(rbTree.delete(-1));
    assertTrue(rbTree.delete(-6));
    assertTrue(rbTree.delete(6));

    assertEquals(8, rbTree.size());
    assertEquals(List.of(-5, -4, -3, -2, 2, 3, 4, 5), rbTree.toSortedList());

    Logger.debug(rbTree);
  }

  @Test
  public void testDeletes() {
    for (int i = 0; i < 1000; ++i) {
      assertTrue(rbTree.insert(i));
    }
    assertEquals(1000, rbTree.size());

    for (int i = 299; i < 700; ++i) {
      assertTrue(rbTree.delete(i));
    }
    assertEquals(599, rbTree.size());
    assertTrue(rbTree.contains(100));
    assertTrue(rbTree.contains(200));
    assertFalse(rbTree.contains(300));
    assertFalse(rbTree.contains(400));
    assertFalse(rbTree.contains(500));
    assertFalse(rbTree.contains(600));
    assertTrue(rbTree.contains(700));
    assertTrue(rbTree.contains(800));
    assertTrue(rbTree.contains(900));
    assertFalse(rbTree.contains(100));

    assertTrue(rbTree.insert(300));
    assertTrue(rbTree.insert(350));
    assertTrue(rbTree.insert(400));
    assertTrue(rbTree.insert(450));
    assertTrue(rbTree.insert(500));
    assertTrue(rbTree.insert(550));
    assertTrue(rbTree.insert(600));
    assertTrue(rbTree.insert(650));
    assertTrue(rbTree.insert(69990));
    assertFalse(rbTree.insert(700));
    assertFalse(rbTree.insert(750));
    assertFalse(rbTree.insert(800));
    assertFalse(rbTree.insert(850));
    assertEquals(608, rbTree.size());

    for (int i = -1000; i < 2000; ++i) {
      rbTree.delete(i);
    }
    assertEquals(1, rbTree.size());
    assertTrue(rbTree.contains(69990));

    Logger.debug(rbTree);
  }

  @Test
  public void testLargeCase() {
    for (int i = -1; i < 65536; ++i) {
      assertTrue(rbTree.insert(i));
      assertFalse(rbTree.insert(1));
    }
    assertEquals(65537, rbTree.size());

    for (int i = -65536; i < 25536; ++i) {
      rbTree.delete(i);
    }
    assertEquals(40000, rbTree.size());

    for (int i = -70000; i < 80000; ++i) {
      rbTree.insert(i);
    }
    assertEquals(150000, rbTree.size());

    for (int i = -100; i < 80000; ++i) {
      assertTrue(rbTree.delete(i));
    }
    assertEquals(69900, rbTree.size());
  }
}
