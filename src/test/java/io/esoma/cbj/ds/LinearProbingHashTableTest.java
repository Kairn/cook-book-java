package io.esoma.cbj.ds;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class LinearProbingHashTableTest {

  private static LinearProbingHashTable<Integer, Character> lpTable;

  @BeforeEach
  void setUp() {
    lpTable = new LinearProbingHashTable<>();
  }

  @AfterEach
  void tearDown() {
    lpTable = null;
  }

  @Test
  void testPutSimple() {
    assertTrue(lpTable.put(0, '0'));
    assertTrue(lpTable.put(1, '1'));
    assertTrue(lpTable.put(2, '2'));
    assertTrue(lpTable.put(3, '3'));
    assertTrue(lpTable.put(4, '4'));
    assertTrue(lpTable.put(5, '5'));
    assertTrue(lpTable.put(6, '6'));
    assertTrue(lpTable.put(7, '7'));
    assertTrue(lpTable.put(8, '8'));
    assertTrue(lpTable.put(9, '9'));
    assertTrue(lpTable.put(10, 'A'));

    assertEquals(11, lpTable.size());
    assertTrue(lpTable.containsKey(2));
    assertTrue(lpTable.containsKey(4));
    assertTrue(lpTable.containsKey(6));
    assertTrue(lpTable.containsKey(8));
    assertTrue(lpTable.containsKey(10));
    assertFalse(lpTable.containsKey(500));
    assertNull(lpTable.get(300));
    assertEquals('1', lpTable.get(1).charValue());
    assertEquals('3', lpTable.get(3).charValue());
    assertEquals('5', lpTable.get(5).charValue());
    assertEquals('7', lpTable.get(7).charValue());
    assertEquals('9', lpTable.get(9).charValue());

    assertFalse(lpTable.put(1, '!'));
    assertFalse(lpTable.put(3, '#'));
    assertFalse(lpTable.put(5, '%'));
    assertFalse(lpTable.put(7, '&'));
    assertFalse(lpTable.put(9, '('));

    assertEquals(11, lpTable.size());
    assertEquals('!', lpTable.get(1).charValue());
    assertEquals('#', lpTable.get(3).charValue());
    assertEquals('%', lpTable.get(5).charValue());
    assertEquals('&', lpTable.get(7).charValue());
    assertEquals('(', lpTable.get(9).charValue());

    Logger.debug(lpTable);
  }

  @Test
  void testPuts() {
    for (int i = 0; i < 200; ++i) {
      assertTrue(lpTable.put(i, 'a'));
    }
    assertEquals(200, lpTable.size());

    for (int i = 100; i < 300; ++i) {
      lpTable.put(i, 'b');
    }
    assertEquals(300, lpTable.size());

    assertTrue(lpTable.containsKey(0));
    assertTrue(lpTable.containsKey(150));
    assertTrue(lpTable.containsKey(299));
    assertFalse(lpTable.containsKey(300));
    assertEquals('a', lpTable.get(50).charValue());
    assertEquals('a', lpTable.get(99).charValue());
    assertEquals('b', lpTable.get(200).charValue());
    assertEquals('b', lpTable.get(266).charValue());
  }

  @Test
  void testDeleteSimple() {
    assertFalse(lpTable.delete(0));
    assertTrue(lpTable.put(5000, 'a'));
    assertTrue(lpTable.put(6000, 'b'));
    assertTrue(lpTable.put(7000, 'c'));
    assertTrue(lpTable.put(555, 'd'));
    assertTrue(lpTable.put(333, 'e'));
    assertTrue(lpTable.put(222, 'f'));
    assertTrue(lpTable.put(1234, 'g'));
    assertTrue(lpTable.put(Integer.MAX_VALUE, 'h'));
    assertTrue(lpTable.put(Integer.MIN_VALUE, 'i'));
    assertTrue(lpTable.put(299, 'j'));
    assertTrue(lpTable.put(399, 'k'));
    assertTrue(lpTable.put(499, 'l'));
    assertTrue(lpTable.put(200, 'm'));
    assertTrue(lpTable.put(300, 'n'));
    assertTrue(lpTable.put(750, 'o'));
    assertTrue(lpTable.put(9999, 'p'));

    assertEquals(16, lpTable.size());

    assertFalse(lpTable.delete(599));
    assertFalse(lpTable.delete(999));
    assertTrue(lpTable.delete(9999));
    assertTrue(lpTable.delete(299));
    assertTrue(lpTable.delete(200));
    assertTrue(lpTable.delete(Integer.MAX_VALUE));

    assertEquals(12, lpTable.size());
    assertEquals('a', lpTable.get(5000).charValue());
    assertEquals('c', lpTable.get(7000).charValue());
    assertEquals('d', lpTable.get(555).charValue());
    assertEquals('g', lpTable.get(1234).charValue());
    assertEquals('i', lpTable.get(Integer.MIN_VALUE).charValue());
    assertEquals('o', lpTable.get(750).charValue());
    assertNull(lpTable.get(200));

    for (int i = 600; i < 701; ++i) {
      assertTrue(lpTable.put(i, '6'));
    }
    assertEquals(113, lpTable.size());

    for (int i = 610; i < 700; ++i) {
      assertTrue(lpTable.delete(i));
    }
    assertEquals(23, lpTable.size());

    Logger.debug(lpTable);
  }

  @Test
  void testDeletes() {
    for (int i = 0; i < 512; ++i) {
      assertTrue(lpTable.put(i, (char) i));
    }
    assertEquals(512, lpTable.size());

    for (int i = 256; i < 513; ++i) {
      lpTable.delete(i);
    }
    assertEquals(256, lpTable.size());

    for (int i = 0; i < 1025; ++i) {
      lpTable.put(i, (char) i);
    }
    assertEquals(1025, lpTable.size());

    for (int i = 512; i < 888; ++i) {
      assertTrue(lpTable.delete(i));
    }

    assertEquals((char) 3, lpTable.get(3).charValue());
    assertEquals((char) 55, lpTable.get(55).charValue());
    assertEquals((char) 222, lpTable.get(222).charValue());
    assertEquals((char) 277, lpTable.get(277).charValue());
    assertEquals((char) 311, lpTable.get(311).charValue());
    assertEquals((char) 478, lpTable.get(478).charValue());
    assertEquals((char) 510, lpTable.get(510).charValue());
    assertEquals((char) 888, lpTable.get(888).charValue());
    assertEquals((char) 999, lpTable.get(999).charValue());
    assertEquals((char) 1000, lpTable.get(1000).charValue());
    assertEquals((char) 1024, lpTable.get(1024).charValue());
    assertNull(lpTable.get(1025));
    assertNull(lpTable.get(-1));
    assertNull(lpTable.get(-512));
    assertNull(lpTable.get(887));
  }

  @Test
  void testResizing() {
    for (int i = 0; i < 656; ++i) {
      lpTable.put(i, (char) (i + 1));
    }
    assertEquals(656, lpTable.size());

    for (int i = 0; i < 656; ++i) {
      if (i <= 555 || i >= 560) {
        assertTrue(lpTable.delete(i));
      }
    }

    assertEquals(4, lpTable.size());
    assertEquals((char) 557, lpTable.get(556).charValue());
    assertEquals((char) 558, lpTable.get(557).charValue());
    assertEquals((char) 559, lpTable.get(558).charValue());
    assertEquals((char) 560, lpTable.get(559).charValue());
  }

  @Test
  void testLargeCase() {
    for (int i = -65536; i < 65539; ++i) {
      assertTrue(lpTable.put(i, '*'));
      if (i % 10000 == 0) {
        Logger.info(i);
      }
    }
    assertEquals(131075, lpTable.size());

    for (int i = -5000; i < 5000; ++i) {
      assertFalse(lpTable.put(i, ')'));
    }
    assertEquals(131075, lpTable.size());
    assertEquals('*', lpTable.get(-65523).charValue());
    assertEquals('*', lpTable.get(-50000).charValue());
    assertEquals('*', lpTable.get(-10000).charValue());
    assertEquals('*', lpTable.get(-5001).charValue());
    assertEquals(')', lpTable.get(-5000).charValue());
    assertEquals(')', lpTable.get(0).charValue());
    assertEquals(')', lpTable.get(4999).charValue());

    for (int i = 2500; i < 32178; ++i) {
      assertTrue(lpTable.delete(i));
    }
    assertEquals(101397, lpTable.size());

    for (int i = -50000; i < -32178; ++i) {
      assertTrue(lpTable.delete(i));
    }
    assertEquals(83575, lpTable.size());

    assertTrue(lpTable.containsKey(-65536));
    assertTrue(lpTable.containsKey(1));
    assertTrue(lpTable.containsKey(-10));
    assertTrue(lpTable.containsKey(1444));
    assertTrue(lpTable.containsKey(-20000));
    assertTrue(lpTable.containsKey(65538));
  }
}
