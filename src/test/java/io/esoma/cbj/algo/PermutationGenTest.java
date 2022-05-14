package io.esoma.cbj.algo;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PermutationGenTest {

  @Test
  public void testPermGenSmall() {
    PermutationGen gen = new PermutationGen(3);

    assertArrayEquals(new int[] {1, 2, 3}, gen.getNext());
    assertArrayEquals(new int[] {1, 3, 2}, gen.getNext());
    assertArrayEquals(new int[] {2, 1, 3}, gen.getNext());
    assertArrayEquals(new int[] {2, 3, 1}, gen.getNext());
    assertArrayEquals(new int[] {3, 1, 2}, gen.getNext());
    assertArrayEquals(new int[] {3, 2, 1}, gen.getNext());
  }

  @Test
  public void testPermGenLarger() {
    PermutationGen gen = new PermutationGen(6);

    assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6}, gen.getNext());
    assertArrayEquals(new int[] {1, 2, 3, 4, 6, 5}, gen.getNext());
    assertArrayEquals(new int[] {1, 2, 3, 5, 4, 6}, gen.getNext());
    assertArrayEquals(new int[] {1, 2, 3, 5, 6, 4}, gen.getNext());
    assertArrayEquals(new int[] {1, 2, 3, 6, 4, 5}, gen.getNext());
    assertArrayEquals(new int[] {1, 2, 3, 6, 5, 4}, gen.getNext());
    assertArrayEquals(new int[] {1, 2, 4, 3, 5, 6}, gen.getNext());
    assertArrayEquals(new int[] {1, 2, 4, 3, 6, 5}, gen.getNext());

    String generated = "124365";
    for (int i = 9; i <= 719; ++i) {
      String next =
          Arrays.stream(gen.getNext()).mapToObj(String::valueOf).collect(Collectors.joining());
      assertTrue(next.compareTo(generated) > 0);
      generated = next;
    }

    assertEquals("654312", generated);
    assertArrayEquals(new int[] {6, 5, 4, 3, 2, 1}, gen.getNext());
  }

  @Test
  public void testPermGenOutOfBounds() {
    PermutationGen gen = new PermutationGen(2);

    assertTrue(gen.hasNext());
    assertArrayEquals(new int[] {1, 2}, gen.getNext());
    assertTrue(gen.hasNext());
    assertArrayEquals(new int[] {2, 1}, gen.getNext());

    assertFalse(gen.hasNext());
    assertThrows(IllegalStateException.class, gen::getNext);
    assertThrows(IllegalStateException.class, gen::getNext);
    assertThrows(IllegalStateException.class, gen::getNext);
  }
}
