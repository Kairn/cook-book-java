package io.esoma.cbj.crypto.core;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility class that contains functions related to basic binary operations.
 * 
 * @author Eddy Soma
 *
 */
public class BinUtil {

	/**
	 * Converts a binary string consists of 0s and 1s into an integer. Negative
	 * numbers are not accounted for. If the bit stream represents a number greater
	 * than {@link Integer#MAX_VALUE}, the result is undefined.
	 * 
	 * @param bits the string containing bits
	 * @return the resulting integer
	 */
	public static int bitStreamToInt(String bits) {
		if (StringUtils.isBlank(bits)) {
			throw new IllegalArgumentException("Invalid bits");
		}

		int result = 0;
		for (int i = 0; i < bits.length(); ++i) {
			char c = bits.charAt(bits.length() - 1 - i);
			if (c == '1') {
				result += 1 << i;
			} else if (c != '0') {
				throw new IllegalArgumentException("Invalid bit found");
			}
		}

		return result;
	}

}
