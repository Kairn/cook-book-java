package io.esoma.cbj.ds;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class illustrates a classic 2-3 tree which is an extension of a binary search tree with
 * contains both 2-nodes (a node with one key and 2 children, the same structure as in any standard
 * binary tree) and 3-nodes (a node with 2 keys and 3 children, middle child represents a value
 * between the left and right keys). This tree is self-balancing during insert/delete operations. In
 * other words, all null keys are at the same distance from the root node. For simplicity, only
 * integer keys are supported in this illustration, but the same concept can easily be extended to
 * any general comparable class.
 */
public class BTree23 {

  private int size;
  private Node24 root;

  // Information for tracing an in-order traversal.
  int walkIndex;
  int[] walkCache;

  /**
   * Returns the size of the current tree.
   *
   * @return an int value representing the tree size.
   */
  public int size() {
    return size;
  }

  /**
   * Inserts an integer key into the tree. Returns true if a new key is inserted.
   *
   * @param key the key to insert
   * @return true if a new key is inserted, or false otherwise
   */
  public boolean insert(int key) {
    Node24 candidate = dig(key, root);
    if (candidate == null) {
      // The tree must be empty, so create the root.
      root = new Node24(key, null);
      ++size;
      return true;
    }

    if (candidate.match(key)) {
      // Key exists, so don't do anything.
      return false;
    }

    // At this point, the candidate must be a node at the bottom without children.
    // And the candidate will attempt to add the key to itself and balance on the way up if needed.
    Node24 newRoot = candidate.addKey(key);
    if (newRoot != null) {
      root = newRoot;
    }
    ++size;
    return true;
  }

  /**
   * Checks if the tree contains the specified key.
   *
   * @param key the key to search
   * @return true of the key exists in the tree, or false otherwise
   */
  public boolean contains(int key) {
    Node24 potential = dig(key, root);
    if (potential == null) {
      return false;
    }
    return potential.match(key);
  }

  /**
   * Deletes a key from the current tree. Returns true if a key is deleted.
   *
   * @param key the key to delete
   * @return true if a key is deleted after the operation, or false otherwise
   */
  public boolean delete(int key) {
    if (!contains(key)) {
      return false;
    }

    if (size == 1) {
      // Destroy the only node which is the root.
      root = null;
    } else {
      Node24 newRoot = root.deleteKey(key);
      if (newRoot != null) {
        root = newRoot;
      }
    }

    --size;
    return true;
  }

  /**
   * Returns an array view of all the keys in the tree in ascending order.
   *
   * @return a sorted int array of keys
   */
  public int[] toSortedArray() {
    walkAndFillInOrder();
    return Arrays.copyOf(walkCache, size);
  }

  @Override
  public String toString() {
    return String.format("BTree23{ %s }", Arrays.toString(toSortedArray()));
  }

  /**
   * Performs an in-order traversal of the tree and stores seen elements in an internal cache for
   * later use.
   */
  private void walkAndFillInOrder() {
    // Reset index and initialize array if needed.
    walkIndex = 0;
    if (walkCache == null || size > walkCache.length) {
      walkCache = new int[size];
    }

    walkAndFillInOrder(root);
  }

  /**
   * The internal method called from {@link #walkAndFillInOrder()} to perform the walk on a starting
   * node (root). This process itself is also recursive until the entire subtree is examined.
   *
   * @param start which node to start the walk from
   */
  private void walkAndFillInOrder(Node24 start) {
    if (start == null) {
      return;
    }

    if (start.is2Node()) {
      walkAndFillInOrder(start.childLeft);
      walkCache[walkIndex++] = start.primary;
      walkAndFillInOrder(start.childRight);
    } else {
      walkAndFillInOrder(start.childLeft);
      walkCache[walkIndex++] = start.primary;
      walkAndFillInOrder(start.childMidLeft);
      walkCache[walkIndex++] = start.secondary;
      walkAndFillInOrder(start.childRight);
    }
  }

  /**
   * Searches the subtree starting from a node for the specified key and digs up the deepest child
   * where the key could be residing. This process is recursive. The returned node might NOT contain
   * the key, but it will be where the key is to be inserted (if not present yet).
   *
   * @param key the key to search for
   * @param start where to start the search
   * @return the bottommost node that might contain the key, or null of the tree is empty
   */
  private Node24 dig(int key, Node24 start) {
    if (start == null) {
      return null;
    }

    if (start.match(key)) {
      // The key is found.
      return start;
    }

    if (!start.hasChildren()) {
      // Cannot dig any deeper.
      return start;
    }

    // Continue to dig on one of the children.
    if (start.is2Node()) {
      // For a 2-node, compare with the node's primary and go left or right.
      if (key < start.primary) {
        return dig(key, start.childLeft);
      } else {
        return dig(key, start.childRight);
      }
    } else {
      // Must be a 3-node, so choose a direction by comparing from left to right.
      if (key < start.primary) {
        return dig(key, start.childLeft);
      } else if (key > start.secondary) {
        return dig(key, start.childRight);
      } else {
        // Dig on the middle child when the key is between the two node's keys.
        return dig(key, start.childMidLeft);
      }
    }
  }

