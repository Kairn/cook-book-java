package io.esoma.cbj.algo;

import java.util.Stack;

import io.esoma.cbj.core.ArrayCore;
import io.esoma.cbj.core.BinaryInsertionSort;
import io.esoma.cbj.core.BinarySearch;

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
	private static final boolean ENABLE_GALLOP = true;

	// This keeps track of the ideal galloping threshold which adapts appropriately
	// based on the data for each sorting case.
	private static int min_gallop = MIN_GALLOP_INIT;
	// This controls the merge mode.
	private static boolean gallop_mode = false;
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
				if (i == n - 1) {
					run_stack.push(new Run(i, i));
					break;
				} else {
					// A run has at least a length of 2, we will decide the order.
					asc = array[i + 1] >= array[i];
					rb = ++i;
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
					if (rb - lb + 1 >= min_run) {
						// This element needs to be reused for the new run.
						--i;
					} else {
						// Collect more elements
						rb = Math.min(n - 1, lb + min_run - 1);
						BinaryInsertionSort.sortOnline(array, lb, rb, i);
						i = rb;
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
		if (run_stack.size() < 2) {
			return;
		}

		Run lRun = null;
		Run rRun = null;

		if (run_stack.size() == 2 && force) {
			rRun = run_stack.pop();
			lRun = run_stack.pop();
		} else if (run_stack.size() > 2) {
			// Load the top three runs and check the merge condition.
			Run c = run_stack.pop();
			Run b = run_stack.pop();
			Run a = run_stack.pop();
			// Two invariants specified by the author.
			if ((b.getLen() > c.getLen() && a.getLen() > (b.getLen() + c.getLen())) && !force) {
				// Return the runs to the stack.
				run_stack.push(a);
				run_stack.push(b);
				run_stack.push(c);
				return;
			}

			// Merge is needed.
			if (c.getLen() > a.getLen()) {
				// Merge a and b.
				lRun = a;
				rRun = b;
				run_stack.push(new Run(a.getLb(), b.getRb()));
				run_stack.push(c);
			} else {
				// Merge b and c.
				lRun = b;
				rRun = c;
				run_stack.push(a);
				run_stack.push(new Run(b.getLb(), c.getRb()));
			}
		} else {
			// Only 2 runs in the stack while in the process.
			return;
		}

		// Further shrink the merging size by searching for elements already suited in
		// the right place on both runs.
		int lBin = lRun.getLb();
		int lEnd = lRun.getRb();
		int rBin = rRun.getLb();
		int rEnd = rRun.getRb();

		// New bounds after binary search.
		int nlb = BinarySearch.searchIntRight(array, array[rBin], lBin, lEnd);
		int nrb = BinarySearch.searchIntLeft(array, array[lEnd], rBin, rEnd) - 1;
		int lSize = lEnd - nlb + 1;
		int rSize = nrb - rBin + 1;

		// Decide the merge direction. Favor left.
		if (lSize > 0 && rSize >= lSize) {
			// Merge starts from the lower end.
			mergeLo(array, nlb, nrb, lEnd);
		} else if (rSize > 0 && lSize > rSize) {
			// Merge starts from the higher end.
			mergeHi(array, nlb, nrb, rBin);
		}

		// Recursively call until no longer needed.
		mergeCollapse(array, force);
	}

	/**
	 * Merges two runs starting at the left bound. Assumes the left side is shorter.
	 * Automatically puts the left run into temporary memory and starts pairing
	 * mode.
	 * 
	 * @param array the source array
	 * @param bin   the beginning index
	 * @param end   the ending index
	 * @param mid   the ending index of the left run
	 */
	private static void mergeLo(int[] array, int bin, int end, int mid) {
		// Copy the left run into temporary memory.
		int[] tempL = ArrayCore.copyInt(array, bin, mid);
		// Index pointers.
		int lp = 0;
		int rp = mid + 1;

		// Keep track of the win streak. Positive is right, and negative is left.
		int streak = 0;
		// The run from which we select the first element to search.
		boolean leftGallop = true;
		// If galloping is not paying off, will shutdown on the next bad result.
		boolean badGallop = false;

		for (int i = bin; i <= end; ++i) {
			if (lp >= tempL.length) {
				array[i] = array[rp];
				++rp;
			} else if (rp > end) {
				array[i] = tempL[lp];
				++lp;
			} else {
				int le = tempL[lp];
				int re = array[rp];
				if (!gallop_mode) {
					// One pair at a time mode (pairing).
					if (le <= re) {
						// Left run wins.
						streak = Math.min(-1, streak - 1);
						// Need to maintain stability.
						array[i] = le;
						++lp;
					} else {
						streak = Math.max(1, streak + 1);
						array[i] = re;
						++rp;
					}
					// Check if we need to switch mode.
					if (Math.abs(streak) >= min_gallop) {
						gallop_mode = ENABLE_GALLOP;
						streak = 0;
					}
				} else {
					// Galloping mode.
					// Keep track of the galloping payoff.
					int gp = 0;
					if (leftGallop) {
						// Left search.
						int rlp = BinarySearch.searchIntLeft(array, le, rp, end);
						gp = rlp - rp;
						// Load the right chunk.
						while (rp < rlp) {
							array[i] = array[rp];
							++i;
							++rp;
						}
						// Move the left run element.
						array[i] = le;
						++lp;
					} else {
						// Right search.
						int lrp = BinarySearch.searchIntRight(tempL, re, lp, tempL.length - 1);
						gp = lrp - lp;
						while (lp < lrp) {
							array[i] = tempL[lp];
							++i;
							++lp;
						}
						// Move the right run element.
						array[i] = re;
						++rp;
					}
					// Evaluate galloping.
					leftGallop = !leftGallop;
					if (gp >= min_gallop) {
						// Galloping is paying off.
						--min_gallop;
					} else {
						if (badGallop) {
							// Switch back to pairing mode.
							gallop_mode = false;
							badGallop = false;
							++min_gallop;
						} else {
							badGallop = true;
							++min_gallop;
						}
					}
				}
			}
		}
	}

	/**
	 * Merges two runs starting at the right bound. Assumes the right side is
	 * shorter. Automatically puts the right run into temporary memory and starts
	 * pairing mode.
	 * 
	 * @param array the source array
	 * @param bin   the beginning index
	 * @param end   the ending index
	 * @param mid   the ending index of the right run
	 */
	private static void mergeHi(int[] array, int bin, int end, int mid) {
		// Copy the left run into temporary memory.
		int[] tempR = ArrayCore.copyInt(array, mid, end);

		int rp = tempR.length - 1;
		int lp = mid - 1;

		int streak = 0;
		boolean rightGallop = true;
		boolean badGallop = false;

		for (int i = end; i >= bin; --i) {
			if (rp < 0) {
				array[i] = array[lp];
				--lp;
			} else if (lp < bin) {
				array[i] = tempR[rp];
				--rp;
			} else {
				int re = tempR[rp];
				int le = array[lp];
				if (!gallop_mode) {
					// Pairing mode.
					if (re >= le) {
						streak = Math.max(1, streak + 1);
						// Again, stability.
						array[i] = re;
						--rp;
					} else {
						streak = Math.min(-1, streak - 1);
						array[i] = le;
						--lp;
					}
					// Check if we need to switch mode.
					if (Math.abs(streak) >= min_gallop) {
						gallop_mode = ENABLE_GALLOP;
						streak = 0;
					}
				} else {
					// Galloping mode.
					int gp = 0;
					if (rightGallop) {
						// Right search.
						int lrp = BinarySearch.searchIntRight(array, re, bin, lp) - 1;
						gp = lp - lrp;
						while (lp > lrp) {
							array[i] = array[lp];
							--i;
							--lp;
						}
						array[i] = re;
						--rp;
					} else {
						// Left search.
						int rlp = BinarySearch.searchIntLeft(tempR, le, 0, rp) - 1;
						gp = rp - rlp;
						while (rp > rlp) {
							array[i] = tempR[rp];
							--i;
							--rp;
						}
						array[i] = le;
						--lp;
					}
					// Evaluate galloping.
					rightGallop = !rightGallop;
					if (gp >= min_gallop) {
						// Galloping is paying off.
						--min_gallop;
					} else {
						if (badGallop) {
							// Switch back to pairing mode.
							gallop_mode = false;
							badGallop = false;
							++min_gallop;
						} else {
							badGallop = true;
							++min_gallop;
						}
					}
				}
			}
		}
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

	public int getLen() {
		return this.rb - this.lb + 1;
	}

}
