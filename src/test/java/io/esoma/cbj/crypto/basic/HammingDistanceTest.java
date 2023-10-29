package io.esoma.cbj.crypto.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class HammingDistanceTest {

    @Test
    void testComputeStringString() {
        final String s1 = "this is a test";
        final String s2 = "wokka wokka!!!";
        final int expected = 37;
        int actual = HammingDistance.compute(s1, s2);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }
}
