package com.github.fritaly.projecteuler;

import java.math.BigInteger;

/**
 * Problem "Powerful digit sum".
 *
 * @author francois_ritaly
 */
public class Problem56 {

	public static void main(String[] args) {
		int max = 0;

		for (int a = 1; a < 100; a++) {
			for (int b = 1; b < 100; b++) {
				final BigInteger result = BigInteger.valueOf(a).pow(b);

				int sum = 0;

				for (char c : result.toString().toCharArray()) {
					sum += c - '0';
				}

				max = Math.max(max, sum);

				System.out.println(String.format("%d^%d = %d", a, b, sum));
			}
		}

		System.out.println("Result: " + max);
	}
}