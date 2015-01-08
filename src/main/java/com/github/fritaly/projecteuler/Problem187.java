package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem "Semiprimes".
 *
 * @author francois_ritaly
 */
public class Problem187 {

	public static void main(String[] args) {
		final int limit = 100 * 1000 * 1000;

		int total = 0;

		final List<Long> primes = new ArrayList<>(Utils.getPrimeNumbers(limit));

		// Iterator over all prime numbers
		for (int i = 0; i < primes.size(); i++) {
			final long prime1 = primes.get(i);

			// The number of composite numbers created with this prime number
			int count = 0;

			for (int j = i; j < primes.size(); j++) {
				final long prime2 = primes.get(j);

				if (prime1 * prime2 > limit) {
					// The limit was reached, stop the search
					break;
				}

				// System.out.println(String.format("%d x %d = %d", prime1, prime2, prime1 * prime2));

				count++;
			}

			if (count == 0) {
				// We've found all the possible composites made of 2 primes, stop the search
				break;
			}

			total += count;
		}

		System.out.println("Result: " + total);
	}
}