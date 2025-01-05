package io.esoma.cbj.prob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.tinylog.Logger;

class ZumaTest {

    @ParameterizedTest
    @CsvSource({
        "WRRBBW, RB, -1",
        "WWRRBBWW, WRBRW, 2",
        "G, GGGGG, 2",
    })
    void testSolveZuma(String board, String hand, int answer) {
        int actual = Zuma.findMinSteps(board, hand);
        Logger.debug(actual);
        assertEquals(answer, actual);
    }
}
