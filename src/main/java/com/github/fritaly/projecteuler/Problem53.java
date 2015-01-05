package com.github.fritaly.projecteuler;

import static com.github.fritaly.projecteuler.Utils.factorial;

import java.math.BigInteger;

/**
 * Problem "Combinatoric selections".
 *
 * @author francois_ritaly
 */
public class Problem53 {

	public static void main(String[] args) {
		final BigInteger oneMillion = BigInteger.valueOf(1000 * 1000);

		int count = 0;

		for (int r = 0; r <= 100; r++) {
			final BigInteger f1 = factorial(r);

			for (int n = r; n <= 100; n++) {
				final BigInteger c = factorial(n).divide(f1.multiply(factorial(n - r)));

				if (c.compareTo(oneMillion) > 0) {
					count += (100 - n + 1);
					break;
				}
			}
		}

		System.out.println("Result: " + count);
	}
}