  /**
   * A flexible node structure that is the basic component in a 2-3 tree. It can represent a 2, 3,
   * or 4-node (temporary) on demand. Because the tree is required to be perfectly balanced, this
   * node is also always balanced, meaning it will either have no children, or all of its (required)
   * children (depending on the form) will be non-null.
   */
  private static class Node24 {

    // Primary is the only key when the node is a 2-node. It is always the leftmost key in extended
    // form(s).
    Integer primary;
    // Secondary is always the rightmost key when the node is a 3 or 4-node.
    Integer secondary;
    // Tertiary is always the middle key when the node is a temporary 4-node.
    Integer tertiary;

    // The parent node from which this node is extended from.
    Node24 parent;

    // This always represent the leftmost child in all node forms.
    Node24 childLeft;
    // This represents the middle child in a 3-node or the second from left child in a 4-node.
    Node24 childMidLeft;
    // This always represent the second from right child in a 4-node, or it will be null otherwise.
    Node24 childMidRight;
    // This always represent the rightmost child in all node forms.
    Node24 childRight;

    Node24(int primary) {
      this.primary = primary;
    }

    /**
     * Creates a full 2-node directly. Both children must be non-null.
     *
     * @param primary the key of the node
     * @param childLeft the left child node
     * @param childRight the right child node
     */
    Node24(int primary, Node24 childLeft, Node24 childRight) {
      this.primary = primary;
      this.childLeft = Objects.requireNonNull(childLeft);
      this.childRight = Objects.requireNonNull(childRight);
      childLeft.parent = this;
      childRight.parent = this;
    }

    Node24(int primary, Node24 parent) {
      this.primary = primary;
      this.parent = parent;
    }

    boolean is2Node() {
      return secondary == null;
    }

    boolean is3Node() {
      return secondary != null && tertiary == null;
    }

    boolean is4Node() {
      return tertiary != null;
    }

    /**
     * Tries to match the provided key with elements in the node. This is non-recursive.
     *
     * @param key the key to match
     * @return true if the key is contained in this node, or false otherwise
     */
    boolean match(int key) {
      Integer keyWrapped = key;
      return keyWrapped.equals(primary)
          || keyWrapped.equals(secondary)
          || keyWrapped.equals(tertiary);
    }

    /**
     * Checks if the node has (non-null) children.
     *
     * @return true if the node has children, or false otherwise
     */
    boolean hasChildren() {
      return childLeft != null;
    }

    /**
     * Add the given key to the node. If this is a 2-node, the key is directly added to the node
     * structure with children adjusted. If this is a 3-node, it will become a temporary 4-node and
     * be split up later. If a new root node is created during the split call(s) (recursive), it is
     * returned to the caller. This method itself is non-recursive.
     *
     * @param key the key to be added
     * @return the new root node if created
     */
    Node24 addKey(int key) {
      // This method must only be called on a node without children.
      if (this.is2Node()) {
        // 2-node is the simple case, a key is added without further action.
        if (key < this.primary) {
          // Key is inserted in the left (primary) position.
          this.secondary = this.primary;
          this.primary = key;
        } else {
          this.secondary = key;
        }
        // No need to split anything or creating new nodes.
        return null;
      }

      // Create a 4-node first.
      if (key < this.primary) {
        // Old left key is now the middle key.
        this.tertiary = this.primary;
        this.primary = key;
      } else if (key > this.secondary) {
        // Old right key is now the middle key.
        this.tertiary = this.secondary;
        this.secondary = key;
      } else {
        // New key goes directly in the middle.
        this.tertiary = key;
      }

      // Since a 4-node is born, split it.
      return this.split();
    }

