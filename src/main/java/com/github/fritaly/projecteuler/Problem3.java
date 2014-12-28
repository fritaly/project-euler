package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Largest prime factor".
 *
 * @author francois_ritaly
 */
public class Problem3 {

	// Implementation of "Sieve of Eratosthenes"
	private static Set<Long> getPrimeNumbers(long limit) {
		final Set<Long> primeNumbers = new TreeSet<>();
		primeNumbers.add(2L);

		// All the multiple numbers of the prime numbers we identified
		final Set<Long> skip = new TreeSet<>();

		// Only consider the odd numbers
		for (long n = 3; n < limit; n += 2) {
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

	public static void main(String[] args) {
		final long input = 600851475143L;

		// Find all the prime numbers between 3 and Math.sqrt(input)
		final long upperBound = (int) Math.sqrt(input);

		System.out.println(upperBound);

		final Set<Long> primeNumbers = getPrimeNumbers(upperBound);

		System.out.println(String.format("Identified %d prime numbers", primeNumbers.size()));

		// Factor out the input number
		final List<Long> primeFactors = factor(input, primeNumbers);

		System.out.println("Prime factors: " + primeFactors);

		long result = 1;

		for (Long primeFactor : primeFactors) {
			result *= primeFactor;
		}

		System.out.println("Product: " + result);

		System.out.println("Result: " + Collections.max(primeFactors));
	}

	private static List<Long> factor(long number, Set<Long> primeNumbers) {
		final List<Long> primeFactors = new ArrayList<>();

		for (Long primeNumber : primeNumbers) {
			if (number % primeNumber == 0) {
				primeFactors.add(primeNumber);

				number /= primeNumber;

				if (primeNumbers.contains(number)) {
					primeFactors.add(number);
					break;
				}
			}
		}

		return primeFactors;
	}
}