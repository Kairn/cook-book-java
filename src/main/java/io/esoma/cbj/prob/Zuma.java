package io.esoma.cbj.prob;

import java.util.*;

/** An implementation that solves the variation of the Zuma game listed on LeetCode No. 488. */
public class Zuma {

    private static final ZumaState MARKER = new ZumaState(null, null);

    private Zuma() {}

    /**
     * Calculates the minimum steps to clear a Zuma board using the hand given. Balls in hand can be
     * played in any order.
     *
     * @param board a string consisting of RYBGW characters, max length is 16
     * @param hand a string representing balls at hand, max length is 5
     * @return the minimum number of steps to clear the board, or -1 if impossible
     */
    public static int findMinSteps(String board, String hand) {
        char[] handChars = hand.toCharArray();
        Arrays.sort(handChars);

        String initBoard = encode(board);
        String initHand = encode(new String(handChars));
        initHand = evalAndOptimizeHand(initBoard, initHand);
        if (initHand.isEmpty()) {
            return -1;
        }

        ZumaState initState = new ZumaState(initBoard, initHand);
        Optional<List<ZumaState>> firstStates = initState.generateNextStates();
        if (firstStates.isEmpty()) {
            return 1;
        }

        Deque<ZumaState> queue = new ArrayDeque<>(firstStates.get());
        if (queue.isEmpty()) {
            return -1;
        }
        queue.offer(MARKER);
        int steps = 2;

        Set<ZumaState> seenStates = new HashSet<>();
        while (!queue.isEmpty()) {
            ZumaState state = queue.poll();
            if (state == MARKER) {
                if (queue.isEmpty()) {
                    return -1;
                }
                steps++;
                queue.offer(MARKER);
                seenStates.clear();
                continue;
            }

            Optional<List<ZumaState>> nextStates = state.generateNextStates();
            if (nextStates.isEmpty()) {
                return steps;
            }
            for (ZumaState zs : nextStates.get()) {
                if (!seenStates.contains(zs)) {
                    seenStates.add(zs);
                    queue.offer(zs);
                }
            }
        }

        // Unreachable.
        return -2;
    }

    /**
     * Run-length encoding of the input source string.
     *
     * @param source the source string
     * @return the encoded string
     */
    private static String encode(String source) {
        StringBuilder builder = new StringBuilder(source.length() * 2 + 1);
        char curChar = '\0';
        int cnt = 0;
        for (char c : source.toCharArray()) {
            if (curChar == c) {
                ++cnt;
            } else {
                if (cnt > 0) {
                    builder.append(curChar).append(cnt);
                }
                curChar = c;
                cnt = 1;
            }
        }
        if (cnt > 0) {
            builder.append(curChar).append(cnt);
        }
        return builder.toString();
    }

    /**
     * Evaluates the current board and hand (encoded form) to see if the game is over or balls from
     * the hand can be removed.
     *
     * @param board the board
     * @param hand the hand
     * @return the optimized hand by removing useless balls, empty string if no solution is
     *     available
     */
    static String evalAndOptimizeHand(String board, String hand) {
        int removeIndex = -1;
        LOOP:
        for (; ; ) {
            if (removeIndex >= 0) {
                hand = hand.substring(0, removeIndex) + hand.substring(removeIndex + 2);
            }
            for (int i = 0; i < hand.length(); i += 2) {
                char ball = hand.charAt(i);
                int handCount = hand.charAt(i + 1) - 48;
                int boardCount = 0;
                for (int j = 0; j < board.length(); j += 2) {
                    if (ball == board.charAt(j)) {
                        boardCount += board.charAt(j + 1) - 48;
                    }
                }
                // No solution is possible.
                if (boardCount > 0 && boardCount < 3 && boardCount + handCount < 3) {
                    return "";
                }
                // This ball color in hand is useless.
                if (boardCount == 0 && handCount < 3) {
                    removeIndex = i;
                    continue LOOP;
                }
            }
            break;
        }
        return hand;
    }
}

record ZumaState(String board, String hand) {

