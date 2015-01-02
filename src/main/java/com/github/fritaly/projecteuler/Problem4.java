package com.github.fritaly.projecteuler;

/**
 * Problem "Largest palindrome product".
 *
 * @author francois_ritaly
 */
public class Problem4 {

	public static void main(String[] args) {
		int result = 0;

		for (int a = 999; a >= 100; a--) {
			for (int b = 999; b >= 100; b--) {
				final int c = a * b;

				if (Utils.isPalindrome(c)) {
					// System.out.println(String.format("%d x %d = %d", a, b, c));

					result = Math.max(result, c);
				}
			}
		}

		System.out.println("Result: " + result);
	}
}