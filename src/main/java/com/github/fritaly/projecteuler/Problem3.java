package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Problem "Largest prime factor".
 *
 * @author francois_ritaly
 */
public class Problem3 {

	public static void main(String[] args) {
		final long input = 600851475143L;

		// Find all the prime numbers between 3 and Math.sqrt(input)
		final long upperBound = (int) Math.sqrt(input);

		System.out.println(upperBound);

		final Set<Long> primeNumbers = Utils.getPrimeNumbers(upperBound);

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