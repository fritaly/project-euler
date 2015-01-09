package com.github.fritaly.projecteuler;

import java.math.BigInteger;

/**
 * Problem "Large non-Mersenne prime".
 *
 * @author francois_ritaly
 */
public class Problem97 {

	public static void main(String[] args) {
		final BigInteger two = BigInteger.valueOf(2);
		final BigInteger cap = BigInteger.TEN.pow(12);

		BigInteger value = BigInteger.ONE;

		for (int i = 1; i <= 7830457; i++) {
			value = value.multiply(two).mod(cap);
		}

		value = value.multiply(BigInteger.valueOf(28433)).add(BigInteger.ONE);

		final String text = value.toString();

		System.out.println("Result: " + text.substring(text.length() - 10));
	}
}