package io.esoma.cbj.algo;

/**
 * Class for implementing a 3-way string (quick) sort. The idea is to do a 3-way partition on the
 * leading character of the keys, dividing all strings into a "greater than", a "less than", and an
 * "equal to" part. For the "equal to" part, advance to the next character and repeat the process
 * until all recursions have ended. This is particularly efficient for sorting strings with lots of
 * common prefixes by eliminating duplicated work in examining the same characters over and over.
 */
public class ThreeWayStringSort {

    private ThreeWayStringSort() {}

    /**
     * Sorts the input strings based on the "natural ordering" of strings in Java. Strings are
     * partitioned by characters from left to right in succession. The sort is performed in place and
     * the input array will be modified.
     *
     * @param array the array to sort
     * @return the same input array after in-place sort
     */
    public static String[] sort(String[] array) {
        if (array == null || array.length < 1) {
            return array;
        }

        doSort(array, 0, array.length, 0);
        return array;
    }

    /**
     * Partitions the subarray based on the specified range at the specified character position. The
     * specified subarray will be rearranged into three parts, and this is recursively called on each
     * part. Only the call on the "equal" partition will advance the character position.
     *
     * @param array the source array
     * @param start the first index
     * @param end the last index (exclusive)
     * @param pos the character position to check
     */
    private static void doSort(String[] array, int start, int end, int pos) {
        if (end - start < 2) {
            // One element is already sorted.
            return;
        }

        Character pivot = choosePivot(array, start, end, pos);
        if (pivot == null) {
            // No more character to examine. All strings are equal in this subarray.
            return;
        }

        // Count the number of elements in each partition.
        int ltCount = 0;
        int eqCount = 0;
        // gtCount is not needed and can be inferred.
        for (int i = start; i < end; ++i) {
            String str = array[i];
            if (pos >= str.length()) {
                // End of string is reached.
                ++ltCount;
            } else {
                char c = str.charAt(pos);
                if (c < pivot) {
                    ++ltCount;
                } else if (c == pivot) {
                    ++eqCount;
                }
            }
        }

        int cursor = start;
        // Keep track of the next insertion point of each partition.
        int ltCursor = start;
        int eqCursor = start + ltCount;
        int gtCursor = eqCursor + eqCount;

        while (cursor < start + ltCount + eqCount) {
            String str = array[cursor];
            if (str.length() > pos) {
                char c = str.charAt(pos);
                if (c == pivot) {
                    if (cursor >= start + ltCount) {
                        // Passed "less than" partition.
                        ++cursor;
                        continue;
                    }
                    // Place the string to the "equal" partition.
                    swap(array, cursor, eqCursor++);
                    continue;
                } else if (c > pivot) {
                    swap(array, cursor, gtCursor++);
                    continue;
                }
            }

            // The current string should be in the "less than" partition.
            if (cursor >= start + ltCount) {
                // Passed "less than" partition.
                swap(array, cursor, ltCursor++);
                continue;
            }
            ++ltCursor;
            ++cursor;
        }

        // Recursively sort each partition.
        doSort(array, start, start + ltCount, pos);
        doSort(array, start + ltCount, start + ltCount + eqCount, pos + 1);
        doSort(array, start + ltCount + eqCount, end, pos);
    }

    /**
     * Selects a pivot character for the given subarray. This is the first operation in the partition
     * step. Selection is based on the first string that has a character in the specified position.
     *
     * @param array the source array
     * @param start the first index
     * @param end the last index (exclusive)
     * @param pos the character position to check
     * @return the chosen pivot character, or null if no string has more characters
     */
    private static Character choosePivot(String[] array, int start, int end, int pos) {
        for (int i = start; i < end; ++i) {
            String str = array[i];
            if (pos >= str.length()) {
                continue;
            }
            return str.charAt(pos);
        }

        return null;
    }

    private static void swap(String[] array, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        String temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
