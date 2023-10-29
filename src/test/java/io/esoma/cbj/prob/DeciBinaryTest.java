package io.esoma.cbj.prob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.tinylog.Logger;

class DeciBinaryTest {

    @BeforeAll
    static void setUpBeforeClass() {
        DeciBinary.initCache();
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 1", "10, 2", "2016, 24", "2008, 24", "75, 19", "108, 12", "7422485, 661"})
    void testDeciValue(int dbNum, int value) {
        int actual = DeciBinary.deciValue(dbNum);
        Logger.debug(actual);
        assertEquals(value, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "7, 4",
        "10, 100",
        "3, 2",
        "6, 11",
        "26, 111",
        "19, 102",
        "30, 32",
        "1104, 11111",
        "1963, 10406",
        "20, 110",
        "1, 0"
    })
    void testDeciFromDb(long db, long value) {
        long actual = DeciBinary.deciFromDb(db);
        Logger.debug(actual);
        assertEquals(value, actual);
    }
}
