package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem "Prime permutations".
 *
 * @author francois_ritaly
 */
public class Problem49 {

	private static boolean isPermutation(long a, long b) {
		return Utils.sort(Long.toString(a)).equals(Utils.sort(Long.toString(b)));
	}

	public static void main(String[] args) {
		final List<Long> primes = new ArrayList<>();

		for (Long prime : Utils.getPrimeNumbers(10000)) {
			if (prime >= 1000) {
				primes.add(prime);
			}
		}

		System.out.println("Primes: " + primes);

		for (int i = 0; i < primes.size() - 1; i++) {
			// We're only interested in the sequence whose step is 3330 (as per
			// the problem statement)
			final long a = primes.get(i);
			final long b = a + 3330;
			final long c = b + 3330;

			if (primes.contains(b) && primes.contains(c) && isPermutation(a, b) && isPermutation(b, c)) {
				System.out.println(String.format("%d -> %d -> %d", a, b, c));
				System.out.println(String.format("%d%d%d", a, b, c));
			}
		}
	}
}