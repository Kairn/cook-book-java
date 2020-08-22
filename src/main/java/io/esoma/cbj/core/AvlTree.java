package io.esoma.cbj.core;

/**
 * Class for implementing the AVL Tree data structure. An AVL tree (named after
 * inventors Adelson-Velsky and Landis) is a self-balancing binary search tree.
 * For any tree node, the heights of the two child subtrees differ by at most
 * one; if at any time they differ by more than one, rebalancing is done to
 * restore this property. This data structure enables quick search, insertion,
 * and deletion of elements, but duplicates are not allowed. Elements will be
 * organized based on their natural ordering.
 * 
 * @author Eddy Soma
 *
 * @param <E> the type of elements held in this collection
 */
public class AvlTree<E extends Comparable<E>> {

	/**
	 * Helper class for tree nodes.
	 * 
	 * @author Eddy Soma
	 *
	 * @param <E> the type of elements held in the node
	 */
	private static class AtNode<E extends Comparable<E>> {

		E key;
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

	// Internal state.
	private int size;
	private AtNode<E> root;

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
		return false;
	}

	/**
	 * Inserts the given element into the AVL tree. The operation performs a basic
	 * search and appends the new node at the appropriate location. If the balance
	 * factor constraint is violated after insertion, the tree rotates to maintain a
	 * perfect balance.
	 * 
	 * @param e the element to be inserted
	 * @return the element itself if inserted successfully, or null a duplicate
	 *         exists
	 */
	public E insert(E e) {
		return e;
	}

	/**
	 * Deletes the given element from the tree if present. If it has children, one
	 * of them will be promoted to take its place. Rebalancing will be performed if
	 * necessary once all orphan nodes are placed.
	 * 
	 * @param e the element to delete
	 * @return the element itself if deleted successfully, or null if not present
	 */
	public E delete(E e) {
		return e;
	}

	/**
	 * Converts the ALV tree into a sorted array by traversing the nodes in order.
	 * 
	 * @return a sorted array of all elements collected in the tree
	 */
	public E[] toSortedArray() {
		return null;
	}

	@Override
	/*
	 * Returns a string representation of the AVL tree. Elements will be shown in
	 * ascending order.
	 */
	public String toString() {
		StringBuilder bu = new StringBuilder();
		for (E e : this.toSortedArray()) {
			bu.append(e.toString());
			bu.append(' ');
		}

		return "AvlTree [" + bu.toString().trim() + "]";
	}

}
