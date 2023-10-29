package io.esoma.cbj.prob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class SubArrayImbalanceTest {

    @Test
    void testGetTotalImbalance1() {
        int[] array = new int[] {988};
        long expected = 0L;
        long actual = SubArrayImbalance.getTotalImbalance(array);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testGetTotalImbalance2() {
        int[] array = new int[] {1, 2, 3};
        long expected = 4L;
        long actual = SubArrayImbalance.getTotalImbalance(array);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testGetTotalImbalance3() {
        int[] array = new int[] {10, 9, 8, 7};
        long expected = 10L;
        long actual = SubArrayImbalance.getTotalImbalance(array);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testGetTotalImbalance4() {
        int[] array = new int[] {5, 2, 3, 7, 9};
        long expected = 43L;
        long actual = SubArrayImbalance.getTotalImbalance(array);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testGetTotalImbalance5() {
        int[] array = new int[] {
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1
        };
        long expected = 0L;
        long actual = SubArrayImbalance.getTotalImbalance(array);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }
}
