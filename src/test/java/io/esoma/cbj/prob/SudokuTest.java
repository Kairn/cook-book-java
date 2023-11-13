package io.esoma.cbj.prob;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.tinylog.Logger;

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
        "...26.7.168..7..9.19...45..82.1...4...46.29...5...3.28..93...74.4..5..367.3.18..., 435269781682571493197834562826195347374682915951743628519326874248957136763418259",
        ".2.6.8...58...97......4....37....5..6.......4..8....13....2......98...36...3.6.9., 123678945584239761967145328372461589691583274458792613836924157219857436745316892"
    })
    void testStandardDifficulty(String input, String answer) {
        Sudoku sudoku = Sudoku.fromString(input);
        assertTrue(sudoku.solve());
        sudoku.debugPrint();
        assertEquals(answer, sudoku.toString());
    }

    @ParameterizedTest
    @CsvSource({
        "...6..4..7....36......91.8...........5.18...3...3.6.45.4.2...6.9.3.......2....1.., 581672439792843651364591782438957216256184973179326845845219367913768524627435198",
        "2..3.....8.4.62..3.138..2......2.39.5.7...621.32..6....2...914.6.125.8.9.....1..2, 276314958854962713913875264468127395597438621132596487325789146641253879789641532",
        ".2..........6....3.74.8.........3..2.8..4..1.6..5.........1.78.5....9..........4., 126437958895621473374985126457193862983246517612578394269314785548769231731852649",
        "...65......2..93......4..5..1....23.3....7.41.29.........4......73...8......15..., 947653182152789364638241759714596238365827941829134576591478623473962815286315497",
        "...87...417..9..3...5........6....2.52.9..8....3.5....29..1..7..6............73.., 639872154172594638845163792916748523524931867783256941298315476367489215451627389"
    })
    void testDiabolical(String input, String answer) {
        Sudoku sudoku = Sudoku.fromString(input);
        assertTrue(sudoku.solve());
        Logger.debug("Solved with {} shadow(s).", sudoku.getShadowCount());
        sudoku.debugPrint();
        assertEquals(answer, sudoku.toString());
    }

    @Test
    void testInvalidPuzzles() {
        String fewClues = ".......4.....5......2.........9.1................................................";
        assertThrowsExactly(IllegalArgumentException.class, () -> Sudoku.fromString(fewClues));

        String noGood = "...2.3.6..9.4.........23......8.7.92.....4..56...8...1.......9....23..6..7..1....";
        assertFalse(Sudoku.fromString(noGood).solve());
    }
}