    /**
     * Splits the node (if is a 4-node) by pushing the middle key up one level (insert into the
     * parent if present) and re-adjusting the children. This process is repeated until there are no
     * more 4-nodes on the way up to the root. If a new root is created at the top level, it is
     * returned to the caller.
     *
     * @return the new root node if created
     */
    private Node24 split() {
      if (!this.is4Node()) {
        if (this.parent == null) {
          return null;
        } else {
          return this.parent.split();
        }
      }

      // First, both the left (primary) and right (secondary) keys will become new 2-nodes.
      Node24 leftPartition;
      Node24 rightPartition;
      if (this.hasChildren()) {
        leftPartition = new Node24(this.primary, this.childLeft, this.childMidLeft);
        rightPartition = new Node24(this.secondary, this.childMidRight, this.childRight);
      } else {
        leftPartition = new Node24(this.primary);
        rightPartition = new Node24(this.secondary);
      }

      // Where to push the middle key.
      Node24 destNode = this.parent;

      if (destNode == null) {
        // A new root must be created, and the process ends.
        return new Node24(this.tertiary, leftPartition, rightPartition);
      } else {
        // Both partitions must become the children of the destination.
        leftPartition.parent = destNode;
        rightPartition.parent = destNode;
      }

      if (destNode.is2Node()) {
        // The parent will become a 3-node with a new key inserted.
        if (this.tertiary < destNode.primary) {
          // Move the middle key to the parent's left.
          destNode.secondary = destNode.primary;
          destNode.primary = this.tertiary;
          // Partitions will be attached to the left side.
          destNode.childLeft = leftPartition;
          destNode.childMidLeft = rightPartition;
          // The right child already exists and won't need to be changed.
        } else {
          // Move the middle key to the parent's right.
          destNode.secondary = this.tertiary;
          // Partitions will be attached to the right side.
          destNode.childMidLeft = leftPartition;
          destNode.childRight = rightPartition;
          // The left child already exists and won't need to be changed.
        }
        // Continue on the way up.
        return destNode.split();
      }

      // Make the destination (parent) node a 4-node.
      if (this.tertiary < destNode.primary) {
        // Insert the middle key to the left and shift other keys.
        destNode.tertiary = destNode.primary;
        destNode.primary = this.tertiary;
        // Attach partitions to the left side.
        destNode.childMidRight = destNode.childMidLeft;
        destNode.childMidLeft = rightPartition;
        destNode.childLeft = leftPartition;
      } else if (this.tertiary > destNode.secondary) {
        // Insert the middle key to the right and shift other keys.
        destNode.tertiary = destNode.secondary;
        destNode.secondary = this.tertiary;
        // Attach partitions to the right side.
        destNode.childMidRight = leftPartition;
        destNode.childRight = rightPartition;
      } else {
        // Insert the middle key to the middle as well.
        destNode.tertiary = this.tertiary;
        // Attach partitions to the middle.
        destNode.childMidLeft = leftPartition;
        destNode.childMidRight = rightPartition;
      }

      // Recursively split up the parent as well.
      return destNode.split();
    }

