package com.github.fritaly.projecteuler;

/**
 * Problem "Largest palindrome product".
 *
 * @author francois_ritaly
 */
public class Problem4 {

	private static boolean isPalindrome(int n) {
		final String text = String.format("%d", n);

		boolean palindrome = true;

		for (int i = 0; i < text.length() / 2; i++) {
			if (text.charAt(i) != text.charAt(text.length() - 1 - i)) {
				palindrome = false;
				break;
			}
		}

		return palindrome;
	}

	public static void main(String[] args) {
		int result = 0;

		for (int a = 999; a >= 100; a--) {
			for (int b = 999; b >= 100; b--) {
				final int c = a * b;

				if (isPalindrome(c)) {
					// System.out.println(String.format("%d x %d = %d", a, b, c));

					result = Math.max(result, c);
				}
			}
		}

		System.out.println("Result: " + result);
	}
}