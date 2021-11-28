package io.esoma.cbj.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for implementing the AVL Tree data structure. An AVL tree (named after inventors
 * Adelson-Velsky and Landis) is a self-balancing binary search tree. For any tree node, the heights
 * of the two child subtrees differ by at most one; if at any time they differ by more than one,
 * rebalancing is done to restore this property. This data structure enables quick search,
 * insertion, and deletion of elements, but duplicates are not allowed. Elements will be organized
 * based on their natural ordering.
 *
 * @author Eddy Soma
 * @param <E> the type of elements held in this collection
 */
public class AvlTree<E extends Comparable<E>> {

  // Internal state.
  private int size;
  private AtNode<E> root;
  private AtNode<E> del;
  /*
   * Default constructor for creating a blank AVL tree.
   */
  public AvlTree() {
    super();
  }

  /**
   * Queries and returns the number of elements in the tree.
   *
   * @return the tree size
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Checks the tree to see if the given element is present.
   *
   * @param e the element to search
   * @return true if the element is present, or false otherwise
   */
  public boolean contains(E e) {
    return this.checkElement(this.root, e);
  }

  /**
   * Checks if the given node contains the requested element, and if not, recursively checks its
   * children based on comparison.
   *
   * @param n the node to check
   * @param e the element to search
   * @return true if the node or one of its children has the given element, or false otherwise
   */
  private boolean checkElement(AtNode<E> n, E e) {
    E self = n.key;
    if (self.equals(e)) {
      return true;
    } else if (e.compareTo(self) > 0) {
      return n.right != null && this.checkElement(n.right, e);
    } else {
      return n.left != null && this.checkElement(n.left, e);
    }
  }

  /**
   * Inserts the given element into the AVL tree. The operation performs a basic search and appends
   * the new node at the appropriate location. If the balance factor constraint is violated after
   * insertion, the tree rotates to maintain a perfect balance.
   *
   * @param e the element to be inserted
   * @return the element itself if inserted successfully, or null a duplicate exists
   */
  public E insert(E e) {
    // Create a new node.
    AtNode<E> n = new AtNode<>(e);

    if (this.root == null) {
      this.root = n;
      ++this.size;
      return e;
    } else {
      if (this.appendNode(this.root, n) == -1) {
        return null;
      } else {
        ++this.size;
        // Balance the tree if needed.
        this.rebalance(n);
        return e;
      }
    }
  }

  /**
   * Tries to add a new node to the host (parent) node as a child.
   *
   * @param host the parent node
   * @param n the new node
   * @return an integer representing the height of host after appending the new node, or -1 if the
   *     new node is not inserted
   */
  private int appendNode(AtNode<E> host, AtNode<E> n) {
    // Duplicate element will be rejected.
    if (host.key.equals(n.key)) {
      return -1;
    }

    if (n.key.compareTo(host.key) > 0) {
      // Right side.
      if (host.right == null) {
        host.right = n;
        n.parent = host;
        host.rh = 1 + Math.max(n.lh, n.rh);
        return Math.max(host.lh, host.rh);
      } else {
        int h = this.appendNode(host.right, n);
        if (h == -1) {
          return -1;
        } else {
          host.rh = h + 1;
          return Math.max(host.lh, host.rh);
        }
      }
    } else {
      // Left side.
      if (host.left == null) {
        host.left = n;
        n.parent = host;
        host.lh = 1 + Math.max(n.lh, n.rh);
        return Math.max(host.lh, host.rh);
      } else {
        int h = this.appendNode(host.left, n);
        if (h == -1) {
          return -1;
        } else {
          host.lh = h + 1;
          return Math.max(host.lh, host.rh);
        }
      }
    }
  }

  /**
   * Deletes the given element from the tree if present. If it has one child, this child will be
   * promoted to take its place. If the node has two children, its value will be replaced by its
   * closest successor, and then the successor will be deleted instead. Rebalancing will be
   * performed if necessary once all orphan nodes are placed.
   *
   * @param e the element to delete
   * @return the element itself if deleted successfully, or null if not present
   */
  public E delete(E e) {
    if (this.root == null) {
      return null;
    } else {
      if (this.cutNode(this.root, e) == -2) {
        return null;
      } else {
        --this.size;
        // Balance the tree if necessary.
        this.rebalance(this.del);
        this.del = null;
        return e;
      }
    }
  }