    /**
     * Deletes the specified key from the subtree starting from this node. The key is guaranteed to
     * exist within the subtree. If a new root is created after restructuring, it is returned to the
     * caller.
     *
     * @param key the key to delete
     * @return the new root node if created
     */
    Node24 deleteKey(int key) {
      if (this.parent == null && this.is2Node()) {
        // Perform root specific transformation.
        if (this.childLeft.is2Node() && this.childRight.is2Node()) {
          // Merge both children into the root.
          this.tertiary = this.primary;
          this.primary = this.childLeft.primary;
          this.secondary = this.childRight.primary;
          // Replace the children with grandchildren.
          this.childMidLeft = this.childLeft.childRight;
          this.childMidRight = this.childRight.childLeft;
          this.childLeft = this.childLeft.childLeft;
          this.childRight = this.childRight.childRight;
          // Readjust the parent for moved children.
          this.ensureParent();
          // Repeat the process on the root.
          return this.deleteKey(key);
        } else if (this.childLeft.is3Node() && this.childRight.is3Node()) {
          // Both children don't need extra transformation.
          if (key == this.primary) {
            // Replace this with the successor from the right child and delete the same.
            this.primary = this.childRight.findMin();
            return this.childRight.deleteKey(this.primary);
          } else if (key < this.primary) {
            return this.childLeft.deleteKey(key);
          } else {
            return this.childRight.deleteKey(key);
          }
        } else if (this.childLeft.is2Node()) {
          // The right child must be a 3-node.
          if (key <= this.primary) {
            // Must borrow a key from the right child and move this key to the left.
            this.primary = this.rotateLeft(this.childLeft, this.primary, this.childRight);
            // Delete from the left.
            return this.childLeft.deleteKey(key);
          } else {
            return this.childRight.deleteKey(key);
          }
        } else {
          // The left child must be a 3-node.
          if (key >= this.primary) {
            // Must borrow a key from the left child and move this key to the right.
            this.primary = this.rotateRight(this.childLeft, this.primary, this.childRight);
            // Delete from the right.
            return this.childRight.deleteKey(key);
          } else {
            return this.childLeft.deleteKey(key);
          }
        }
      }

      // At this point, the current node is guaranteed to NOT be a 2-node.
      if (!this.hasChildren()) {
        // This node must contain the key, so delete it.
        this.deleteKeyInside(key);
        // Split any temporary 4-nodes on the way up.
        return this.split();
      }

      // Ensure the child for the next step is not a 2-node.
      Node24 nextChild;
      // The peer node to help the next child to become a non 2-node.
      Node24 helper;
      if (key < this.primary) {
        // The next child is the left child.
        nextChild = this.childLeft;
        if (nextChild.is2Node()) {
          // Help is needed from the right peer.
          helper = this.childMidLeft;
          if (helper.is3Node()) {
            // Let's rotate to the left.
            this.primary = this.rotateLeft(nextChild, this.primary, helper);
          } else {
            // Let's merge peers.
            Node24 mergedChild = this.mergeTo4Node(nextChild, this.primary, helper);
            // Attach the merged child on the left.
            this.replaceLeftKeyWithChild(mergedChild);
          }
        }
      } else if (key >= this.secondary) {
        // The next child is the right child.
        nextChild = this.childRight;
        if (nextChild.is2Node()) {
          // Help is needed from the left peer.
          helper = this.is3Node() ? this.childMidLeft : this.childMidRight;
          if (helper.is3Node()) {
            // Let's rotate to the right.
            this.secondary = this.rotateRight(helper, this.secondary, nextChild);
          } else {
            // Let's merge peers.
            Node24 mergedChild = this.mergeTo4Node(helper, this.secondary, nextChild);
            nextChild = mergedChild;
            // Attach the merged child on the right.
            this.replaceRightKeyWithChild(mergedChild);
          }
        }
      } else if (this.tertiary != null && key >= this.tertiary) {
        // The next child is the mid-right child.
        nextChild = this.childMidRight;
        if (nextChild.is2Node()) {
          // Help is needed from the right peer.
          helper = this.childRight;
          if (helper.is3Node()) {
            // Let's rotate to the left.
            this.secondary = this.rotateLeft(nextChild, this.secondary, helper);
          } else {
            // Let's merge peers.
            Node24 mergedChild = this.mergeTo4Node(nextChild, this.secondary, helper);
            // Attach the merged child on the right.
            this.replaceRightKeyWithChild(mergedChild);
          }
        }
      } else {
        // The next child is the mid-left child.
        nextChild = this.childMidLeft;
        if (nextChild.is2Node()) {
          // Help is needed from the left peer.
          helper = this.childLeft;
          if (helper.is3Node()) {
            // Let's rotate to the right.
            this.primary = this.rotateRight(helper, this.primary, nextChild);
          } else {
            // Let's merge peers.
            Node24 mergedChild = this.mergeTo4Node(helper, this.primary, nextChild);
            nextChild = mergedChild;
            // Attach the merged child on the left.
            this.replaceLeftKeyWithChild(mergedChild);
          }
        }
      }

      // If the key is present in the current node, replace it with the successor (min key) in the
      // next child. Then, the process becomes deleting the successor in the next child.
      if (this.primary == key) {
        this.primary = nextChild.findMin();
        key = this.primary;
      } else if (this.tertiary != null && this.tertiary == key) {
        this.tertiary = nextChild.findMin();
        key = this.tertiary;
      } else if (this.secondary != null && this.secondary == key) {
        this.secondary = nextChild.findMin();
        key = this.secondary;
      }

      return nextChild.deleteKey(key);
    }

    /**
     * Iterates through this node's children and ensure they have this node as the parent. A
     * convenient method used internally only.
     */
    private void ensureParent() {
      if (this.childLeft != null) {
        this.childLeft.parent = this;
      }
      if (this.childMidLeft != null) {
        this.childMidLeft.parent = this;
      }
      if (this.childMidRight != null) {
        this.childMidRight.parent = this;
      }
      if (this.childRight != null) {
        this.childRight.parent = this;
      }
    }

