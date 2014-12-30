package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Distinct powers".
 *
 * @author francois_ritaly
 */
public class Problem29 {
	public static void main(String[] args) throws IOException {
		final Set<BigInteger> set = new TreeSet<>();

		for (int a = 2; a <= 100; a++) {
			final BigInteger bigA = BigInteger.valueOf(a);
			BigInteger c = bigA;

			for (int b = 2; b <= 100; b++) {
				c = c.multiply(bigA);

				set.add(c);
			}
		}

		System.out.println("Result: " + set.size());
	}
}