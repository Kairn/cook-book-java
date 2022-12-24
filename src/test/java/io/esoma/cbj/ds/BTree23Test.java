package io.esoma.cbj.ds;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BTree23Test {

  // Hold the tree used for testing.
  private static BTree23 bt23;

  @Before
  public void setUp() {
    bt23 = new BTree23();
  }

  @After
  public void tearDown() {
    bt23 = null;
  }

  @Test
  public void testRandomInserts() {
    bt23.insert(344);
    bt23.insert(24);
    bt23.insert(-98);
    bt23.insert(66);
    bt23.insert(77868);
    bt23.insert(5223);
    assertEquals(6, bt23.size());
    assertTrue(bt23.contains(5223));
    assertTrue(bt23.contains(-98));
    assertTrue(bt23.contains(24));
    assertFalse(bt23.contains(0));

    bt23.insert(43);
    bt23.insert(2);
    bt23.insert(89);
    assertTrue(bt23.insert(0));
    bt23.insert(-6);
    bt23.insert(-443);
    bt23.insert(-938755);
    bt23.insert(Integer.MIN_VALUE);
    bt23.insert(Integer.MAX_VALUE);
    assertFalse(bt23.insert(0));
    assertFalse(bt23.insert(0));
    bt23.insert(5533);
    bt23.insert(-7766);
    bt23.insert(278);
    bt23.insert(-900);
    assertEquals(19, bt23.size());
    assertTrue(bt23.contains(0));
    assertTrue(bt23.contains(Integer.MAX_VALUE));
    assertTrue(bt23.contains(Integer.MIN_VALUE));
    assertTrue(bt23.contains(5533));
    assertTrue(bt23.contains(-6));
  }

  @Test
  public void testUniformInserts() {
    int min = -600;
    int len = 65537;
    int[] array = new int[len];
    for (int i = 0; i < len; ++i) {
      int key = min + i;
      array[i] = key;
      assertTrue(bt23.insert(key));
    }

    assertEquals(len, bt23.size());
    assertArrayEquals(array, bt23.toSortedArray());
  }

  @Test
  public void testDeletes() {
    for (int i = 0; i < 200; ++i) {
      assertTrue(bt23.insert(i));
    }
    assertEquals(200, bt23.size());
    assertTrue(bt23.contains(0));
    assertTrue(bt23.contains(199));

    assertFalse(bt23.delete(200));
    assertTrue(bt23.delete(199));
    assertFalse(bt23.delete(199));
    bt23.delete(199);
    bt23.delete(1);
    bt23.delete(2);
    bt23.delete(3);
    bt23.delete(67);
    bt23.delete(60);
    bt23.delete(5);
    bt23.delete(0);
    bt23.delete(0);
    bt23.delete(100);
    bt23.delete(10000);
    assertFalse(bt23.delete(Integer.MIN_VALUE));
    bt23.delete(-700);
    bt23.delete(-200);
    bt23.delete(198);
    bt23.delete(199);
    assertEquals(190, bt23.size());
    assertFalse(bt23.contains(0));
    assertFalse(bt23.contains(1));
    assertFalse(bt23.contains(2));
    assertFalse(bt23.contains(199));
    assertTrue(bt23.contains(197));
    assertTrue(bt23.contains(196));
    assertTrue(bt23.contains(195));
    assertTrue(bt23.contains(6));
    assertTrue(bt23.contains(66));

    for (int i = -1; i < 99; ++i) {
      if (i < 4) {
        assertTrue(bt23.insert(i));
      } else if (i > 70) {
        assertFalse(bt23.insert(i));
      } else {
        bt23.insert(i);
      }
    }
    assertEquals(198, bt23.size());
    assertTrue(bt23.contains(-1));
    assertTrue(bt23.contains(1));
    assertTrue(bt23.contains(2));
    assertTrue(bt23.contains(3));
    assertTrue(bt23.contains(60));
    assertTrue(bt23.contains(67));
    assertFalse(bt23.contains(199));
    assertFalse(bt23.contains(198));
    assertFalse(bt23.contains(100));
  }
}
