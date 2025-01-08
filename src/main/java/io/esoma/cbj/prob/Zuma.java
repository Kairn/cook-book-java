package io.esoma.cbj.prob;

import java.util.*;
import org.tinylog.Logger;

/** An implementation that solves the variation of the Zuma game listed on LeetCode No. 488. */
public class Zuma {

    private static final ZumaState MARKER = new ZumaState(null, null);
    private static final char[] COLORS = new char[] {'R', 'Y', 'B', 'G', 'W'};

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
        int[] boardCounts = new int[COLORS.length];
        int[] handCounts = new int[COLORS.length];
        for (int i = 0; i < COLORS.length; ++i) {
            for (char c : board.toCharArray()) {
                if (c == COLORS[i]) {
                    boardCounts[i]++;
                }
            }
            for (char c : hand.toCharArray()) {
                if (c == COLORS[i]) {
                    handCounts[i]++;
                }
            }
        }
        // Shortcut when there aren't enough balls in hand to clear a color.
        for (int i = 0; i < boardCounts.length; ++i) {
            if (boardCounts[i] > 0 && boardCounts[i] < 3 && boardCounts[i] + handCounts[i] < 3) {
                return -1;
            }
        }

        char[] handChars = hand.toCharArray();
        Arrays.sort(handChars);

        ZumaState initState = new ZumaState(encode(board), encode(new String(handChars)));
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
                Logger.debug("Queue size: " + queue.size());
                Logger.debug("Set size: " + seenStates.size());
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

    boolean isClear() {
        return board.isEmpty();
    }

    boolean isOver() {
        return hand.isEmpty() && !board.isEmpty();
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

        for (int i = 0; i < board.length(); i += 2) {
            if (board.charAt(i) == ball) {
                skip = true;
                String nextBoard;
                if (board.charAt(i + 1) == '2') {
                    nextBoard = reduce(board, i);
                } else {
                    char[] nextBoardCharArr = board.toCharArray();
                    nextBoardCharArr[i + 1] = '2';
                    nextBoard = new String(nextBoardCharArr);
                }
                if (nextBoard.isEmpty()) {
                    return 1;
                } else if (!seenBoards.contains(nextBoard)) {
                    seenBoards.add(nextBoard);
                    nextStates.add(new ZumaState(nextBoard, nextHand));
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
