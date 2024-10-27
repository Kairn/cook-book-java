package io.esoma.cbj.pe;

import io.esoma.cbj.util.ResourceLoader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import org.tinylog.Logger;

/**
 * This stream scanner reads from a continuous data stream and looks for chunks that match a
 * particular pattern and record their starting and ending indices. Pattern can contain alphabet
 * characters or "*" (matches all characters). Matching should be done in a case insensitive way.
 * New-line characters are ignored when encountered.
 */
public class StreamScanner {

    static final String PATTERN = "Glo**";

    public static void main(String[] args) {
        int pn = PATTERN.length();
        try (BufferedReader br =
                new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsReader("example/scanText.txt")))) {
            int ln = 0;
            Iterator<String> lines = br.lines().iterator();
            while (lines.hasNext()) {
                ++ln;
                String line = lines.next();
                int cn = 0;

                STR_LOOP:
                for (; cn < line.length() - (pn - 1); ++cn) {
                    for (int k = 0; k < pn; ++k) {
                        if (!charMatch(PATTERN.charAt(k), line.charAt(cn + k))) {
                            continue STR_LOOP;
                        }
                    }
                    Logger.info("Found match at line {} and column {} --- {}.", ln, cn, line.substring(cn, cn + pn));
                }
            }
        } catch (Exception e) {
            Logger.error("Unable to read scan text.");
            Logger.error(e);
        }
    }

    /** Checks if the text character matches the pattern character. */
    private static boolean charMatch(char pc, char tc) {
        if (pc == '*') {
            return true;
        } else if (pc == tc) {
            return true;
        } else {
            return (pc >= 'a' && pc <= 'z') ? pc - tc == 32 : tc - pc == 32;
        }
    }
}
