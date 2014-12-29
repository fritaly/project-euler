package com.github.fritaly.projecteuler;

import java.math.BigInteger;

/**
 * Problem "Factorial digit sum".
 *
 * @author francois_ritaly
 */
public class Problem20 {

	private static BigInteger factorial(int n) {
		if (n == 0) {
			return BigInteger.ONE;
		}

		return BigInteger.valueOf(n).multiply(factorial(n - 1));
	}

	public static void main(String[] args) {
		final String text = factorial(100).toString();

		int result = 0;

		for (int i = 0; i < text.length(); i++) {
			result += text.charAt(i) - '0';
		}

		System.out.println("Result: " + result);
	}
}