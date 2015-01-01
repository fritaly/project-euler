package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Problem "Champernowne's constant".
 *
 * @author francois_ritaly
 */
public class Problem40 {

	public static void main(String[] args) throws IOException {
		long result = 1;

		int numberOfDigits = 0;
		int sequence = 1;

		final LinkedList<Integer> indices = new LinkedList<>(Arrays.asList(1, 10, 100, 1000, 10000, 100000, 1000000));

		while (true) {
			final String text = Integer.toString(sequence);

			while (numberOfDigits + text.length() >= indices.getFirst()) {
				final int index = indices.removeFirst();

				// System.out.println(String.format("# of digits = %d / text = %s / index = %d", numberOfDigits, text, index));
				// System.out.println(text.charAt(index - numberOfDigits - 1));

				result *= (text.charAt(index - numberOfDigits - 1) - '0');

				if (indices.isEmpty()) {
					break;
				}
			}

			if (indices.isEmpty()) {
				break;
			}

			numberOfDigits += text.length();
			sequence++;
		}

		System.out.println("Result: " + result);
	}
}