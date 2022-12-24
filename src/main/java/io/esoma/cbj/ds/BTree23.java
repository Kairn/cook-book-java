package io.esoma.cbj.ds;

import java.util.Arrays;

/**
 * This class illustrates a classic 2-3 tree which is an extension of a binary search tree with
 * contains both 2-nodes (a node with one key and 2 children, the same structure as in any standard
 * binary tree) and 3-nodes (a node with 2 keys and 3 children, middle child represents a value
 * between the left and right keys). This tree is self-balancing during insert/delete operations.
 * For simplicity, only integer keys are supported.
 */
public class BTree23 {

  /**
   * Returns the size of the current tree.
   *
   * @return an int value representing the tree size.
   */
  public int size() {
    return 0;
  }

  /**
   * Inserts an integer key into the tree. Returns true if a new key is inserted.
   *
   * @param key the key to insert
   * @return true if a new key is inserted, and false otherwise
   */
  public boolean insert(int key) {
    return false;
  }

  /**
   * Checks if the tree contains the specified key.
   *
   * @param key the key to search
   * @return true of the key exists in the tree
   */
  public boolean contains(int key) {
    return false;
  }

  /**
   * Deletes a key from the current tree. Returns true if a key is deleted.
   *
   * @param key the key to delete
   * @return true if a key is deleted after the operation, and false otherwise
   */
  public boolean delete(int key) {
    return false;
  }

  /**
   * Returns an array view of all the keys in the tree in ascending order.
   *
   * @return a sorted int array of keys
   */
  public int[] toSortedArray() {
    return new int[0];
  }

  @Override
  public String toString() {
    return String.format("BTree23{ %s }", Arrays.toString(toSortedArray()));
  }
}
