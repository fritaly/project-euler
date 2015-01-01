package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.util.Set;

/**
 * Problem "Circular primes".
 *
 * @author francois_ritaly
 */
public class Problem35 {

	private static boolean isCircular(long number, Set<Long> primes) {
		for (String rotation : Utils.getRotations(Long.toString(number))) {
			if (!primes.contains(Long.parseLong(rotation))) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		int count = 0;

		final Set<Long> primes = Utils.getPrimeNumbers(1000000);

		for (Long prime : primes) {
			if (isCircular(prime, primes)) {
				// System.out.println(prime + " is a circular prime");
				count++;
			}
		}

		System.out.println("Result: " + count);
	}
}