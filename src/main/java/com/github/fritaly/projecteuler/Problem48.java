package com.github.fritaly.projecteuler;

import java.math.BigInteger;

/**
 * Problem "Self powers".
 *
 * @author francois_ritaly
 */
public class Problem48 {

	public static void main(String[] args) {
		BigInteger result = BigInteger.ZERO;

		for (int i = 1; i <= 1000; i++) {
			result = result.add(BigInteger.valueOf(i).pow(i));
		}

		final String text = result.toString();

		System.out.println(text.substring(text.length() - 10));
	}
}