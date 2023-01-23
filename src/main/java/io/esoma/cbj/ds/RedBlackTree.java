package io.esoma.cbj.ds;

import java.util.ArrayList;
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
  // Stores the elements gathered through an in-order traversal.
  private final List<E> inOrderCache = new ArrayList<>();

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
    return search(key, root) != null;
  }

  /**
   * Searches the starting node for the specified key and returns it if found. If the key doesn't
   * match, the search process repeats in one of the child nodes based on a comparison.
   *
   * @param key the key to search for
   * @param start the starting node
   * @return the node containing the key, or null if not found in the entire subtree.
   */
  private RbNode<E> search(E key, RbNode<E> start) {
    if (start == null) {
      return null;
    } else if (start.key.equals(key)) {
      return start;
    } else if (key.compareTo(start.key) < 0) {
      // Key is less than the starting node's key.
      return search(key, start.left);
    } else {
      // Key must be greater than the starting node's key.
      return search(key, start.right);
    }
  }

  /**
   * Inserts a key into the tree. Returns true if a new key is inserted.
   *
   * @param key the key to insert
   * @return true if a new key is inserted, or false otherwise
   */
  public boolean insert(E key) {
    if (contains(key)) {
      return false;
    } else {
      root = insertInternal(key, root);
      // Root is always black.
      root.setBlack();
    }

    ++size;
    return true;
  }

  /**
   * Inserts a key into the starting node's subtree while preserving the properties of the tree. It
   * is possible that another node will be in the place of the starting node (could be null) after
   * some transformations, therefore this node is returned to the caller.
   *
   * @param key the key to insert
   * @param start the root of the targeted subtree
   * @return the node to be placed in the starting node's position
   */
  private RbNode<E> insertInternal(E key, RbNode<E> start) {
    if (start == null) {
      // Create a new red link.
      return new RbNode<>(key, true);
    }

    if (key.compareTo(start.key) < 0) {
      start.left = insertInternal(key, start.left);
    } else {
      // The key must not exist in the tree.
      start.right = insertInternal(key, start.right);
    }

    // Mitigate if invariants are broken after adding the new link.
    if (start.isRightLeaning()) {
      start = rotateLeft(start);
    }
    if (start.hasConsecutiveRedLinks()) {
      start = rotateRight(start);
    }
    if (start.hasTwoRedChildren()) {
      flipColors(start);
    }

    return start;
  }

  /**
   * Performs a left rotation around the pivot node. This is needed when the subtree is
   * right-leaning, and the right child will take the pivot's place after the operation.
   *
   * @param pivot the node on which the operation occurs
   * @return the node that replaces the pivot at the end
   */
  private RbNode<E> rotateLeft(RbNode<E> pivot) {
    RbNode<E> toReturn = pivot.right;
    pivot.right = toReturn.left;
    toReturn.color = pivot.color;
    pivot.setRed();
    toReturn.left = pivot;
    return toReturn;
  }

  /**
   * Performs a right rotation around the pivot node. This is needed when there are 2 consecutive
   * red links on the pivot's left subtree, and the left child will take the pivot's place after the
   * operation.
   *
   * @param pivot the node on which the operation occurs
   * @return the node that replaces the pivot at the end
   */
  private RbNode<E> rotateRight(RbNode<E> pivot) {
    RbNode<E> toReturn = pivot.left;
    pivot.left = toReturn.right;
    toReturn.color = pivot.color;
    pivot.setRed();
    toReturn.right = pivot;
    return toReturn;
  }

  /**
   * Turns both red children into black children on the pivot node. The pivot is also flipped to red
   * to preserve the perfect black balanced as required. This is similar to splitting a 4-node into
   * three 2-nodes.
   *
   * @param pivot the node on which to flip colors
   */
  private void flipColors(RbNode<E> pivot) {
    pivot.left.setBlack();
    pivot.right.setBlack();
    pivot.setRed();
  }

  /**
   * Deletes a key from the current tree. Returns true if a key is deleted.
   *
   * @param key the key to delete
   * @return true if a key is deleted after the operation, or false otherwise
   */
  public boolean delete(E key) {
    if (!contains(key)) {
      return false;
    } else if (size == 1) {
      root = null;
    }

    // TODO

    --size;
    return true;
  }

  /**
   * Converts the tree into a sorted list by traversing the nodes in order.
   *
   * @return a sorted list of all elements in the tree in ascending order
   */
  public List<E> toSortedList() {
    inOrderCache.clear();
    fillInOrderCache(root);
    return Collections.unmodifiableList(inOrderCache);
  }

  /**
   * Puts all elements found in the starting node's subtree into the cache by performing an in-order
   * traversal.
   *
   * @param start the starting node
   */
  private void fillInOrderCache(RbNode<E> start) {
    if (start == null) {
      return;
    }

    // Potentially fill in the left subtree first.
    fillInOrderCache(start.left);
    // Add this node's element.
    inOrderCache.add(start.key);
    // Potentially fill in the right subtree.
    fillInOrderCache(start.right);
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

    private enum Color {
      RED,
      BLACK;
    }

    E key;
    // Color of the link that connects this node to the parent.
    Color color;
    RbNode<E> left;
    RbNode<E> right;

    RbNode(E key) {
      this.key = key;
      this.color = Color.BLACK;
    }

    RbNode(E key, boolean isRed) {
      this.key = key;
      this.color = isRed ? Color.RED : Color.BLACK;
    }

    boolean isRed() {
      return color == Color.RED;
    }

    boolean isBlack() {
      return color == Color.BLACK;
    }

    void setRed() {
      this.color = Color.RED;
    }

    void setBlack() {
      this.color = Color.BLACK;
    }

    /**
     * Checks if this node has a red link connecting to its right child but not left child. The
     * situation is similar to a 3-node but with the wrong direction.
     *
     * @return true if this node is a right-leaning 3-node, or false otherwise
     */
    boolean isRightLeaning() {
      if (this.right != null && this.right.isRed()) {
        return this.left == null || this.left.isBlack();
      }
      return false;
    }

    /**
     * Checks if this node has two consecutive red links on its (left) subtree. This will not happen
     * on the right subtree as long as the properties of the red-black tree is maintained. The
     * situation is similar to a 4-node with this node's key being the right key.
     *
     * @return tree if the left child that has a red child is also red, or false otherwise
     */
    boolean hasConsecutiveRedLinks() {
      if (this.left != null && this.left.isRed()) {
        return this.left.left != null && this.left.left.isRed();
      }
      return false;
    }

    /**
     * Checks if both of this node's children are connected by red links. The situation is similar
     * to a 4-node with this node's key being the middle key.
     *
     * @return true if both children are red, or false otherwise
     */
    boolean hasTwoRedChildren() {
      return this.left != null && this.left.isRed() && this.right != null && this.right.isRed();
    }
  }
}
