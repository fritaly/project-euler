package com.github.fritaly.projecteuler;

import java.io.IOException;

/**
 * Problem "Double-base palindromes".
 *
 * @author francois_ritaly
 */
public class Problem36 {

	private static boolean isPalindrome(String text) {
		return text.equals(Utils.reverse(text));
	}

	public static void main(String[] args) throws IOException {
		long sum = 0;

		for (int number = 1; number < 1000000; number++) {
			final String text10 = Integer.toString(number);

			if (text10.endsWith("0")) {
				// The number can't start and end with a zero
				continue;
			}

			final String text2 = Integer.toBinaryString(number);

			if (isPalindrome(text10) && isPalindrome(text2)) {
				System.out.println(number + " is a palindrome");

				sum += number;
			}
		}

		System.out.println("Result: " + sum);
	}
}