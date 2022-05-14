package io.esoma.cbj.algo;

/**
 * Class for solving the Maximal Square problem in which a 2D matrix of only 0s and 1s is given, and
 * the task is to find the largest square sub-matrix consisting of only 1s.
 */
public class MaximalSquare {

  static int[][] cache = new int[][] {};

  private MaximalSquare() {}

  /**
   * Iterates through the matrix starting from the top left, and for each element, the algorithm
   * checks for the largest square of 1s it can be a part of if the element is on the top left
   * corner. This implementation uses a dynamic programming approach to recursively aggregate the
   * results returned from each element's three neighbors (right, bottom, and bottom right) to
   * determine the largest square and propagate it up until all elements are searched.
   *
   * @param matrix the input int matrix
   * @return the area of the target square
   */
  public static int findMaxArea(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }

    int maxLength = 0;
    int n = matrix.length;
    int m = matrix[0].length;

    // Initialize cache
    cache = new int[n][m];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        cache[i][j] = -1;
      }
    }

    // Search each element by fixing it at the top left
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        maxLength = Math.max(maxLength, findAtPoint(matrix, i, j));
      }
    }

    return maxLength * maxLength;
  }

  /**
   * Returns the edge length of the largest square of 1s starting from a point assuming it is the
   * top left element.
   *
   * @param matrix the input matrix
   * @param i the row index of the point
   * @param j the column index of the point
   * @return the length of the largest square
   */
  private static int findAtPoint(int[][] matrix, int i, int j) {
    int n = matrix.length;
    int m = matrix[0].length;

    if (i >= n || j >= m) {
      return 0;
    } else if (cache[i][j] != -1) {
      return cache[i][j];
    }

    if (matrix[i][j] == 0) {
      cache[i][j] = 0;
      return 0;
    } else if (matrix[i][j] != 1) {
      throw new IllegalArgumentException("Invalid input element: " + matrix[i][j]);
    }

    int maxFromRight = findAtPoint(matrix, i, j + 1);
    int maxFromBottom = findAtPoint(matrix, i + 1, j);
    int maxFromBottomRight = findAtPoint(matrix, i + 1, j + 1);

    cache[i][j] = 1 + Math.min(maxFromRight, Math.min(maxFromBottom, maxFromBottomRight));
    return cache[i][j];
  }
}
