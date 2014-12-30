package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem "Reciprocal cycles".
 *
 * @author francois_ritaly
 */
public class Problem26 {

	private static int findPattern(int denominator) {
		final List<String> steps = new ArrayList<>();

		int remainder = 1 % denominator;

		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			if (remainder < denominator) {
				remainder = remainder * 10;
			}

			final int ratio = remainder / denominator;

			remainder = remainder % denominator;

			final String step = String.format("%d:%d", ratio, remainder);

			if (steps.contains(step)) {
				return steps.size() - steps.indexOf(step);
			}

			steps.add(step);
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		int longestPattern = 0;
		int result = -1;

		for (int d = 1; d < 1000; d++) {
			final int patternLength = findPattern(d);

			System.out.println(String.format("1/%d -> Pattern length: %d", d, patternLength));

			if (patternLength > longestPattern) {
				longestPattern = patternLength;

				result = d;
			}
		}

		System.out.println("Result: " + result);
	}
}