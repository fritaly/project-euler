package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Truncatable primes".
 *
 * @author francois_ritaly
 */
public class Problem37 {

	private static boolean isTruncatable(long number, Set<Long> primes) {
		if (number < 10) {
			return false;
		}

		final String text = Long.toString(number);

		final Set<Long> truncated = new TreeSet<>();

		for (int i = 1; i < text.length(); i++) {
			truncated.add(Long.parseLong(text.substring(i)));
			truncated.add(Long.parseLong(text.substring(0, text.length() - i)));
		}

		for (Long n : truncated) {
			if (!primes.contains(n)) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		long sum = 0;
		int count = 0;

		final Set<Long> primes = Utils.getPrimeNumbers(1000000);

		for (Long prime : primes) {
			if (isTruncatable(prime, primes)) {
				System.out.println(prime + " is a truncatable prime");

				sum += prime;

				if (++count == 11) {
					System.out.println("Result: " + sum);
					return;
				}
			}
		}

		throw new RuntimeException("Unable to find 11 truncatable prime numbers");
	}
}