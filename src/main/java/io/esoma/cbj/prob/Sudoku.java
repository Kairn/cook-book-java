package io.esoma.cbj.prob;

import org.tinylog.Logger;

/**
 * This is a solver for the classic Sudoku game. A Sudoku puzzle is a 9 x 9 grid partially filled
 * with numbers from 1-9, and the goal is to complete the whole grid where no row, column, or any of
 * the 3 x 3 subgrid contains duplicate numbers. A valid puzzle must have one and only one such
 * arrangement.
 */
public class Sudoku {

    public static void main(String[] args) {
        Sudoku sudoku = Sudoku.fromString("");
        if (sudoku.solve()) {
            Logger.info("Sudoku puzzle solved! Answer:");
            sudoku.debugGrid();
        } else {
            Logger.error("Unsolvable puzzle :(.");
        }
    }

    private Sudoku() {}

    /**
     * Creates a Sudoku solver instance with a puzzle input defined by a 2-D char array. The input
     * array must be 9 x 9. Characters from '1' to '9' will be interpreted as a prefilled number,
     * and all other characters will be interpreted as an empty cell.
     *
     * @param grid a valid 9 x 9 char grid
     * @return a solver instance for the input
     */
    public static Sudoku fromCharGrid(char[][] grid) {
        return null;
    }

    /**
     * Creates a Sudoku solver instance with a puzzle input defined by a string. The string must
     * contain 81 characters which represents the concatenation of all 9 rows of the puzzle (the 0th
     * to 8th characters are read as the first row from left to right, and the 9th to 17th as the
     * second row, and so on). Characters from '1' to '9' will be interpreted as a prefilled number,
     * and all other characters will be interpreted as an empty cell.
     *
     * @param input a valid string input
     * @return a solver instance for the input
     */
    public static Sudoku fromString(String input) {
        return null;
    }

    /**
     * Attempts to solve the current puzzle. If the current puzzle is already solved, calling it
     * multiple times will have no effect on the state.
     *
     * @return whether the puzzle is successfully solved
     */
    public boolean solve() {
        return false;
    }

    /** Debug prints the current puzzle state as a 9 x 9 grid. */
    public void debugGrid() {}

    /**
     * Returns a string representation by concatenating all rows of the current puzzle state. Empty
     * cell will be encoded as '.' character.
     *
     * @return an 81-character string representing the Sudoku puzzle
     */
    @Override
    public String toString() {
        return "";
    }
}
