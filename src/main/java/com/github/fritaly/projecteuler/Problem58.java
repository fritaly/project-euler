package com.github.fritaly.projecteuler;

import java.util.Set;

/**
 * Problem "Spiral primes". This program requires lots of RAM to run.
 *
 * @author francois_ritaly
 */
public class Problem58 {

	private static String format(long number, Set<Long> primes) {
		return primes.contains(number) ? String.format("(%d)", number) : Long.toString(number);
	}

	public static void main(String[] args) {
		final int limit = 1000 * 1000 * 1000;

		// Compute all the primes until the given limit
		final Set<Long> primes = Utils.getPrimeNumbers(limit);

		long currentNumber = 1;

		int numberOfPrimes = 0;

		// a NxN grid contains ((size - 1) / 2) concentric layers of numbers
		for (int n = 1;; n++) {
			// The difference between consecutive corner numbers for a layer is
			// 2n
			final int delta = 2 * n;

			// The length of the spiral's side
			final int side = (2 * n) + 1;

			// n1 = top right, n2 = top left, n3 = bottom left, n4 = bottom
			// right
			final long n1 = currentNumber + delta;
			final long n2 = n1 + delta;
			final long n3 = n2 + delta;
			final long n4 = n3 + delta;

			if (n4 > limit) {
				throw new IllegalStateException("Limit reached");
			}

			if (primes.contains(n1)) {
				numberOfPrimes++;
			}
			if (primes.contains(n2)) {
				numberOfPrimes++;
			}
			if (primes.contains(n3)) {
				numberOfPrimes++;
			}
			if (primes.contains(n4)) {
				numberOfPrimes++;
			}

			final int numberOfDiagonalNumbers = (4 * n) + 1;

			System.out.println(String.format("#%d: %s %s %s %s -> %d/%d", n, format(n1, primes), format(n2, primes),
					format(n3, primes), format(n4, primes), numberOfPrimes, numberOfDiagonalNumbers));

			if (numberOfPrimes * 10 < numberOfDiagonalNumbers) {
				System.out.println("Result: " + side);
				break;
			}

			currentNumber = n4;
		}
	}
}