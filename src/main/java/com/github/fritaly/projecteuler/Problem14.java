package com.github.fritaly.projecteuler;

/**
 * Problem "Longest Collatz sequence".
 *
 * @author francois_ritaly
 */
public class Problem14 {

	private static long next(long n) {
		return (n % 2 == 0) ? (n / 2) : (n * 3 + 1);
	}

	public static void main(String[] args) {
		long result = 0;
		int maxCount = 0;

		for (long number = 1; number < 1000000; number++) {
			int count = 1;

			long current = number;

			while (current != 1) {
				current = next(current);

				count++;
			}

			// System.out.println("Processed " + number + " (" + count + ")");

			if (count > maxCount) {
				maxCount = count;
				result = number;
			}
		}

		System.out.println("Result: " + result);
	}
}