    /**
     * From the parent node's view, rotates a key from right to left with the specified children
     * (peers). The right peer is guaranteed to be a 3-node, and the left peer must be a 2-node.
     *
     * @param leftPeer the left peer (borrower)
     * @param parentKey the parent key between the peers on the parent
     * @param rightPeer the right peer (lender)
     * @return the key to replace the original parent key
     */
    private int rotateLeft(Node24 leftPeer, int parentKey, Node24 rightPeer) {
      // Move the parent key to the left peer.
      leftPeer.secondary = parentKey;
      leftPeer.childMidLeft = leftPeer.childRight;
      leftPeer.childRight = rightPeer.childLeft;

      leftPeer.ensureParent();
      int keyToReturn = rightPeer.primary;

      // Trim the right peer to a 2-node.
      rightPeer.primary = rightPeer.secondary;
      rightPeer.secondary = null;
      rightPeer.childLeft = rightPeer.childMidLeft;
      rightPeer.childMidLeft = null;

      return keyToReturn;
    }

    /**
     * From the parent node's view, rotates a key from left to right with the specified children
     * (peers). The left peer is guaranteed to be a 3-node, and the right peer must be a 2-node.
     *
     * @param leftPeer the left peer (lender)
     * @param parentKey the parent key between the peers on the parent
     * @param rightPeer the right peer (borrower)
     * @return the key to replace the original parent key
     */
    private int rotateRight(Node24 leftPeer, int parentKey, Node24 rightPeer) {
      // Move the parent key to the right peer.
      rightPeer.secondary = rightPeer.primary;
      rightPeer.primary = parentKey;
      rightPeer.childMidLeft = rightPeer.childLeft;
      rightPeer.childLeft = leftPeer.childRight;

      rightPeer.ensureParent();
      int keyToReturn = leftPeer.secondary;

      // Trim the left peer to a 2-node.
      leftPeer.secondary = null;
      leftPeer.childRight = leftPeer.childMidLeft;
      leftPeer.childMidLeft = null;

      return keyToReturn;
    }

    /**
     * From the parent node's view, merges its two children with a key in the middle into a 4-node.
     * Both peers must be 2-nodes, and the left peer is the target to receive extra keys and
     * children.
     *
     * @param leftPeer the left child (receiver)
     * @param parentKey the parent key that goes into the middle of the merged node
     * @param rightPeer the right child (giver)
     * @return the merged node
     */
    private Node24 mergeTo4Node(Node24 leftPeer, int parentKey, Node24 rightPeer) {
      leftPeer.secondary = rightPeer.primary;
      leftPeer.tertiary = parentKey;
      leftPeer.childMidLeft = leftPeer.childRight;
      leftPeer.childMidRight = rightPeer.childLeft;
      leftPeer.childRight = rightPeer.childRight;
      leftPeer.ensureParent();
      return leftPeer;
    }

    /**
     * Removes the left key in the node and attach a new left child instead. This node might be a
     * 3-node or a 4-node.
     *
     * @param child the child to be attached
     */
    private void replaceLeftKeyWithChild(Node24 child) {
      if (this.is3Node()) {
        this.primary = this.secondary;
        this.secondary = null;
        this.childMidLeft = null;
      } else {
        this.primary = this.tertiary;
        this.tertiary = null;
        this.childMidLeft = this.childMidRight;
        this.childMidRight = null;
      }

      this.childLeft = child;
      this.ensureParent();
    }

    /**
     * Removes the right key in the node and attach a new right child instead. This node might be a
     * 3-node or a 4-node.
     *
     * @param child the child to be attached
     */
    private void replaceRightKeyWithChild(Node24 child) {
      if (this.is3Node()) {
        this.secondary = null;
        this.childMidLeft = null;
      } else {
        this.secondary = this.tertiary;
        this.tertiary = null;
        this.childMidRight = null;
      }

      this.childRight = child;
      this.ensureParent();
    }

    /**
     * Deletes a key inside this node. The key is guaranteed to exist, and this node is guaranteed
     * to have no children and NOT be a 2-node. This is only called internally as the final step of
     * key deletion.
     *
     * @param key the key to delete
     */
    private void deleteKeyInside(int key) {
      if (this.is3Node()) {
        if (this.primary == key) {
          this.primary = this.secondary;
        }
        // Right key is always deleted as it becomes a 2-node.
        this.secondary = null;
      } else {
        // Must be a temporary 4-node.
        if (this.primary == key) {
          this.primary = this.tertiary;
        } else if (this.secondary == key) {
          this.secondary = this.tertiary;
        }
        // Middle key is always deleted as it becomes a 3-node.
        this.tertiary = null;
      }
    }

    /**
     * Finds the key with the minimum value in this node's subtree.
     *
     * @return the min key
     */
    private int findMin() {
      if (!this.hasChildren()) {
        return this.primary;
      } else {
        return this.childLeft.findMin();
      }
    }
  }
}