    /**
     * Generates the next set of states after firing a ball from hand.
     *
     * @return the list of states wrapped in Optional, empty Optional if board can be cleared, empty
     *     list is returned if no solution
     */
    Optional<List<ZumaState>> generateNextStates() {
        List<ZumaState> nextStates = new ArrayList<>();
        for (int i = 0; i < this.hand.length(); i += 2) {
            char ball = this.hand.charAt(i);
            int cnt = this.hand.charAt(i + 1) - 48;

            String nextHand;
            if (cnt > 1) {
                char[] nextHandCharArr = this.hand.toCharArray();
                nextHandCharArr[i + 1] = (char) (cnt - 1 + 48);
                nextHand = new String(nextHandCharArr);
            } else {
                nextHand = this.hand.substring(0, i) + this.hand.substring(i + 2);
            }

            if (collide(this.board, nextHand, ball, nextStates) == 1) {
                return Optional.empty();
            }
        }
        return Optional.of(nextStates);
    }

    /**
     * Collide a single ball into the board and generates all possible outcomes at different
     * locations where the ball is inserted.
     *
     * @param board the board
     * @param nextHand the next hand after firing this ball
     * @param ball the letter representing the ball
     * @param nextStates the sink to populate next generation of states
     * @return 1 if board is cleared in any case, 2 if board cannot be solved, 0 otherwise
     */
    int collide(String board, String nextHand, char ball, List<ZumaState> nextStates) {
        Set<String> seenBoards = new HashSet<>();
        boolean skip = false;
        boolean optimize;

        for (int i = 0; i < board.length(); i += 2) {
            if (board.charAt(i) == ball) {
                skip = true;
                optimize = false;
                String nextBoard;
                if (board.charAt(i + 1) == '2') {
                    nextBoard = reduce(board, i);
                    optimize = true;
                } else if (!nextHand.isEmpty()) {
                    char[] nextBoardCharArr = board.toCharArray();
                    nextBoardCharArr[i + 1] = '2';
                    nextBoard = new String(nextBoardCharArr);
                } else {
                    continue;
                }

                if (nextBoard.isEmpty()) {
                    return 1;
                } else if (!seenBoards.contains(nextBoard)) {
                    seenBoards.add(nextBoard);
                    if (optimize) {
                        nextHand = Zuma.evalAndOptimizeHand(nextBoard, nextHand);
                    }
                    if (!nextHand.isEmpty()) {
                        nextStates.add(new ZumaState(nextBoard, nextHand));
                    }
                }
            } else if (!nextHand.isEmpty()) {
                if (!skip) {
                    nextStates.add(new ZumaState(board.substring(0, i) + ball + "1" + board.substring(i), nextHand));
                }
                if (board.charAt(i + 1) == '2') {
                    String segment = board.charAt(i) + "1" + ball + "1" + board.charAt(i) + "1";
                    nextStates.add(new ZumaState(board.substring(0, i) + segment + board.substring(i + 2), nextHand));
                }
                skip = false;
            }
        }
        if (board.charAt(board.length() - 2) != ball && !nextHand.isEmpty()) {
            nextStates.add(new ZumaState(board + ball + "1", nextHand));
        }

        return nextHand.isEmpty() ? -1 : 0;
    }

    /**
     * Collapse the Zuma board until stable from the `pos` position.
     *
     * @param board the board
     * @param pos the first set of balls to remove
     * @return the stable board after reduction
     */
    String reduce(String board, int pos) {
        int lPtr = pos;
        int rPtr = pos + 2;
        for (; ; ) {
            int lBallPos = lPtr - 2;
            int rBallPos = rPtr;
            if (lBallPos < 0 || rBallPos >= board.length()) {
                break;
            }
            if (board.charAt(lBallPos) != board.charAt(rBallPos)) {
                break;
            }
            if (board.charAt(lBallPos + 1) > '1' || board.charAt(rBallPos + 1) > '1') {
                lPtr -= 2;
                rPtr += 2;
                continue;
            }
            break;
        }

        if (lPtr >= 2 && rPtr < board.length() && board.charAt(lPtr - 2) == board.charAt(rPtr)) {
            char ball = board.charAt(lPtr - 2);
            return board.substring(0, lPtr - 2) + ball + "2" + board.substring(rPtr + 2);
        }

        return board.substring(0, lPtr) + board.substring(rPtr);
    }
}
