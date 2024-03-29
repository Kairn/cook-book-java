package io.esoma.cbj.algo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class QuickSelectTest {

    @Test
    void testFindKth1() {
        int[] array = new int[] {5, 9, 8, 10, 5, 8, 1, 8, 7, 1};
        int k = 6;
        int expected = 8;
        int found = QuickSelect.findKthSmallest(array, k);
        Logger.debug(found);
        assertEquals(expected, found);
    }

    @Test
    void testFindKth2() {
        int[] array = new int[] {20, 4, 8, 1, 10, 4, 16, 14, 18, 7, 15, 1, 5, 7, 9, 12, 4, 11, 15, 12};
        int k = 1;
        int expected = 1;
        int found = QuickSelect.findKthSmallest(array, k);
        Logger.debug(found);
        assertEquals(expected, found);
    }

    @Test
    void testFindKth3() {
        int[] array = new int[] {
            19, 15, 13, 8, 4, 16, 17, 10, 16, 7, 14, 10, 3, 7, 19, 18, 18, 18, 4, 6, 5, 10, 20, 7, 12, 20, 7, 6, 19, 3,
            16, 19, 9, 18, 19
        };
        int k = 30;
        int expected = 19;
        int found = QuickSelect.findKthSmallest(array, k);
        Logger.debug(found);
        assertEquals(expected, found);
    }

    @Test
    void testFindKth4() {
        int[] array = new int[] {-487, 1657, 1806, -262, 1381, 1294, -124, -974, 285, 1165, 1631, 320, 931, -70, 440};
        int k = 4;
        int expected = -124;
        int found = QuickSelect.findKthSmallest(array, k);
        Logger.debug(found);
        assertEquals(expected, found);
    }

    @Test
    void testFindKth5() {
        int[] array = new int[] {-1181, -339, 1725, 851, 257, 1896, 1517, 1527, 256, 1017, -205, 55, 1782, 607, 879};
        int k = 10;
        int expected = 1017;
        int found = QuickSelect.findKthSmallest(array, k);
        Logger.debug(found);
        assertEquals(expected, found);
    }

    @Test
    void testFindKth6() {
        int[] array = new int[] {
            -33823, -15963, -38334, -46335, -96163, -76210, -57797, -75574, -85646, -33865, -57678,
            -97684, -86654, -70048, -56841, -20378, -39564, -64560, -33597, -39307, -7121, -46283,
            -9988, -52895, -73041
        };
        int k = 17;
        int expected = -39307;
        int found = QuickSelect.findKthSmallest(array, k);
        Logger.debug(found);
        assertEquals(expected, found);
    }

    @Test
    void testFindKth7() {
        int[] array = new int[] {
            0, 2, 1, 1, 1, 0, 0, 1, 0, 1, 2, 1, 0, 1, 2, 0, 1, 0, 1, 1, 2, 0, 1, 2, 2, 1, 1, 1, 0, 0, 2, 1, 2, 0, 0, 1,
            1, 0, 1, 1, 0, 2, 1, 1, 0, 2, 2, 2, 0, 0
        };
        int k = 9;
        int expected = 0;
        int found = QuickSelect.findKthSmallest(array, k);
        Logger.debug(found);
        assertEquals(expected, found);
    }

    @Test
    void testFindKth8() {
        int[] array = new int[] {
            -6, 6, -7, -8, 0, -4, 5, 8, -2, -1, -3, 7, 10, 7, -3, 7, 2, 7, 2, 2, 7, 5, -5, 10, -8, 4, -4, -2, -4, -6, 9,
            -10, -10, -6, -5, -2, 6, 5, 5, -4, 4, 3, -7, -4, -2, 3, 8, -5, 9, 2, 0, -7, -5, 4, -4, -3, -3, 4, -10, 2,
            -4, 5, -5, -3, 6, 3, 0, -1, 6, 4, 4, -8, 5, -10, 8, -4, 6, -5, -5, 5, 7, -4, 9, -10, 2, -6, 0, 5, -5, 3, 1,
            9, 6, -6, -5, -8, -8, 0, 0, 4, 1, 10, 5, -3, 9, -7, 7, -2, 4, 0, 2, 7, -8, 10, -5, -9, 0, -10, 4, -1, -4, 8,
            4, 4, 1, -6, -8, -10, 4, 6, -1, -1, -1, -8, 10, 5, -9, -10, -2, 1, 4, -3, 7, 1, -5, 2, 10, 3, 1, -3
        };
        int k = 61;
        int expected = -2;
        int found = QuickSelect.findKthSmallest(array, k);
        Logger.debug(found);
        assertEquals(expected, found);
    }

    @Test
    void testFindKth9() {
        int[] array = new int[] {0};
        int k = 1;
        int expected = 0;
        int found = QuickSelect.findKthSmallest(array, k);
        Logger.debug(found);
        assertEquals(expected, found);
    }

    @Test
    void testFindKth10() {
        int[] array = new int[] {-90, 5003};
        int k = 2;
        int expected = 5003;
        int found = QuickSelect.findKthSmallest(array, k);
        Logger.debug(found);
        assertEquals(expected, found);
    }

    @Test
    void testFindKth11() {
        int[] array = new int[] {
            5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
            5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5
        };
        int k = 29;
        int expected = 5;
        int found = QuickSelect.findKthSmallest(array, k);
        Logger.debug(found);
        assertEquals(expected, found);
    }
}
