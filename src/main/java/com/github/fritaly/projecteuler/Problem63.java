package com.github.fritaly.projecteuler;

import java.math.BigInteger;

/**
 * Problem "Powerful digit counts".
 *
 * @author francois_ritaly
 */
public class Problem63 {

	public static void main(String[] args) {
		int count = 0;

		for (int i = 1; i < 100; i++) {
			for (int n = 1; n < 100; n++) {
				final BigInteger result = BigInteger.valueOf(i).pow(n);

				final int length = result.toString().length();

				if (length == n) {
					System.out.println(String.format("%d ^ %d = %d", i, n, result));
					count++;
				}
			}
		}

		System.out.println("Result: " + count);
	}
}