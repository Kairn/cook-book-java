package io.esoma.cbj.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Class for implementing the Fibonacci Heap data structure. A Fibonacci heap is used for priority
 * queue operations, consisting of a collection of heap-ordered trees. It has a better amortized
 * running time than many other priority queue data structures including the binary heap and
 * binomial heap. This implementation is a min-heap, and it will order elements only by their
 * natural ordering.
 *
 * @author Eddy Soma
 * @param <E> the type of elements held in this heap
 */
public class FibonacciHeap<E extends Comparable<E>> {

  // Internal state.
  private int size;
  private FhNode<E> minNode;
  private LinkedList<FhNode<E>> mainChain;
  /*
   * Default constructor for creating a blank Fibonacci heap.
   */
  public FibonacciHeap() {
    super();
    this.mainChain = new LinkedList<>();
  }

  /**
   * Queries and returns the number of elements in the heap.
   *
   * @return the heap size
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Pushes a new element to the heap. In a Fibonacci heap, this operation is to simply add this
   * element as the root of a new node tree which will then be appended to the main chain. The
   * pointer to the minimum element will be updated if necessary.
   *
   * @param e the element to be inserted
   */
  public void push(E e) {
    // Create a new node for the element.
    FhNode<E> n = new FhNode<>(e);
    // Append the node to the chain.
    this.mainChain.add(n);
    ++this.size;

    // Update the min key if necessary.
    if (this.minNode == null || n.isLess(this.minNode)) {
      this.minNode = n;
    }
  }

  /**
   * Queries and returns the head of the heap, but the head will not be removed from the heap.
   *
   * @return the head (the least element) of the heap, or null of the heap is empty
   */
  public E peekMin() {
    return this.minNode.key;
  }

  /**
   * Retrieves the least element and removes it from the heap. The children of the removed node will
   * be promoted to become parts of the root list. Any time a node has lost two children, it will
   * also be dumped to the root list. At the end, trees in the main chain will be merged if they
   * have the same degree (number of direct children). The pointer to the next minimum node will
   * also be updated appropriately.
   *
   * @return the head of the heap, or null of the heap is empty
   */
  public E popMin() {
    if (this.minNode == null) {
      return null;
    }

    // Hold on to the reference for the return value.
    E min = this.minNode.key;
    // Remove the min node.
    this.mainChain.remove(this.minNode);
    // Promote its children if any.
    if (this.minNode.children != null) {
      for (FhNode<E> mnc : this.minNode.children) {
        this.mainChain.add(mnc);
      }
    }

    // Prepare the next min key.
    --this.size;
    if (this.mainChain.size() == 0) {
      this.minNode = null;
    } else {
      FhNode<E> newMn = this.mainChain.element();
      Map<Integer, FhNode<E>> map = new HashMap<>();

      while (!this.mainChain.isEmpty()) {
        FhNode<E> n = this.mainChain.poll();
        // Update the next min key if less.
        if (n.isLess(newMn)) {
          newMn = n;
        }

        // Merge node operation.
        this.mergeNode(map, n);
      }

      // Populate the main chain again.
      for (FhNode<E> n : map.values()) {
        this.mainChain.add(n);
      }

      // Set the next min key.
      this.minNode = newMn;
    }

    return min;
  }

  /**
   * Recursively merges nodes of the same degree based on the given map of degree to node.
   *
   * @param map a map containing nodes of different degrees
   * @param node the node to be merged
   */
  private void mergeNode(Map<Integer, FhNode<E>> map, FhNode<E> node) {
    int deg = node.degree();
    if (map.get(deg) == null) {
      map.put(deg, node);
    } else {
      FhNode<E> pn = map.remove(deg);
      if (node.isLess(pn)) {
        node.children.add(pn);
        pn = node;
      } else {
        pn.children.add(node);
      }

      // Repeat the process.
      this.mergeNode(map, pn);
    }
  }

  @Override
  /*
   * Returns a string representation of the Fibonacci heap. All elements will be
   * listed but in arbitrary order.
   */
  public String toString() {
    StringBuilder bu = new StringBuilder();
    if (this.mainChain != null) {
      for (FhNode<E> n : this.mainChain) {
        n.reportElements(bu);
      }
    }

    return "FibonacciHeap [" + bu.toString().trim() + "]";
  }

  /**
   * Helper class for heap nodes.
   *
   * @author Eddy Soma
   * @param <E> the type of elements stored in the node
   */
  private static class FhNode<E extends Comparable<E>> {

    E key;
    LinkedList<FhNode<E>> children;

    /*
     * Constructor for creating a basic node with the given key.
     */
    FhNode(E key) {
      this.key = key;
      this.children = new LinkedList<>();
    }

    /**
     * Returns the degree (number of direct children) of the node.
     *
     * @return the degree
     */
    int degree() {
      if (this.children == null) {
        return 0;
      } else {
        return this.children.size();
      }
    }

    /**
     * Writes all the elements under the node into the given StringBuilder for debugging and
     * reporting purpose.
     *
     * @param bu
     */
    void reportElements(StringBuilder bu) {
      bu.append(this.key.toString());
      bu.append(' ');
      if (this.children != null) {
        for (FhNode<E> n : this.children) {
          n.reportElements(bu);
        }
      }
    }

    /**
     * Compares to another node and checks if the key is less in comparison.
     *
     * @param other the other node
     * @return true if the key in the current node is less than the key in the other node, or false
     *     otherwise
     */
    boolean isLess(FhNode<E> other) {
      if (other == null) {
        return false;
      } else {
        return this.key.compareTo(other.key) < 0;
      }
    }
  }
}
