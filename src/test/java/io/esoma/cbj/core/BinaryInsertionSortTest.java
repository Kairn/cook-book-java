package io.esoma.cbj.core;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class BinaryInsertionSortTest {

    @Test
    void testSortOnline1() {
        int[] unsorted = new int[] {
            132, 412, 694, 898, 376, 575, 25, 653, 502, 887, 375, 204, 270, 945, 842, 770, 881, 34, 488, 773, 981, -36
        };
        int[] sorted = new int[] {
            -36, 25, 34, 132, 204, 270, 375, 376, 412, 488, 502, 575, 653, 694, 770, 773, 842, 881, 887, 898, 945, 981
        };
        BinaryInsertionSort.sortOnline(unsorted, 0, unsorted.length - 1, 0);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, unsorted);
    }

    @Test
    void testSortOnline2() {
        int[] unsorted = new int[] {2, 3, 1, 2, 2, 2, 1, 2, 2, 1, 2, 3, 2, 3, 2, 2, 3, 1, 3, 1, 3, 2, 1, 1, 3, 1, 1};
        int[] sorted = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3};
        BinaryInsertionSort.sortOnline(unsorted, 0, unsorted.length - 1, 0);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, unsorted);
    }

    @Test
    void testSortOnline3() {
        int[] unsorted = new int[] {4, 5, 6, 7, 8};
        int[] sorted = new int[] {4, 5, 6, 7, 8};
        BinaryInsertionSort.sortOnline(unsorted, 0, unsorted.length - 1, 0);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, unsorted);
    }

    @Test
    void testSortOnline4() {
        int[] unsorted = new int[] {1, 2, 2, 4, 3, 3, 13, 14, 14, 5, 7, 5, 5, 13, 13, 4, 13, 4, 2, 4, 8, 12, 13, 14, 4};
        int[] sorted = new int[] {1, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 7, 8, 12, 13, 13, 13, 13, 13, 14, 14, 14};
        BinaryInsertionSort.sortOnline(unsorted, 0, unsorted.length - 1, 4);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, unsorted);
    }

    @Test
    void testSortOnline5() {
        int[] unsorted = new int[] {15, 15, 13, 13, 13, 11, 10, 10, 9, 8, 8, 8, 8, 7, 6, 5, 5, 4, 4, 4};
        int[] sorted = new int[] {4, 4, 4, 5, 5, 6, 7, 8, 8, 8, 8, 9, 10, 10, 11, 13, 13, 13, 15, 15};
        BinaryInsertionSort.sortOnline(unsorted, 0, unsorted.length - 1, 0);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, unsorted);
    }

    @Test
    void testSortOnline6() {
        int[] unsorted = new int[] {
            33735, 35433, 35749, 34310, 35516, 35449, 34072, 34284, 34156, 34963, 35199, 33815, 33626, 33723, 33595,
            35316
        };
        int[] sorted = new int[] {
            33595, 33626, 33723, 33735, 33815, 34072, 34156, 34284, 34310, 34963, 35199, 35316, 35433, 35449, 35516,
            35749
        };
        BinaryInsertionSort.sortOnline(unsorted, 0, unsorted.length - 1, 0);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, unsorted);
    }

    @Test
    void testSortOnline7() {
        int[] unsorted = new int[] {-24, 68, -66, -67, 48, -53, -51, -48, -49, -74, 54, 51, -47, -45, 56, 59, 69, -47};
        int[] sorted = new int[] {-74, -67, -66, -53, -51, -49, -48, -47, -47, -45, -24, 48, 51, 54, 56, 59, 68, 69};
        BinaryInsertionSort.sortOnline(unsorted, 0, unsorted.length - 1, 0);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, unsorted);
    }

    @Test
    void testSortOnline8() {
        int[] unsorted = new int[] {-64, -31, -17, -16, -16, -14, -13, 6, 14, 26, 50, 52, 72, 54, 68, 69};
        int[] sorted = new int[] {-64, -31, -17, -16, -16, -14, -13, 6, 14, 26, 50, 52, 54, 68, 69, 72};
        BinaryInsertionSort.sortOnline(unsorted, 0, unsorted.length - 1, 12);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, unsorted);
    }

    @Test
    void testSortOnline9() {
        int[] unsorted = new int[] {10, 18, 12, 10, 11, 16, 17, 9, 15, 15, 18, 14, 12, 18, 14, 17};
        int[] sorted = new int[] {9, 10, 10, 11, 12, 12, 14, 14, 15, 15, 16, 17, 17, 18, 18, 18};
        BinaryInsertionSort.sortOnline(unsorted, 0, unsorted.length - 1, 1);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, unsorted);
    }

    @Test
    void testSortOnline10() {
        int[] unsorted = new int[] {2, 9, 19, 19, 19, 17, 15, 13, 13, 11, 10};
        int[] sorted = new int[] {2, 9, 10, 11, 13, 13, 15, 17, 19, 19, 19};
        BinaryInsertionSort.sortOnline(unsorted, 0, unsorted.length - 1, 4);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, unsorted);
    }
}
