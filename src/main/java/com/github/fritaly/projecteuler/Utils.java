package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Utils {

	// Implementation of "Sieve of Eratosthenes"
	public static Set<Long> getPrimeNumbers(long limit) {
		final Set<Long> primeNumbers = new TreeSet<>();
		primeNumbers.add(2L);

		// All the multiple numbers of the prime numbers we identified
		final Set<Long> skip = new TreeSet<>();

		// Only consider the odd numbers
		for (long n = 3; n <= limit; n += 2) {
			if (skip.contains(n)) {
				continue;
			}

			boolean prime = true;

			for (Long primeNumber : primeNumbers) {
				if (n % primeNumber == 0) {
					prime = false;
					break;
				}
			}

			if (prime) {
				// System.out.println(n + " is prime");

				primeNumbers.add(n);

				// Skip all the numbers which are multiple of this prime number
				for (long i = n + n; i < limit; i += n) {
					skip.add(i);
				}
			}
		}

		return primeNumbers;
	}

	public static List<Long> factor(long number) {
		final Set<Long> primeNumbers = getPrimeNumbers(number);

		final List<Long> primeFactors = new ArrayList<>();

		for (Long primeNumber : primeNumbers) {
			while (number % primeNumber == 0) {
				primeFactors.add(primeNumber);

				number /= primeNumber;
			}

			if (primeNumbers.contains(number)) {
				primeFactors.add(number);
				break;
			}
		}

		return primeFactors;
	}
}