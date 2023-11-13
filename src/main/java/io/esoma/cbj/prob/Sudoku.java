package io.esoma.cbj.prob;

import java.util.*;
import org.tinylog.Logger;

/**
 * This is a solver for the classic Sudoku game. A Sudoku puzzle is a 9 x 9 grid partially filled
 * with numbers from 1-9, and the goal is to complete the whole grid where no row, column, or any of
 * the 3 x 3 subgrid contains duplicate numbers. A valid puzzle must have one and only one such
 * arrangement.
 *
 * <p>The main algorithm is based on common heuristics with the capability of using indirect
 * inferences (i.e. trial-and-error) at one level deep. Although it can solve almost all puzzles
 * found online very efficiently, there exist deliberately crafted puzzles that require a much more
 * hardcore brute force approach to crack.
 */
public class Sudoku {

    public static void main(String[] args) {
        Sudoku sudoku =
                Sudoku.fromString(".8......79...2...36..1..4.....3..72.....4.....95...........1.452..46.3....4...8..");
        if (sudoku.solve()) {
            Logger.info("Sudoku puzzle solved! Answer:");
            sudoku.debugPrint();
        } else {
            Logger.info("Unsolvable puzzle :(. Stuck at:");
            sudoku.debugPrint();
        }
    }

    // Puzzle grid width. Only 9 is supported.
    private static final int PW = 9;
    // Null (unfilled) character.
    private static final char NC = '.';
    private static final int MIN_CLUES = 17;
    private static final int SHADOW_HALT_THRESHOLD = 18;

