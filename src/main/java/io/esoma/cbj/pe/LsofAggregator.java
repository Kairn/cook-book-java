package io.esoma.cbj.pe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

        try {
            // Figure out the current user with `whoami` command.
            Process whoamiProcess = new ProcessBuilder("whoami").start();
            String curUser = new String(whoamiProcess.getInputStream().readAllBytes(), StandardCharsets.UTF_8).trim();
            Logger.info("Current user is {}.", curUser);

            Process lsofProcess = new ProcessBuilder("lsof", "-u", curUser).start();
            LsofStats lsofStats = new LsofStats();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(lsofProcess.getInputStream()))) {
                Iterator<String> lineIter = br.lines().iterator();
                lineIter.next(); // Skip the header line.
                while (lineIter.hasNext()) {
                    String line = lineIter.next();
                    lsofStats.addRecord(line);
                }
            }

            Logger.info("Printing lsof stats...");
            lsofStats.display();
            Logger.info("Completed lsof aggregator.");
        } catch (Exception e) {
            Logger.error("Failed to execute the process.", e);
        }
    }
}

/** A representation of lsof statistics according to the rules. */
class LsofStats {

    static final Pattern PERM_DENY_PATTERN = Pattern.compile(".*Permission denied.*");
    static final Pattern ROOT_DIR_PATTERN = Pattern.compile("(/.*?)(/.*|$)");
    static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");
    static final int EXPECTED_ATTRIBUTES = 9;

    private int permDeniedCount;
    private int badLineCount;
    private final Map<LsofKey, LsofValue> records = new HashMap<>();

    void addRecord(String lsofLine) {
        // Skips lines with hidden info due to permissions.
        if (PERM_DENY_PATTERN.matcher(lsofLine).matches()) {
            ++permDeniedCount;
            return;
        }

        // Skips lines without full attributes.
        String[] attributes = lsofLine.split("\\s+");
        if (attributes.length != EXPECTED_ATTRIBUTES) {
            ++badLineCount;
            return;
        }

        String command = attributes[0];
        String type = attributes[4];
        String sizeStr = attributes[6];
        if (!NUMERIC_PATTERN.matcher(sizeStr).matches()) {
            ++badLineCount;
            return;
        }
        Matcher pathMatcher = ROOT_DIR_PATTERN.matcher(attributes[8]);
        if (!pathMatcher.matches()) {
            ++badLineCount;
            return;
        }
        String rootDir = pathMatcher.group(1);

        LsofKey lsofKey = new LsofKey(command, type, rootDir);
        records.computeIfAbsent(lsofKey, k -> new LsofValue()).add(Long.parseLong(sizeStr));
    }

    void display() {
        records.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> Logger.info("{} -> {}", entry.getKey(), entry.getValue()));
    }
}

/** The key of a single aggregated record. */
record LsofKey(String command, String type, String rootDir) implements Comparable<LsofKey> {

    @Override
    public int hashCode() {
        return command.hashCode() * 7 + type.hashCode() * 779 + rootDir.hashCode() * 9991;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LsofKey ok) {
            return command.equals(ok.command) && type.equals(ok.type) && rootDir.equals(ok.rootDir);
        }
        return false;
    }

    @Override
    public int compareTo(LsofKey o) {
        if (Objects.equals(command, o.command)) {
            if (Objects.equals(type, o.type)) {
                return rootDir.compareTo(o.rootDir);
            } else {
                return type.compareTo(o.type);
            }
        } else {
            return command.compareTo(o.command);
        }
    }

    @Override
    public String toString() {
        return String.join(":", command, type, rootDir);
    }
}

/** The stats tracked for a single lsof key. */
class LsofValue implements Comparable<LsofValue> {

    private int count;
    private long maxSize;
    private long totalSize;

    void add(long size) {
        this.count++;
        this.maxSize = Math.max(this.maxSize, size);
        this.totalSize += size;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", count, maxSize, totalSize);
    }

    @Override
    public int compareTo(LsofValue o) {
        if (totalSize == o.totalSize) {
            return o.count - count;
        } else {
            return Long.compare(o.totalSize, totalSize);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LsofValue lsofValue = (LsofValue) o;
        return count == lsofValue.count && maxSize == lsofValue.maxSize && totalSize == lsofValue.totalSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, maxSize, totalSize);
    }
}