  /**
   * Cuts the node off from the tree if it contains the same element as the input. This is
   * recursively called on the appropriate child node until the target node is found or it becomes
   * apparent that the element is not present.
   *
   * @param n the node to start
   * @param e the target value to be cut
   * @return the height of the node once the target has been cut from the tree, or -2 if the target
   *     is not found
   */
  private int cutNode(AtNode<E> n, E e) {
    // Check if the current node is the target.
    if (n.key.equals(e)) {
      // Node has no children.
      if (n.left == null && n.right == null) {
        // Node is removed from the tree.
        if (n.parent == null) {
          this.root = null;
        } else if (n.parent.left == n) {
          n.parent.left = null;
        } else {
          n.parent.right = null;
        }
        // Mark the parent as the potential imbalance point.
        this.del = n.parent;
        return -1;
      }

      // Node has one right child.
      if (n.left == null) {
        if (n.parent == null) {
          this.root = n.right;
        } else if (n.parent.left == n) {
          n.parent.left = n.right;
          n.right.parent = n.parent;
        } else {
          n.parent.right = n.right;
          n.right.parent = n.parent;
        }
        this.del = n.parent;
        return Math.max(n.right.lh, n.right.rh);
      }

      // Node has one left child.
      if (n.right == null) {
        if (n.parent == null) {
          this.root = n.left;
        } else if (n.parent.left == n) {
          n.parent.left = n.left;
          n.left.parent = n.parent;
        } else {
          n.parent.right = n.left;
          n.left = n.parent;
        }
        this.del = n.parent;
        return Math.max(n.left.lh, n.left.rh);
      }

      // Node has two children.
      // Find the successor value.
      E suc = this.findSuccessor(n.right);
      // Replace the value.
      n.key = suc;
      // Delete the successor.
      int h = this.cutNode(n.right, suc);
      n.rh = h + 1;
      return Math.max(n.lh, n.rh);
    } else {
      // Look for child nodes.
      if (e.compareTo(n.key) > 0) {
        // Go to the right.
        if (n.right == null) {
          return -2;
        } else {
          int h = this.cutNode(n.right, e);
          if (h == -2) {
            return -2;
          } else {
            n.rh = h + 1;
            return Math.max(n.lh, n.rh);
          }
        }
      } else {
        // Go to the left.
        if (n.left == null) {
          return -2;
        } else {
          int h = this.cutNode(n.left, e);
          if (h == -2) {
            return -2;
          } else {
            n.lh = h + 1;
            return Math.max(n.lh, n.rh);
          }
        }
      }
    }
  }

  /**
   * Finds the smallest (leftmost) child that branched from the given node.
   *
   * @param n the node to start the search
   * @return the value of the leftmost child
   */
  private E findSuccessor(AtNode<E> n) {
    if (n.left == null) {
      return n.key;
    } else {
      return this.findSuccessor(n.left);
    }
  }

  /**
   * Checks the balance factor of the given node, and if the constraint is violated, triggers the
   * operation to fix the tree structure. All parent nodes will also be checked once the current
   * node is in shape.
   *
   * @param n the node to check
   */
  private void rebalance(AtNode<E> n) {
    // Termination condition.
    if (n == null) {
      return;
    }

    // Update the heights of subtrees.
    n.lh = n.left == null ? 0 : 1 + Math.max(n.left.lh, n.left.rh);
    n.rh = n.right == null ? 0 : 1 + Math.max(n.right.lh, n.right.rh);

    // Check the height difference.
    int hd = Math.abs(n.lh - n.rh);
    if (hd > 1) {
      if (n.lh > n.rh) {
        // Left side is heavier.
        if (n.left.lh <= n.left.rh) {
          // Left shift first.
          this.lShift(n.left);
        }
        this.rShift(n);
      } else {
        // Right side is heavier.
        if (n.right.rh <= n.right.lh) {
          // Right shift first.
          this.rShift(n.right);
        }
        this.lShift(n);
      }
    }

    this.rebalance(n.parent);
  }

