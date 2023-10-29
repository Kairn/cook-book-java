package io.esoma.cbj.prob;

/**
 * Class for solving the Interleaving String problem where given two source strings, the task is to
 * determine if they can be interleaved together to form a third string. The interleaving process is
 * completed by merging the source strings together without changing the character order in each
 * string. For example, string "abc" can be interleaved with string "xyz" to produce "axbycz",
 * "abxyzc", or "xyzabc", etc., but "cbaxyz" is not legal because the character order of the first
 * source string is not preserved in the final string.
 */
public class InterleavingString {

    private InterleavingString() {}

    /**
     * Determines if the two source strings can be interleaved to form the destination string. This
     * implementation uses a 2-D array to track the validity of the interleaving process.
     *
     * @param src1 the first source string
     * @param src2 the second source string
     * @param dest the destination string
     * @return true if an interleaving solution exists, or false otherwise
     */
    public static boolean judge(String src1, String src2, String dest) {
        int n = src1.length();
        int m = src2.length();
        int r = dest.length();

        if (r != n + m) {
            return false;
        }

        int[][] validationGrid = new int[n + 1][m + 1];

        for (int i = n; i >= 0; --i) {
            boolean shouldContinue = false;

            for (int j = m; j >= 0; --j) {
                if (i == n && j == m) {
                    // Bottom right is the valid ending.
                    validationGrid[i][j] = 1;
                    shouldContinue = true;
                    continue;
                }

                int stepBack = (n - i) + (m - j);

                // Test from the right.
                if (j + 1 <= m && validationGrid[i][j + 1] == 1) {
                    char curChar = src2.charAt(j);
                    if (dest.charAt(r - stepBack) == curChar) {
                        validationGrid[i][j] = 1;
                        shouldContinue = true;
                        continue;
                    }
                }

                // Test from the bottom.
                if (i + 1 <= n && validationGrid[i + 1][j] == 1) {
                    char curChar = src1.charAt(i);
                    if (dest.charAt(r - stepBack) == curChar) {
                        validationGrid[i][j] = 1;
                        shouldContinue = true;
                        continue;
                    }
                }

                validationGrid[i][j] = -1;
            }

            if (!shouldContinue) {
                break;
            }
        }

        return validationGrid[0][0] == 1;
    }
}
