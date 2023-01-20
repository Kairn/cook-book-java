package io.esoma.cbj.ds;

import java.util.Collections;
import java.util.List;

/**
 * Class for implementing a classic "left-leaning" red-black tree. A red-black tree is a
 * self-balancing binary tree derived from a 2-3 or 2-4 tree, meaning it can be used to represent
 * these B-trees but without storing multiple keys in a single node. Instead, colored links are used
 * to distinguish between children (black) and internal nodes (red). This type of representation has
 * 2 major advantages. First, other than insert and delete calls, all other public APIs don't differ
 * from a regular BST in terms of implementation. Second, the insert and delete calls can (by and
 * large) be implemented with much simpler common operations thus reducing the overall code length.
 */
public class RedBlackTree<E extends Comparable<E>> {

  private int size;
  private RbNode<E> root;

  /**
   * Queries and returns the number of elements in the tree.
   *
   * @return the tree size
   */
  public int size() {
    return size;
  }

  /**
   * Checks the tree to see if the given key is present.
   *
   * @param key the key to search
   * @return true if the key is present, or false otherwise
   */
  public boolean contains(E key) {
    return false;
  }

  /**
   * Inserts a key into the tree. Returns true if a new key is inserted.
   *
   * @param key the key to insert
   * @return true if a new key is inserted, or false otherwise
   */
  public boolean insert(E key) {
    return false;
  }

  /**
   * Deletes a key from the current tree. Returns true if a key is deleted.
   *
   * @param key the key to delete
   * @return true if a key is deleted after the operation, or false otherwise
   */
  public boolean delete(E key) {
    return false;
  }

  /**
   * Converts the tree into a sorted list by traversing the nodes in order.
   *
   * @return a sorted list of all elements in the tree in ascending order
   */
  public List<E> toSortedList() {
    return Collections.emptyList();
  }

  @Override
  public String toString() {
    StringBuilder bu = new StringBuilder();
    for (E e : this.toSortedList()) {
      bu.append(e.toString());
      bu.append(' ');
    }

    return String.format("RedBlackTree{ %s }", bu.toString().trim());
  }

  /**
   * A helper structure to represent nodes in a red-black tree.
   *
   * @param <E>
   */
  private static class RbNode<E extends Comparable<E>> {

    E key;
  }
}
