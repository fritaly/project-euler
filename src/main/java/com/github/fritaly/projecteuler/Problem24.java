package com.github.fritaly.projecteuler;

/**
 * Problem "Lexicographic permutations".
 *
 * @author francois_ritaly
 */
public class Problem24 {

	private static int factorial(int n) {
		return (n == 0) ? 1 : n * factorial(n - 1);
	}

	private static String shift(String digits) {
		return digits.substring(1) + digits.charAt(0);
	}

	public static void main(String[] args) {
		String digits = "0123456789";

		int remainder = 1000 * 1000;

		final StringBuilder builder = new StringBuilder();

		while (true) {
			// # of permutations possible by using n-1 digits ?
			final int permutations = factorial(digits.length() - 1);

			if (remainder > permutations) {
				remainder -= permutations;

				digits = shift(digits);
			} else {
				builder.append(digits.charAt(0));

				// Sort the digits again
				digits = Utils.sort(digits.substring(1));

				if ("".equals(digits)) {
					break;
				}
			}
		}

		System.out.println("Result: " + builder.toString());
	}
}