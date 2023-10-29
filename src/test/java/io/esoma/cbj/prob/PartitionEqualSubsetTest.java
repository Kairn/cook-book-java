package io.esoma.cbj.prob;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class PartitionEqualSubsetTest {

    @Test
    void testJudge1() {
        int[] array = new int[] {1, 5, 11, 5};
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertTrue(actual);
    }

    @Test
    void testJudge2() {
        int[] array = new int[] {
            50, 58, 95, 29, 95, 92, 41, 23, 91, 13, 99, 46, 76, 53, 88, 43, 11, 2, 77, 10, 17, 36, 23, 86, 81, 36, 30,
            81, 94, 69, 59, 52, 17, 71, 37, 7, 28, 89, 26, 89, 1, 78, 15, 69, 13, 18, 42, 11, 26, 63, 39, 54, 41, 74,
            39, 24, 94, 32, 80, 34, 79, 34, 87, 78, 84, 32, 23, 31, 96, 59, 49, 28, 66, 10, 5, 94, 34, 27, 47, 77, 56,
            1, 4, 67, 82, 4, 2, 7, 93, 63, 23, 78, 73, 63, 62, 85, 72, 69, 16, 85
        };
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertTrue(actual);
    }

    @Test
    void testJudge3() {
        int[] array = new int[] {8, 8};
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertTrue(actual);
    }

    @Test
    void testJudge4() {
        int[] array = new int[] {
            4, 4, 4, 4, 5, 4, 5, 5, 5, 5, 4, 5, 4, 5, 4, 5, 4, 5, 5, 4, 4, 5, 4, 5, 5, 5, 5, 4, 4, 5, 5, 5, 4, 5, 5, 4,
            4, 4, 5, 4, 4, 4, 4, 5
        };
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertTrue(actual);
    }

    @Test
    void testJudge5() {
        int[] array = new int[] {
            35, 35, 34, 35, 34, 35, 33, 33, 33, 33, 33, 33, 33, 35, 35, 35, 35, 33, 33, 35, 34, 34,
            34, 33, 34, 33, 34, 35, 35, 33, 33, 33, 34, 34, 35, 35, 33, 33, 33, 33, 33, 35, 33, 33,
            34, 34, 34, 35, 35, 33, 33, 34, 35, 34, 33, 35, 34, 35, 33, 35, 35, 34, 33, 33, 34, 33,
            34, 34, 35, 34, 33, 33, 35, 34, 35, 33, 35, 34, 34, 34, 33, 33, 34, 35, 34, 33, 34, 33,
            33, 35, 34, 34, 35, 35, 33, 33, 33, 35, 35, 35, 34, 33, 35, 35, 35, 35, 34, 35, 34, 33,
            33, 35, 34, 34, 35, 33, 33, 34, 34, 33, 34, 33, 34, 35, 33, 33, 34, 35, 33, 34, 35, 34,
            35, 35, 33, 34, 34, 35, 33, 35, 33, 33, 34, 35, 35, 35, 35, 33, 33, 34, 34, 35, 34, 35,
            35, 34, 34
        };
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertTrue(actual);
    }

    @Test
    void testJudge6() {
        int[] array = new int[] {1, 2, 3, 5};
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertFalse(actual);
    }

    @Test
    void testJudge7() {
        int[] array = new int[] {
            89, 80, 83, 82, 80, 87, 87, 88, 88, 83, 87, 89, 87, 86, 87, 90, 84, 88, 83, 88, 90, 86,
            85, 83, 90, 88, 90, 80, 88, 87, 90, 88, 89, 82, 84, 87, 90, 89, 80, 82, 80, 81, 81, 84,
            86, 83, 81, 83, 82, 84, 87, 86, 82, 87, 85, 82, 88, 80, 85, 88, 82, 86, 86, 86, 84, 85,
            80, 81, 84, 87, 80, 82, 80, 82, 87, 87, 88, 81, 82, 90, 83, 84, 80, 85, 83, 83, 88, 85,
            84, 89, 83, 87, 81, 81, 87, 85, 86, 86, 85, 85, 84, 86, 87, 82, 84, 86, 88, 85, 80, 83,
            90, 86, 84, 89, 90, 88, 89, 85, 82, 87, 89, 83, 81, 82, 85, 86, 81, 84, 90, 83, 85, 82,
            87, 89, 90, 80, 80, 87, 89, 84, 89, 85, 82, 85, 85, 86, 80, 84, 86, 90, 87, 84, 88, 83,
            80, 83, 87, 88, 87, 89, 86, 82, 90, 80, 89, 80, 80, 85, 88, 85, 84, 83, 81, 86, 83, 89,
            89, 90, 87, 85
        };
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertFalse(actual);
    }

    @Test
    void testJudge8() {
        int[] array = new int[] {91, 46, 58, 41, 40, 92, 43, 26, 70, 26, 41, 75, 39, 50, 93, 81, 27, 65, 5, 34};
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertFalse(actual);
    }

    @Test
    void testJudge9() {
        int[] array = new int[] {
            1, 2, 3, 1, 1, 3, 3, 1, 1, 1, 1, 3, 1, 1, 3, 3, 3, 2, 2, 1, 3, 1, 3, 2, 2, 3, 1, 1, 1, 1, 3, 3, 1, 3, 2, 1,
            1, 2, 1, 2, 3, 2, 2, 3, 1, 3, 3, 2, 1, 1
        };
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertFalse(actual);
    }

    @Test
    void testJudge10() {
        int[] array = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertFalse(actual);
    }

    @Test
    void testJudge11() {
        int[] array = new int[] {70};
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertFalse(actual);
    }

    @Test
    void testJudge12() {
        int[] array = new int[] {1, 2};
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertFalse(actual);
    }

    @Test
    void testJudge13() {
        int[] array = new int[] {
            12, 5, 3, 11, 3, 4, 4, 1, 3, 9, 4, 9, 7, 9, 13, 1, 8, 6, 15, 12, 11, 4, 3, 8, 1, 5, 8, 1, 3, 3, 12, 3, 4, 4,
            8, 8
        };
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertFalse(actual);
    }

    @Test
    void testJudge14() {
        int[] array = new int[] {
            90, 81, 72, 70, 9, 19, 3, 63, 24, 3, 6, 29, 23, 68, 42, 1, 19, 3, 20, 17, 18, 28, 70, 88, 53, 10, 21, 68,
            93, 14, 12, 88, 15, 63, 42, 45, 95, 6, 73, 24, 99, 45, 62, 11, 68, 71, 37, 42, 73, 25, 81, 26, 21, 52, 5,
            29, 21, 33, 32, 2, 16, 64, 96, 52, 33, 12, 17, 44, 41, 99, 66, 50, 96, 4, 26, 9, 82, 82, 4, 86, 28, 61, 51,
            35, 65, 18, 83, 40, 55, 2, 58, 15, 86, 32, 8, 20, 16, 45, 30, 47, 69, 43, 33, 62, 22, 99, 18, 72, 42, 1, 54,
            10, 75, 72, 35, 95, 67, 16, 13, 13, 49, 89, 45, 18, 39, 34, 34, 89, 61, 12, 34, 3, 80, 94, 97, 85, 52, 23,
            82, 38, 43, 71, 97, 9, 25, 54, 29, 55, 7, 20, 6, 12, 52, 90, 42, 80, 72, 60, 57, 97, 83
        };
        boolean actual = PartitionEqualSubset.judge(array);
        Logger.debug(actual);
        assertFalse(actual);
    }
}