  /**
   * Performs a left shift (rotation) around the given node.
   *
   * @param n the pivot node
   */
  private void lShift(AtNode<E> n) {
    // Take the right child as it will become the new parent.
    AtNode<E> rc = n.right;
    if (rc == null) {
      return;
    }

    // Hold on to the left child of the right child as it will be replaced by the
    // pivot.
    AtNode<E> hold = rc.left;
    // Hold on to the parent of the pivot as it will become the parent of the right
    // child.
    AtNode<E> pnt = n.parent;

    // Attach the pivot as the right child's left child.
    rc.left = n;
    n.parent = rc;
    // Update the subtree height of the new top.
    rc.lh = 1 + Math.max(n.lh, n.rh);

    // Attach the parent.
    rc.parent = pnt;
    if (pnt != null) {
      if (pnt.left == n) {
        pnt.left = rc;
      } else {
        pnt.right = rc;
      }
    } else {
      this.root = rc;
    }

    // Attach the held node to the pivot's right.
    n.right = hold;
    if (hold != null) {
      hold.parent = n;
      n.rh = 1 + Math.max(hold.lh, hold.rh);
    } else {
      n.rh = 0;
    }
  }

  /**
   * Performs a right shift (rotation) around the given node.
   *
   * @param n the pivot node
   */
  private void rShift(AtNode<E> n) {
    AtNode<E> lc = n.left;
    if (lc == null) {
      return;
    }

    AtNode<E> hold = lc.right;
    AtNode<E> pnt = n.parent;

    lc.right = n;
    n.parent = lc;
    lc.rh = 1 + Math.max(n.lh, n.rh);

    lc.parent = pnt;
    if (pnt != null) {
      if (pnt.left == n) {
        pnt.left = lc;
      } else {
        pnt.right = lc;
      }
    } else {
      this.root = lc;
    }

    n.left = hold;
    if (hold != null) {
      hold.parent = n;
      n.lh = 1 + Math.max(hold.lh, hold.rh);
    } else {
      n.lh = 0;
    }
  }

  /**
   * Converts the ALV tree into a sorted list by traversing the nodes in order.
   *
   * @return a sorted list of all elements collected in the tree
   */
  public List<E> toSortedList() {
    // Create a list to store the nodes.
    List<E> li = new ArrayList<>();
    if (this.size == 0) {
      return li;
    }

    this.appendNodeToList(this.root, li);
    return li;
  }

  /**
   * Performs an in order traversal of the given node and its children and appends the elements to
   * the list in ascending order.
   *
   * @param n the node to traverse
   * @param li the list that collects the elements
   */
  private void appendNodeToList(AtNode<E> n, List<E> li) {
    // Check the left child.
    if (n.left != null) {
      this.appendNodeToList(n.left, li);
    }
    // Add the current node element.
    li.add(n.key);
    // Check the right child.
    if (n.right != null) {
      this.appendNodeToList(n.right, li);
    }
  }

  @Override
  /*
   * Returns a string representation of the AVL tree. Elements will be shown in
   * ascending order.
   */
  public String toString() {
    StringBuilder bu = new StringBuilder();
    for (E e : this.toSortedList()) {
      bu.append(e.toString());
      bu.append(' ');
    }

    return "AvlTree [" + bu.toString().trim() + "]";
  }

  /**
   * Helper class for tree nodes.
   *
   * @author Eddy Soma
   * @param <E> the type of elements held in the node
   */
  private static class AtNode<E extends Comparable<E>> {

    E key;
    AtNode<E> parent;
    AtNode<E> left;
    AtNode<E> right;
    // Store the heights of left and right subtrees.
    int lh;
    int rh;

    /*
     * Constructor for creating a basic node with the given key.
     */
    AtNode(E key) {
      this.key = key;
    }
  }
}
