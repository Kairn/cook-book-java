package io.esoma.cbj.util;

/**
 * Utility class for creating and managing banner strings. Banner strings are used for marking the
 * start and end of standard output content. Banner strings make it easier for developers/testers to
 * inspect the results of outputs.
 *
 * @author Eddy Soma
 */
public class Banner {

    private static final char SPACE = ' ';
    private static final char EQUAL = '=';
    private static final char DASH = '-';
    private static final char STAR = '*';

    private Banner() {}

    /**
     * Builds a banner string of a title wrapped by a number of specified padding signs. Used
     * internally for building more specific banner strings.
     *
     * @param title the title
     * @param num the number of padding signs
     * @return the generated banner string
     */
    private static String getCommonBanner(String title, int num, char sign) {
        // Padding amount cannot be lower than 1 or exceeding 50.
        // Default is 3.
        if (num < 1 || num > 50) {
            num = 3;
        }

        // Set default title if null or empty string is passed.
        if (title == null || title.isEmpty()) {
            title = "Untitled";
        }

        return String.valueOf(sign).repeat(num)
                + SPACE
                + title
                + SPACE
                + String.valueOf(sign).repeat(num);
    }

    /**
     * Returns a banner string padded with Equal signs.
     *
     * @param title the title
     * @param num the number of padding signs
     * @return the generated banner string
     */
    public static String getTitleBanner(String title, int num) {
        return getCommonBanner(title, num, EQUAL);
    }

    /**
     * Returns a banner string padded with Dash signs.
     *
     * @param title the title
     * @param num the number of padding signs
     * @return the generated banner string
     */
    public static String getSubBanner(String title, int num) {
        return getCommonBanner(title, num, DASH);
    }

    /**
     * Returns a banner string padded with Star signs.
     *
     * @param title the title
     * @param num the number of padding signs
     * @return the generated banner string
     */
    public static String getSpecialBanner(String title, int num) {
        return getCommonBanner(title, num, STAR);
    }
}
