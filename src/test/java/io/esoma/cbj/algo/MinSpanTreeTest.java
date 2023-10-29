package io.esoma.cbj.algo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class MinSpanTreeTest {

    @Test
    void testCalcKruskal1() {
        int n = 5;
        int[][] edges = new int[][] {{1, 2, 3}, {1, 3, 4}, {4, 2, 6}, {5, 2, 2}, {2, 3, 5}, {3, 5, 7}};
        int expected = 15;
        int actual = MinSpanTree.calcKruskal(n, edges);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testCalcKruskal2() {
        int n = 4;
        int[][] edges = new int[][] {{1, 2, 5}, {1, 3, 3}, {4, 1, 6}, {2, 4, 7}, {3, 2, 4}, {3, 4, 5}};
        int expected = 12;
        int actual = MinSpanTree.calcKruskal(n, edges);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testCalcKruskal3() {
        int n = 5;
        int[][] edges =
                new int[][] {{1, 2, 20}, {1, 3, 50}, {1, 4, 70}, {1, 5, 90}, {2, 3, 30}, {3, 4, 40}, {4, 5, 60}};
        int expected = 150;
        int actual = MinSpanTree.calcKruskal(n, edges);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testCalcKruskal4() {
        int n = 10;
        int[][] edges = new int[][] {
            {1, 2, 234},
            {1, 9, 17},
            {8, 4, 60},
            {3, 6, 41},
            {3, 10, 178},
            {2, 6, 38},
            {1, 8, 99},
            {4, 7, 54},
            {5, 7, 29},
            {1, 5, 160},
            {2, 10, 280},
            {4, 6, 18},
            {5, 6, 33},
            {7, 9, 50},
            {2, 3, 10},
            {6, 10, 82},
            {2, 5, 70},
            {4, 10, 199},
            {8, 9, 6},
            {3, 5, 22}
        };
        int expected = 267;
        int actual = MinSpanTree.calcKruskal(n, edges);
        Logger.debug(actual);
        assertEquals(expected, actual);
    }
}
