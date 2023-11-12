package io.esoma.cbj.prob;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Disabled
class SudokuTest {

    @Test
    void testSampleArrayInput() {
        Sudoku sudoku = Sudoku.fromCharGrid(new char[][] {
            new char[] {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            new char[] {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            new char[] {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            new char[] {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            new char[] {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            new char[] {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            new char[] {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            new char[] {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            new char[] {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        });
        assertTrue(sudoku.solve());
        sudoku.debugPrint();
        assertEquals(
                "534678912672195348198342567859761423426853791713924856961537284287419635345286179", sudoku.toString());
    }

    @ParameterizedTest
    @CsvSource({
        "...26.7.168..7..9.19...45..82.1...4...46.29...5...3.28..93...74.4..5..367.3.18..., 435269781682571493197834562826195347374682915951743628519326874248957136763418259"
    })
    void testStandardDifficulty(String input, String answer) {
        Sudoku sudoku = Sudoku.fromString(input);
        assertTrue(sudoku.solve());
        sudoku.debugPrint();
        assertEquals(answer, sudoku.toString());
    }
}
