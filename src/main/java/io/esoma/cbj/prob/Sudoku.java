package io.esoma.cbj.prob;

import java.util.*;
import org.tinylog.Logger;

/**
 * This is a solver for the classic Sudoku game. A Sudoku puzzle is a 9 x 9 grid partially filled
 * with numbers from 1-9, and the goal is to complete the whole grid where no row, column, or any of
 * the 3 x 3 subgrid contains duplicate numbers. A valid puzzle must have one and only one such
 * arrangement.
 */
public class Sudoku {

    public static void main(String[] args) {
        Sudoku sudoku =
                Sudoku.fromString(".2.6.8...58...97......4....37....5..6.......4..8....13....2......98...36...3.6.9.");
        if (sudoku.solve()) {
            Logger.info("Sudoku puzzle solved! Answer:");
            sudoku.debugPrint();
        } else {
            Logger.error("Unsolvable puzzle :(. Stuck at:");
            sudoku.debugPrint();
        }
    }

    // Puzzle width. Only 9 is supported.
    private static final int PW = 9;
    // Null (unfilled) character.
    private static final char NC = '.';

    /**
     * Creates a Sudoku solver instance with a puzzle input defined by a 2-D char array. The input
     * array must be 9 x 9. Characters from '1' to '9' will be interpreted as a prefilled number,
     * and all other characters will be interpreted as an empty cell.
     *
     * @param grid a valid 9 x 9 char grid
     * @return a solver instance for the input
     */
    public static Sudoku fromCharGrid(char[][] grid) {
        if (grid.length != PW || grid[0].length != PW) {
            throw new IllegalArgumentException("Invalid Sudoku dimension");
        }

        Cell[][] sGrid = new Cell[PW][PW];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                int id = i * 10 + j;
                char c = grid[i][j];
                if (isSudokuDigit(c)) {
                    sGrid[i][j] = new Cell(id, c);
                } else {
                    sGrid[i][j] = new Cell(id);
                }
            }
        }

        CellGroup[] rows = new CellGroup[PW];
        CellGroup[] columns = new CellGroup[PW];
        CellGroup[] subgrids = new CellGroup[PW];
        for (int i = 0; i < PW; ++i) {
            rows[i] = new CellGroup(sGrid[i]);
            columns[i] = getColumn(sGrid, i);
            subgrids[i] = getSubgrid(sGrid, i);
        }

        return new Sudoku(sGrid, rows, columns, subgrids);
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
        if (input == null || input.length() != PW * PW) {
            throw new IllegalArgumentException("Invalid Sudoku dimension");
        }

        char[][] grid = new char[PW][];
        for (int i = 0; i < grid.length; ++i) {
            grid[i] = input.substring(i * PW, i * PW + PW).toCharArray();
        }
        return fromCharGrid(grid);
    }

    private static boolean isSudokuDigit(char c) {
        return c >= '1' && c <= '9';
    }

    private static char renderCell(Cell cell) {
        return cell.isFilled() ? cell.num : ' ';
    }

    private static CellGroup getColumn(Cell[][] grid, int i) {
        return new CellGroup(
                grid[0][i],
                grid[1][i],
                grid[2][i],
                grid[3][i],
                grid[4][i],
                grid[5][i],
                grid[6][i],
                grid[7][i],
                grid[8][i]);
    }

    private static CellGroup getSubgrid(Cell[][] grid, int i) {
        int si = (i / 3) * 3;
        int sj = (i % 3) * 3;
        return new CellGroup(
                grid[si][sj],
                grid[si][sj + 1],
                grid[si][sj + 2],
                grid[si + 1][sj],
                grid[si + 1][sj + 1],
                grid[si + 1][sj + 2],
                grid[si + 2][sj],
                grid[si + 2][sj + 1],
                grid[si + 2][sj + 2]);
    }

    private final Cell[][] grid;
    private final CellGroup[] rows;
    private final CellGroup[] columns;
    private final CellGroup[] subgrids;

    private boolean shadowMode;

    private Sudoku(Cell[][] grid, CellGroup[] rows, CellGroup[] columns, CellGroup[] subgrids) {
        this.grid = grid;
        this.rows = rows;
        this.columns = columns;
        this.subgrids = subgrids;
        this.shadowMode = false;
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
    public void debugPrint() {
        StringBuilder printed = new StringBuilder(525);
        printed.append("---------------------------------------\n");
        pushPrintedRow(printed, 0);
        pushPrintedRow(printed, 1);
        pushPrintedRow(printed, 2);
        printed.append("|===|===|===||===|===|===||===|===|===|\n");
        pushPrintedRow(printed, 3);
        pushPrintedRow(printed, 4);
        pushPrintedRow(printed, 5);
        printed.append("|===|===|===||===|===|===||===|===|===|\n");
        pushPrintedRow(printed, 6);
        pushPrintedRow(printed, 7);
        pushPrintedRow(printed, 8);
        printed.append("---------------------------------------\n");

        Logger.debug("### Grid State Print ###\n{}", printed.toString());
    }

    private void pushPrintedRow(StringBuilder buf, int i) {
        buf.append("| ").append(renderCell(grid[i][0])).append(" ");
        buf.append("| ").append(renderCell(grid[i][1])).append(" ");
        buf.append("| ").append(renderCell(grid[i][2])).append(" |");
        buf.append("| ").append(renderCell(grid[i][3])).append(" ");
        buf.append("| ").append(renderCell(grid[i][4])).append(" ");
        buf.append("| ").append(renderCell(grid[i][5])).append(" |");
        buf.append("| ").append(renderCell(grid[i][6])).append(" ");
        buf.append("| ").append(renderCell(grid[i][7])).append(" ");
        buf.append("| ").append(renderCell(grid[i][8])).append(" |\n");
    }

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

    /**
     * Runs through all CellGroups and eliminate potential numbers on cells that contradict with
     * known numbers in the same group. This is the most frequently used strategy and not very
     * expensive to run. This will NOT actually "fill" the cells even if there is only 1 potential
     * left.
     *
     * @return an int indicating the result of the run
     */
    private int inconsistencyRoutine() {
        return 0;
    }

    /**
     * Runs through all CellGroups and eliminate potential numbers on cells that will contradict a
     * subset of unfilled cells covering a subset of numbers. This is a more advanced strategy and
     * more expensive to run. This will NOT actually "fill" the cells even if there is only 1
     * potential left.
     *
     * @return an int indicating the result of the run
     * @see <a href="https://sandiway.arizona.edu/sudoku/cover.html">Cover Elimination Explained</a>
     */
    private int coverEliminationRoutine() {
        return 0;
    }

    /**
     * Runs through all Cells to find numbers that can be filled in after other elimination
     * routines. A cell will also be filled if a potential number uniquely exists within its
     * CellGroup (other cells can't take this number). If an insufficiency is found, it will be
     * reported immediately.
     *
     * @return an int indicating the result of the run
     */
    private int uniqueRoutine() {
        return 0;
    }

    /**
     * Sometimes, indirect inference must be used to eliminate potential options by chaining direct
     * inferences together across multiple CellGroups. This is implemented via this "Shadow Routine"
     * where a potential number will be "tried" on a cell as the final number, and more direct
     * inferences will be used to hope to either derive a contradiction or successfully solving the
     * puzzle. If a contradiction is reached, the "tried" potential can be eliminated. This routine
     * is only one level deep, meaning only one "try" can be in-progress at a time. If the algorithm
     * gets stuck again while in "Shadow Mode", a rollback is initiated and a new potential will be
     * tried. This routine ends whenever progress is made.
     *
     * @return an int indicating the result of the run
     * @see <a href="https://sandiway.arizona.edu/sudoku/h1p.html">Chaining Explained</a>
     */
    private int shadowRoutine() {
        return 0;
    }

    /** A class that represents a single cell in the Sudoku puzzle. */
    private static class Cell {

        // An unique ID for the cell across all parent CellGroups.
        final int id;
        // A copy of the state before the programs enters non-deterministic mode.
        Cell shadow;
        // The number in the cell; '.' if empty.
        char num;
        // Potential numbers that can occupy the cell.
        final Set<Character> potentials;
        // The row, column, and 3 x 3 subgrid that this cell is a part of.
        final CellGroup[] parents;

        /**
         * Creates an unfilled cell.
         *
         * @param id the ID of the cell
         */
        Cell(int id) {
            this.id = id;
            this.shadow = null;
            this.num = NC;
            this.potentials = Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9');
            this.parents = new CellGroup[3];
        }

        /**
         * Creates a filled cell.
         *
         * @param id the ID of the cell
         * @param num the number on the cell
         */
        Cell(int id, char num) {
            this.id = id;
            this.shadow = null;
            this.num = num;
            this.potentials = Collections.emptySet();
            this.parents = new CellGroup[0];
        }

        boolean isFilled() {
            return num != NC;
        }
    }

    /** A class that represents a row, column, or 3 x 3 subgrid in the Sudoku puzzle. */
    private static class CellGroup {

        final Set<Character> filled;
        final List<Cell> unfilled;

        CellGroup(Cell... cells) {
            this.filled = new HashSet<>(PW);
            this.unfilled = new ArrayList<>(PW);
            for (Cell cell : cells) {
                if (cell.isFilled()) {
                    this.filled.add(cell.num);
                } else {
                    this.unfilled.add(cell);
                }
            }
        }
    }
}
