package io.esoma.cbj.algo;

import java.util.Stack;

import io.esoma.cbj.core.ArrayCore;
import io.esoma.cbj.core.BinaryInsertionSort;

/**
 * Class for implementing the Timsort algorithm. Timsort is a hybrid stable
 * sorting algorithm, derived from merge sort and insertion sort, designed to
 * perform well on many kinds of real-world data. It was implemented by Tim
 * Peters in 2002 for use in the Python programming language. It is also used to
 * sort arrays of non-primitive type in Java SE 7, on the Android platform, in
 * GNU Octave, on V8, and Swift. Timsort was designed to take advantage of runs
 * of consecutive ordered elements that already exist in most real-world data,
 * natural runs. In a nutshell, the main routine marches over the array once,
 * left to right, alternately identifying the next run, then merging it into the
 * previous runs "intelligently".
 * 
 * @author Eddy Soma
 *
 */
public class TimSort {

	// For arrays of length less than 64, this sort functions like a binary
	// insertion sort.
	private static final int MIN_RUN_THRESHOLD = 64;
	// A winning streak of 7 for a run is the initial trigger for entering galloping
	// mode for merging.
	// A high number can be set to disable galloping.
	private static final int MIN_GALLOP_INIT = 7;

	// This keeps track of the ideal galloping threshold which adapts appropriately
	// based on the data for each sorting case.
	private static int min_gallop = MIN_GALLOP_INIT;
	// The minimum length of a run will be calculated at the start of the algorithm.
	private static int min_run = 32;
	// The stack of runs
	private static Stack<Run> run_stack = null;

	/**
	 * Performs the standard Timsort for the given array. The array is theoretically
	 * sorted in-place but a reference is also returned. Galloping is enabled when
	 * appropriate for the merging process but only standard binary search is
	 * employed for the task. This sort is highly efficient for large data sets with
	 * natural patterns, but for random data it is not necessarily faster than other
	 * optimized sorts. Binary insertion sort is leveraged for sorting small data
	 * sets.
	 * 
	 * @param array the input array
	 * @return the sorted array
	 */
	public static int[] sort(int[] array) {
		// Initialize parameters and the run stack.
		int n = array.length;
		min_gallop = MIN_GALLOP_INIT;
		min_run = calcMinRun(n);
		run_stack = new Stack<>();

		// This flag controls whether we create a new run.
		boolean newRun = true;
		// Bound variables
		int lb = 0;
		int rb = 0;
		// This flag indicates if the current run is ascending or descending.
		boolean asc = true;

		// The main loop for collecting runs.
		for (int i = 0; i < n; ++i) {
			if (newRun) {
				newRun = false;
				lb = i;
				// If only one element remains, we push it to the stack.
				if (i + 1 == n) {
					run_stack.push(new Run(i, i));
					break;
				} else {
					// A run is at least a length of 2, we will decide the order.
					rb = ++i;
					asc = array[i + 1] >= array[i];
				}
			} else {
				// Try to add to the run.
				if ((asc && array[i] >= array[i - 1]) || (!asc && array[i] < array[i - 1])) {
					++rb;
				} else {
					// Reverse the run if it is descending.
					if (!asc) {
						ArrayCore.reverseInt(array, lb, rb);
					}
					// Check if the current run has reaches required length.
					// Insertion sort is used for adding additional elements if not.
					if (rb - lb > min_run) {
						// This element needs to be reused for the new run.
						--i;
					} else {
						// Collect more elements
						rb = Math.min(n - 1, lb + min_run - 1);
						BinaryInsertionSort.sortOnline(array, lb, rb, i);
					}
					// Push the natural or crafted run.
					run_stack.push(new Run(lb, rb));
					newRun = true;
				}
			}

			// Check for end of array and push the last run.
			if (i == n - 1 && !newRun) {
				if (!asc) {
					ArrayCore.reverseInt(array, lb, rb);
				}
				run_stack.push(new Run(lb, rb));
			}

			// Check merging condition if a new run is added.
			if (newRun) {
				mergeCollapse(array, false);
			}
		}

		// Once the main loop ends, all runs have been collected. Force merging will
		// start.
		mergeCollapse(array, true);

		return array;
	}

	/**
	 * Checks the merge condition and merges the top runs in the stack if necessary.
	 * It is recursive until the merge condition is no longer satisfied, or the
	 * stack size is less than 3. Force merging will be used at the end of the
	 * sorting process.
	 * 
	 * @param array
	 * @param force
	 */
	private static void mergeCollapse(int[] array, boolean force) {
		//
	}

	/**
	 * Calculates the min_run based on the array length. The goal is to achieve
	 * balanced merges at the end (especially for random data).
	 * 
	 * @param n the length of the input array
	 * @return the ideal min_run
	 */
	static int calcMinRun(int n) {
		// According to the author, straight insertion sort is hard to beat when n is
		// less than 64.
		if (n < MIN_RUN_THRESHOLD) {
			return n;
		}

		int o = n;
		int sh = 0;
		while (n >= MIN_RUN_THRESHOLD) {
			// Keep doing bit shifts until we get the first 6 bits.
			n >>= 1;
			++sh;
		}

		// If any remaining bits are set, we add 1 extra.
		if (o > (n << sh)) {
			++n;
		}

		return n;
	}

}

/**
 * Simple data structure for storing the runs for Timsort.
 * 
 * @author Eddy Soma
 *
 */
class Run {

	// We store the starting and ending indices in the original array.
	private int lb;
	private int rb;

	public Run(int lb, int rb) {
		this.lb = lb;
		this.rb = rb;
	}

	public int getLb() {
		return lb;
	}

	public int getRb() {
		return rb;
	}

}
