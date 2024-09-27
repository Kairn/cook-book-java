package io.esoma.cbj.pe;

import org.tinylog.Logger;

/**
 * An aggregator that processes the `lsof` command to list open files from the current user and
 * aggregates a few stats based on some rules list as follows:
 *
 * <ol>
 *   <li>Ignore lines without full data.
 *   <li>Ignore lines with `Permission denied` string.
 *   <li>Aggregate by COMMAND, TYPE, and the root dir of NAME, for total and max SIZE, and the
 *       count.
 *   <li>Ignore if SIZE is not a number.
 * </ol>
 */
public class LsofAggregator {

    public static void main(String[] args) {
        Logger.info("LsofAggregator works!");
    }
}
