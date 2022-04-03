package io.esoma.cbj.algo;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class for study the algorithm that calculates the sum of sub-array imbalances for a given array.
 * The imbalance of an array is defined as the absolute difference between the maximum element and
 * the minimum element. Therefore, the naive implementation of the algorithm iterates through all
 * sub-arrays of the given array, computes the imbalance of each one, and returns the total.
 * However, the implementation here is to demonstrate a clever way of doing this in O(n) time.
 */
public class SubArrayImbalance {

  private SubArrayImbalance() {}

  public static long getTotalImbalance(int[] array) {
    if (ArrayUtils.isEmpty(array)) {
      return 0L;
    }

    List<Node> nodes = Arrays.stream(array).mapToObj(Node::new).collect(Collectors.toList());

    long maxSum = 0L;
    long minSum = 0L;

    // For each element, calculate how many sub-arrays has it as the min and max value.
    // Both passes (4 loops) must be completed for the counts to be accurate.

    // First pass.
    iterateSequence(nodes, true, true);
    iterateSequence(nodes, false, true);

    // Reverse the nodes.
    Collections.reverse(nodes);

    // Second pass.
    iterateSequence(nodes, true, false);
    iterateSequence(nodes, false, false);

    for (Node node : nodes) {
      maxSum += (long) node.val * node.maxCount;
      minSum += (long) node.val * node.minCount;
    }

    return maxSum - minSum;
  }

  private static void iterateSequence(List<Node> nodes, boolean isMin, boolean isFirstPass) {
    Deque<Node> stack = new ArrayDeque<>();

    for (Node node : nodes) {
      if (stack.isEmpty()) {
        stack.push(node);
        continue;
      }

      if ((isMin && node.val > stack.peek().val) || (!isMin && node.val < stack.peek().val)) {
        stack.push(node);
        continue;
      }

      // We start to pop off and update counts.
      popSequence(stack, node, isMin, isFirstPass);
      stack.push(node);
    }

    // Pop the remaining elements.
    popSequence(stack, null, isMin, isFirstPass);
  }

  private static void popSequence(
      Deque<Node> stack, Node basis, boolean isMin, boolean isFirstPass) {
    int counter = 1;

    if (isMin) {
      while (!stack.isEmpty() && (basis == null || stack.peek().val >= basis.val)) {
        int temp = stack.peek().temp;
        counter += temp;
        Node popped = stack.pop();
        if (isFirstPass) {
          popped.minCount = counter++;
        } else {
          popped.minCount *= counter++;
        }
        popped.temp = 0;
      }
      if (!stack.isEmpty()) {
        stack.peek().temp += counter - 1;
      }

      return;
    }

    while (!stack.isEmpty() && (basis == null || stack.peek().val <= basis.val)) {
      int temp = stack.peek().temp;
      counter += temp;
      Node popped = stack.pop();
      if (isFirstPass) {
        popped.maxCount = counter++;
      } else {
        popped.maxCount *= counter++;
      }
      popped.temp = 0;
    }
    if (!stack.isEmpty()) {
      stack.peek().temp += counter - 1;
    }
  }

  /**
   * Structure for a node representing a value, the number of sub-arrays it can (uniquely) be the
   * minimum value, and the number of sub-arrays it can be the maximum value.
   */
  private static class Node {

    final int val;
    int maxCount;
    int minCount;
    int temp;

    Node(int val) {
      this.val = val;
    }
  }
}
