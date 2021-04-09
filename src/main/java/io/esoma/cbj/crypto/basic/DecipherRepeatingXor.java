package io.esoma.cbj.crypto.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Class for studying how to decrypt a cipher encrypted by a repeating XOR
 * algorithm.
 * 
 * @author Eddy Soma
 *
 */
public class DecipherRepeatingXor {

	public static void main(String[] args) {
		String input = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String inputFileName = "DecipherRepeatingXorText.txt";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(loader.getResourceAsStream(inputFileName)))) {
			input = br.lines().collect(Collectors.joining());
			System.out.println(input);
		} catch (Exception e) {
			throw new IllegalStateException("Unable to read file", e);
		}
	}

}
