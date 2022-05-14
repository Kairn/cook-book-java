package io.esoma.cbj.algo;

/**
 * Class that implements a permutation generator for integers from 1 to n. Permutations are returned
 * in lexicographical order until exhausted.
 */
public class PermutationGen {

  // TODO: README.md update

  private final int[] state;

  private boolean ended;

  /**
   * Initializes the generator with the given upper bound n.
   *
   * @param n any int larger than 0
   */
  public PermutationGen(int n) {
    state = null;
    ended = false;
  }

  /**
   * Returns if there are more permutations to be generated given the current state.
   *
   * @return true if more permutations can be generated, false otherwise
   */
  public boolean hasNext() {
    return !ended;
  }

  /**
   * Generates the next permutation (lexicographical incrementation from the previous return value)
   * given the current state. The first call simply returns an array of 1 to n sorted in ascending
   * order.
   *
   * @return an array representing the next permutation
   */
  public int[] getNext() {
    if (ended) {
      throw new IllegalStateException("No more permutations to be generated");
    }

    return null;
  }
}
