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
        "GYRRB, GGGGG, -1",
        "WWGWGW, GWBWR, 3",
        "WWBBWBBWW, BB, -1",
        "RRWWRRBBRR, WB, 2",
        "RBYYBBRRB, YRBGB, 3",
        "GGYYBBYYG, B, 1",
        "RRGGBBYYWWRRGGBB, RGBYW, -1",
        "RWYWRRWRR, YRY, 3",
        "RRYGGYYRRYYGGYRR, GGBBB, 5",
        "WRBWYGRGYGWWBWRW, YWGRB, -1",
    })
    void testSolveZuma(String board, String hand, int answer) {
        int actual = Zuma.findMinSteps(board, hand);
        Logger.debug(actual);
        assertEquals(answer, actual);
    }
}
