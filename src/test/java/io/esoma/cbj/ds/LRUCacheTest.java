package io.esoma.cbj.ds;

import io.esoma.cbj.util.Banner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class LRUCacheTest {

  private static final String START = "LRU Cache Test Start";
  private static final String END = "LRU Cache Test End";

  @BeforeClass
  public static void setUpBeforeClass() {
    System.out.println(Banner.getTitleBanner(START, 3));
  }

  @AfterClass
  public static void tearDownAfterClass() {
    System.out.println(Banner.getTitleBanner(END, 3));
    System.out.println();
  }

  @Test
  public void testLRUCacheSmallCap() {
    final Object o1 = 12;
    final Object o2 = "sakura";
    final Object o3 = "8899";
    final Object o4 = new Object();
    final Object o5 = new ArrayList<>(1);
    final Object o6 = new Thread(() -> {});
    final Object o7 = 7.9985;
    final Object o8 = new ReentrantLock();
    final Object o9 = new LRUCache<Integer>(1);
    final Object o10 = Stream.of(0, 1, 2);

    LRUCache<Object> lruCache = new LRUCache<>(3);

    assertNull(lruCache.get(0));
    assertTrue(lruCache.put(1, o1));
    assertSame(o1, lruCache.get(1));
    assertFalse(lruCache.put(1, o10)); // 1

    assertTrue(lruCache.put(2, o2));
    assertTrue(lruCache.put(3, o3));
    assertTrue(lruCache.put(4, o4)); // 4, 3, 2
    assertNull(lruCache.get(1));
    assertSame(o2, lruCache.get(2)); // 2, 4, 3
    assertTrue(lruCache.put(5, o5)); // 5, 2, 4
    assertFalse(lruCache.put(4, o10)); // 4, 5, 2
    assertNull(lruCache.get(3));

    assertTrue(lruCache.put(8, o8));
    assertTrue(lruCache.put(7, o7)); // 7, 8, 4
    assertNull(lruCache.get(5));
    assertNull(lruCache.get(6));
    assertFalse(lruCache.put(4, o4)); // 4, 7, 8

    assertTrue(lruCache.put(6, o6)); // 6, 4, 7
    assertNull(lruCache.get(8));
    assertSame(o7, lruCache.get(7)); // 7, 6, 4
    assertTrue(lruCache.put(9, o9)); // 9, 7, 6
    assertSame(o9, lruCache.get(9));
    assertNull(lruCache.get(4));

    assertTrue(lruCache.put(1, o1));
    assertTrue(lruCache.put(5, o5)); // 5, 1, 9
    assertNull(lruCache.get(7));
    assertNull(lruCache.get(6));
    assertFalse(lruCache.put(9, o10));
    assertSame(o10, lruCache.get(9)); // 9, 5, 1

    assertTrue(lruCache.put(6, o6)); // 6, 9, 5
    assertNull(lruCache.get(1));
    assertSame(o5, lruCache.get(5));
  }

  @Test
  public void testLRUCacheLargeCap() {
    final String prefix = "lru_str_";

    LRUCache<String> lruCache = new LRUCache<>(2022);
    for (int i = 1; i <= 3033; ++i) {
      assertTrue(lruCache.put(i, prefix + i));
    }

    // 3033, 3032 ... 1013, 1012
    assertNull(lruCache.get(10));
    assertNull(lruCache.get(577));
    assertNull(lruCache.get(1009));
    assertEquals(prefix + 1082, lruCache.get(1082));
    assertEquals(prefix + 2066, lruCache.get(2066));
    assertEquals(prefix + 3001, lruCache.get(3001));

    // 3001, 2066, 1082, 3033, 3032 ... 1012
    for (int i = 9999; i <= 11111; ++i) {
      assertTrue(lruCache.put(i, prefix + i));
    }

    // 11111, 11110 ... 9999, 3001, 2066, 1082, 3033, 3032 ...
    assertNull(lruCache.get(1016));
    assertNull(lruCache.get(1899));
    assertEquals(prefix + 2999, lruCache.get(2999));

    for (int i = -1; i >= -2021; --i) {
      assertTrue(lruCache.put(i, prefix + i));
    }

    // -2021, -2020 ... -3, -2, -1, 2999
    assertNull(lruCache.get(2000));
    assertNull(lruCache.get(3000));
    assertNull(lruCache.get(9000));
    assertNull(lruCache.get(10000));
    assertEquals(prefix + 2999, lruCache.get(2999));
  }
}
