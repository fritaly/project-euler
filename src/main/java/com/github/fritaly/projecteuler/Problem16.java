package com.github.fritaly.projecteuler;

import java.math.BigInteger;

/**
 * Problem "Power digit sum".
 *
 * @author francois_ritaly
 */
public class Problem16 {

	public static void main(String[] args) {
		final BigInteger two = BigInteger.valueOf(2);

		BigInteger result = BigInteger.ONE;

		for (int i = 0; i < 1000; i++) {
			result = result.multiply(two);
		}

		final String text = result.toString();

		int sum = 0;

		for (int i = 0; i < text.length(); i++) {
			sum += text.charAt(i) - '0';
		}

		System.out.println("Result: " + sum);
	}
}