    /**
     * Creates a Sudoku solver instance with a puzzle input defined by a 2-D char array. The input
     * array must be 9 x 9. Characters from '1' to '9' will be interpreted as prefilled numbers, and
     * all other characters will be interpreted as unfilled cells.
     *
     * @param grid a valid 9 x 9 char grid
     * @return a solver instance for the input
     */
    public static Sudoku fromCharGrid(char[][] grid) {
        if (grid.length != PW || grid[0].length != PW) {
            throw new IllegalArgumentException("Invalid Sudoku dimension");
        }

        int clues = 0;
        Cell[][] sGrid = new Cell[PW][PW];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                int id = i * 10 + j;
                char c = grid[i][j];
                if (isSudokuDigit(c)) {
                    sGrid[i][j] = new Cell(id, c);
                    ++clues;
                } else {
                    sGrid[i][j] = new Cell(id);
                }
            }
        }

        if (clues < MIN_CLUES) {
            throw new IllegalArgumentException("Not enough clues to begin this puzzle");
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
     * second row, and so on). Characters from '1' to '9' will be interpreted as prefilled numbers,
     * and all other characters will be interpreted as unfilled cells.
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
    private final List<CellGroup> allCellGroups;

    private Flag flag;
    private int shadowCount;

    private Sudoku(Cell[][] grid, CellGroup[] rows, CellGroup[] columns, CellGroup[] subgrids) {
        this.grid = grid;
        this.rows = rows;
        this.allCellGroups = new ArrayList<>(PW * 3);
        this.flag = Flag.FRESH;
        this.shadowCount = 0;

        this.allCellGroups.addAll(Arrays.asList(this.rows));
        this.allCellGroups.addAll(Arrays.asList(columns));
        this.allCellGroups.addAll(Arrays.asList(subgrids));
    }

    /**
     * Attempts to solve the current puzzle. If the current puzzle is already solved, calling it
     * multiple times will have no effect on the state.
     *
     * @return whether the puzzle is successfully solved
     */
    public boolean solve() {
        while (!isSolved()) {
            switch (solveDirect()) {
                case -1 -> {
                    // Invalid puzzle due to contradiction under deterministic mode.
                    return false;
                }
                case 0 -> {
                    // Unable to proceed, need shadow mode.
                    if (!solveShadow()) {
                        // Couldn't make progress even with one level shadows.
                        return false;
                    } else {
                        ++shadowCount;
                    }
                }
                default -> {
                    // Solved.
                    return true;
                }
            }
        }
        return true;
    }

    /**
     * Attempts to solve the puzzle in the current state using only direct inferences.
     *
     * @return an int indicating the result of the run
     */
    private int solveDirect() {
        for (; ; ) {
            switch (flag) {
                case FINISHED -> {
                    return 1;
                }
                case BAD -> {
                    return -1;
                }
                case STUCK -> {
                    return 0;
                }
                case FRESH -> inconsistencyRoutine();
                case DIRTY -> promotionRoutine();
                case COVER -> coverEliminationRoutine();
            }
        }
    }

    /**
     * Sometimes, indirect inference must be used to eliminate potential options by chaining direct
     * inferences together across multiple CellGroups. This is implemented via this "Shadow Routine"
     * where a potential number will be "tried" on a cell as the final number, and more direct
     * inferences will be used to hope to either derive a contradiction or successfully solving the
     * puzzle. If a contradiction is reached, the "tried" potential can be eliminated. This routine
     * is only one level deep, meaning only one "try" can be in-progress at a time. If the algorithm
     * gets stuck again while in "Shadow Mode", a rollback is initiated and a new potential will be
     * tried. This routine ends whenever progress is made. If this algorithm fails to move the state
     * forward too many times, it will terminate as the puzzle is likely invalid.
     *
     * @return whether progress is made in shadow mode
     * @see <a href="https://sandiway.arizona.edu/sudoku/h1p.html">Chaining Explained</a>
     */
    private boolean solveShadow() {
        List<Cell> shadowCells = new ArrayList<>(PW * PW);
        for (CellGroup row : rows) {
            shadowCells.addAll(row.unfilled);
        }

        // Try unfilled cells in order of preference up to the threshold limit.
        shadowCells.sort(Cell.SHADOW_RANK);

        // Snapshot the puzzle state by copying to shadow.
        shadowCells.forEach(Cell::copyToShadow);

        int tries = 0;
        for (Cell tryCell : shadowCells) {
            if (tries >= SHADOW_HALT_THRESHOLD) {
                // Halt the program.
                return false;
            }
            // Try this cell with its potentials one at a time.
            ++tries;
            List<Character> toTry = new ArrayList<>(tryCell.potentials);
            for (char tryNum : toTry) {
                tryCell.num = tryNum;
                tryCell.promote();
                flag = Flag.FRESH;

                // Go back to direct inferences.
                switch (solveDirect()) {
                    case 1 -> {
                        // This is a lucky try.
                        return true;
                    }
                        // Stuck again, rollback and try something different.
                    case 0 -> shadowCells.forEach(Cell::restoreFromShadow);
                    case -1 -> {
                        // Got a contradiction, a potential can be eliminated.
                        shadowCells.forEach(Cell::restoreFromShadow);
                        tryCell.potentials.remove(tryNum);
                        flag = Flag.DIRTY;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isSolved() {
        for (CellGroup row : rows) {
            if (!row.unfilled.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int getShadowCount() {
        return shadowCount;
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
        StringBuilder buf = new StringBuilder(PW * PW);
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell.isFilled()) {
                    buf.append(cell.num);
                } else {
                    buf.append(NC);
                }
            }
        }
        return buf.toString();
    }

    /**
     * Runs through all CellGroups and eliminate potential numbers on cells that contradict with
     * known numbers in the same group. This is the most frequently used strategy and not very
     * expensive to run.
     */
    private void inconsistencyRoutine() {
        int res = 0;
        for (CellGroup cg : allCellGroups) {
            res += cg.inconsistentCheck();
        }

        if (res > 0) {
            flag = Flag.DIRTY;
        } else {
            flag = Flag.COVER;
        }
    }

    /**
     * Runs through all CellGroups and eliminate potential numbers on cells that will contradict a
     * subset of unfilled cells covering a subset of numbers. This is a more advanced strategy and
     * more expensive to run.
     *
     * @see <a href="https://sandiway.arizona.edu/sudoku/cover.html">Cover Elimination Explained</a>
     */
    private void coverEliminationRoutine() {
        int res = 0;
        for (CellGroup cg : allCellGroups) {
            res += cg.coverCheck();
        }

        if (res > 0) {
            flag = Flag.DIRTY;
        } else {
            flag = Flag.STUCK;
        }
    }

    /**
     * Runs through all Cells to find numbers that can be filled in after other elimination
     * routines. A cell will also be filled if a potential number uniquely exists within its
     * CellGroup (other cells can't take this number). If insufficiency/inconsistency is found, it
     * will be reported immediately.
     */
    private void promotionRoutine() {
        int res = 0;
        for (CellGroup cg : allCellGroups) {
            int r = cg.promoteAndSanityCheck();
            if (r == -1) {
                flag = Flag.BAD;
                return;
            } else {
                res += r;
            }
        }

        if (res > 0) {
            flag = isSolved() ? Flag.FINISHED : Flag.FRESH;
        } else {
            flag = Flag.COVER;
        }
    }

    /** A class that represents a single cell in the Sudoku puzzle. */
    private static class Cell {

        // An unique ID for the cell across all parent CellGroups.
        final int id;
        // A copy of the state before the programs enters non-deterministic (shadow) mode.
        final Set<Character> shadow;
        // The number in the cell; '.' if empty.
        char num;
        // Potential numbers that can occupy the cell.
        final Set<Character> potentials;
        // The row, column, and 3 x 3 subgrid that this cell is a part of.
        final List<CellGroup> parents;

        // In shadow mode, prefer to try cells with less potentials and parents are more saturated.
        static final Comparator<Cell> SHADOW_RANK = (cell1, cell2) -> {
            if (cell1.potentials.size() == cell2.potentials.size()) {
                return cell2.parentSaturation() - cell1.parentSaturation();
            }
            return cell1.potentials.size() - cell2.potentials.size();
        };

        /**
         * Creates an unfilled cell.
         *
         * @param id the ID of the cell
         */
        Cell(int id) {
            this.id = id;
            this.shadow = new HashSet<>(PW);
            this.num = NC;
            this.potentials = new HashSet<>(PW);
            for (char c = '1'; c <= '9'; ++c) {
                this.potentials.add(c);
            }
            this.parents = new ArrayList<>(3);
        }

        /**
         * Creates a filled cell.
         *
         * @param id the ID of the cell
         * @param num the number on the cell
         */
        Cell(int id, char num) {
            this.id = id;
            this.shadow = Collections.emptySet();
            this.num = num;
            this.potentials = Collections.emptySet();
            this.parents = Collections.emptyList();
        }

        boolean isFilled() {
            return num != NC;
        }

        int parentSaturation() {
            int saturation = 0;
            for (CellGroup cg : parents) {
                saturation += cg.filled.size();
            }
            return saturation;
        }

        /**
         * Promotes this cell to a filled cell in respect to its parents. The resident number needs
         * to be declared by other routines already.
         *
         * @return if the promotion is successful
         */
        boolean promote() {
            if (num == NC) {
                throw new IllegalStateException("No promotion target");
            }
            for (CellGroup cg : parents) {
                if (!cg.filled.add(num)) {
                    return false;
                }
                cg.unfilled.remove(this);
            }
            return true;
        }

        void copyToShadow() {
            shadow.clear();
            shadow.addAll(potentials);
        }

        void restoreFromShadow() {
            if (num != NC) {
                // Potentially demote this cell.
                for (CellGroup cg : parents) {
                    cg.filled.remove(num);
                    // Double check because the promotion might have failed in the middle.
                    if (!cg.unfilled.contains(this)) {
                        cg.unfilled.add(this);
                    }
                }
                num = NC;
            }
            // Potentials cannot grow during regular routines.
            potentials.addAll(shadow);
        }
    }

    /** A class that represents a row, column, or 3 x 3 subgrid in the Sudoku puzzle. */
    private static class CellGroup {

        static final Object NULL_CELL = new Object();

        final Set<Character> filled;
        final List<Cell> unfilled;

        /**
         * Initializes an instance with its member cells.
         *
         * @param cells the cells that belong to this group
         */
        CellGroup(Cell... cells) {
            if (cells.length != PW) {
                throw new IllegalArgumentException("Invalid CellGroup length");
            }

            this.filled = new HashSet<>(PW);
            this.unfilled = new ArrayList<>(PW);
            for (Cell cell : cells) {
                if (cell.isFilled()) {
                    this.filled.add(cell.num);
                } else {
                    this.unfilled.add(cell);
                    cell.parents.add(this);
                }
            }
        }

        /**
         * Promotes unfilled cells if enough information is present to determine their final number.
         * Reports any insufficiencies/contradictions if found.
         *
         * @return the number of successful promotions, or -1 if sanity check fails
         */
        int promoteAndSanityCheck() {
            // Associate each number ('1' to '9') to an object.
            // The object will be a `NULL_CELL` if this number is insufficient.
            // The object will be an Integer if this number is taken or with multiple potentials.
            // The object will be a Cell if there is only one unfilled cell that claims it.
            Map<Character, Object> groupStats = new HashMap<>(PW);
            for (char c = '1'; c <= '9'; ++c) {
                groupStats.put(c, filled.contains(c) ? 1 : NULL_CELL);
            }

            List<Cell> toPromote = new ArrayList<>(PW);

            for (Cell cell : unfilled) {
                if (cell.potentials.isEmpty()) {
                    // Contradiction.
                    return -1;
                } else if (cell.potentials.size() == 1) {
                    cell.num = cell.potentials.iterator().next();
                    toPromote.add(cell);
                    groupStats.put(cell.num, 1);
                    continue;
                }
                for (char c : cell.potentials) {
                    groupStats.compute(c, (k, v) -> v == NULL_CELL ? cell : 1);
                }
            }

            for (Map.Entry<Character, Object> entry : groupStats.entrySet()) {
                if (entry.getValue() == NULL_CELL) {
                    // Insufficient candidate cells.
                    return -1;
                } else if (entry.getValue() instanceof Cell cell) {
                    // Found a unique mention.
                    cell.num = entry.getKey();
                    toPromote.add(cell);
                }
            }

            for (Cell cell : toPromote) {
                if (!cell.promote()) {
                    return -1;
                }
            }

            return toPromote.size();
        }

        /**
         * Eliminates potentials from unfilled cells if they are already taken in this CellGroup.
         *
         * @return the number of eliminations
         */
        int inconsistentCheck() {
            int delCount = 0;
            for (Cell cell : unfilled) {
                delCount += cell.potentials.removeAll(filled) ? 1 : 0;
            }
            return delCount;
        }

        /**
         * Iterates all combinations of unfilled cells in this CellGroup to discover "Covers". A
         * cover must have a minimum of 2 cells. Covers are then used to eliminate potentials in
         * other cells since they cannot take any of the covered numbers.
         *
         * @return the number of eliminations
         */
        int coverCheck() {
            int maxCombo = unfilled.size() - 1;
            if (maxCombo < 2) {
                return 0;
            }

            Deque<Integer> idStack = new ArrayDeque<>(PW - 1);
            Map<Character, Integer> potentialCounts = new HashMap<>(PW);
            List<Cover> covers = new ArrayList<>();
            coverIterate(0, maxCombo, idStack, potentialCounts, covers);

            int delCount = 0;
            for (Cover cover : covers) {
                for (Cell cell : unfilled) {
                    if (!cover.cellIds.contains(cell.id)) {
                        delCount += cell.potentials.removeAll(cover.coveredNums) ? 1 : 0;
                    }
                }
            }

            return delCount;
        }

        private void coverIterate(
                int ptr,
                int maxCombo,
                Deque<Integer> idStack,
                Map<Character, Integer> potentialCounts,
                List<Cover> covers) {
            if (ptr >= unfilled.size() || (ptr == unfilled.size() - 1 && idStack.isEmpty())) {
                return;
            }

            // Include this cell.
            Cell curCell = unfilled.get(ptr);
            idStack.push(curCell.id);
            for (char num : curCell.potentials) {
                potentialCounts.compute(num, (k, count) -> count == null ? 1 : count + 1);
            }
            if (idStack.size() > 1 && idStack.size() == potentialCounts.size()) {
                // Found a cover.
                covers.add(new Cover(new HashSet<>(idStack), new HashSet<>(potentialCounts.keySet())));
            }

            // Try to advance this combination further.
            if (idStack.size() < maxCombo) {
                coverIterate(ptr + 1, maxCombo, idStack, potentialCounts, covers);
            }

            // Remove this cell and try other combinations without it.
            idStack.pop();
            for (char num : curCell.potentials) {
                potentialCounts.compute(num, (k, count) -> (count == null || count == 1) ? null : count - 1);
            }
            coverIterate(ptr + 1, maxCombo, idStack, potentialCounts, covers);
        }
    }

    /**
     * A simple record for a "covered" subset inside a CellGroup. If n (unfilled) cells in a row,
     * column, or 3 x 3 subgrid jointly include exactly n unique potentials, these cells are
     * considered as a cover.
     *
     * @param cellIds IDs for cells in this cover
     * @param coveredNums the numbers covered
     */
    private record Cover(Set<Integer> cellIds, Set<Character> coveredNums) {}

    /**
     * An enum describing the state of the puzzle; used to instruct the algorithm on the next
     * step(s).
     */
    private enum Flag {
        // When new clues are present.
        FRESH,
        // When some potentials have been eliminated.
        DIRTY,
        // When ready to try cover elimination after exhausting cheaper strategies.
        COVER,
        // No more progress can be made using direct inferences.
        STUCK,
        // The puzzle is in an invalid state.
        BAD,
        // The puzzle has been solved.
        FINISHED
    }
}
