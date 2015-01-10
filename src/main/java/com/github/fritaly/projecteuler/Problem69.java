package com.github.fritaly.projecteuler;

import java.util.Set;

/**
 * Problem "Totient maximum".
 *
 * @author francois_ritaly
 */
public class Problem69 {

	public static void main(String[] args) {
		final int limit = 1000 * 1000;

		final Set<Long> primes = Utils.getPrimeNumbers(limit);

		long result = 1;

		for (Long prime : primes) {
			if (result * prime > limit) {
				break;
			}

			result *= prime;
		}

		System.out.println("Result: " + result);
	}
}