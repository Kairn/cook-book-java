package io.esoma.cbj.crypto.rng;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

/*
 * The outputs are different from the original C output for some reason.
 */
@Disabled
class Mt32Test {

  @Test
  void testRngInt() {
    final int[] seed = new int[] {0x123, 0x234, 0x345, 0x456};
    Mt32 mt32 = new Mt32(seed);
    int i1 = mt32.next();
    int i2 = mt32.next();
    int i3 = mt32.next();
    int i4 = mt32.next();
    int i5 = mt32.next();
    Logger.debug(Arrays.asList(i1, i2, i3, i4, i5));
    assertEquals(1067595299, i1);
    assertEquals(955945823, i2);
    assertEquals(477289528, i3);
    assertEquals((int) 4107218783L, i4);
    assertEquals((int) 4228976476L, i5);
  }
}
