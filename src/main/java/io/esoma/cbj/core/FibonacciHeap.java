package io.esoma.cbj.core;

import java.util.LinkedList;

/**
 * Class for implementing the Fibonacci Heap data structure. A Fibonacci heap is
 * used for priority queue operations, consisting of a collection of
 * heap-ordered trees. It has a better amortized running time than many other
 * priority queue data structures including the binary heap and binomial heap.
 * This implementation is a min-heap, and it will order elements only by their
 * natural ordering.
 * 
 * @author Eddy Soma
 *
 * @param <E> the type of elements held in this heap
 */
public class FibonacciHeap<E> {

	/**
	 * Helper class for heap nodes.
	 * 
	 * @author Eddy Soma
	 *
	 * @param <E> the type of elements stored in the heap
	 */
	private static class FhNode<E> {

		E key;
		boolean marked;
		FhNode<E> parent;
		LinkedList<E> children;

		FhNode(E key) {
			this.key = key;
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

	}

	// Internal state.
	private int size;
	private FhNode<E> minNode;
	private LinkedList<FhNode<E>> mainChain;

	/*
	 * Constructor for creating a blank Fibonacci heap.
	 */
	public FibonacciHeap() {
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
	 * Pushes a new element to the heap. In a Fibonacci heap, this operation is to
	 * simply add this element as the root of a new node tree which will then be
	 * appended to the main chain. The pointer to the minimum element will be
	 * updated if necessary.
	 * 
	 * @param e
	 */
	public void push(E e) {
		//
	}

	/**
	 * Queries and returns the head of the heap, but the head will not be removed
	 * from the heap.
	 * 
	 * @return the head (the least element) of the heap, or null of the heap is
	 *         empty
	 */
	public E peekMin() {
		return this.minNode.key;
	}

	/**
	 * Retrieves the least element and removes it from the heap. The children of the
	 * removed node will be promoted to become parts of the root list. Any time a
	 * node has lost two children, it will also be dumped to the root list. At the
	 * end, trees in the main chain will be merged if they have the same degree
	 * (number of direct children). The pointer to the next minimum node will also
	 * be updated appropriately.
	 * 
	 * @return the head of the heap, or null of the heap is empty
	 */
	public E popMin() {
		E min = this.minNode.key;
		return min;
	}

	@Override
	/**
	 * Returns a string representation of the Fibonacci heap. All elements will be
	 * listed but in arbitrary order.
	 */
	public String toString() {
		return "FibonacciHeap []";
	}

}
