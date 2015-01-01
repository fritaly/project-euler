package com.github.fritaly.projecteuler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Utils {

	public static List<String> getRotations(String text) {
		if ("".equals(text)) {
			return Collections.emptyList();
		}
		if (text.length() == 1) {
			return Collections.singletonList(text);
		}

		final List<String> rotations = new ArrayList<>();

		for (int i = 0; i < text.length(); i++) {
			rotations.add(text.substring(i) + text.substring(0, i));
		}

		return rotations;
	}

	public static Set<String> getPermutations(String text) {
		if ("".equals(text)) {
			return Collections.emptySet();
		}
		if (text.length() == 1) {
			return Collections.singleton(text);
		}

		final List<String> permutations = new ArrayList<>();

		for (int i = 0; i < text.length(); i++) {
			final char c = text.charAt(i);

			for (String string : getPermutations(text.substring(0, i) + text.substring(i + 1))) {
				permutations.add(c + string);
			}
		}

		return new TreeSet<>(permutations);
	}

	public static boolean isPrimeNumber(int n) {
		final boolean[] array = new boolean[n + 1];

		Arrays.fill(array, true);

		for (int i = 2; i <= (int) Math.sqrt(n); i++) {
			if (array[i]) {
				for (int j = i * i; j <= n; j += i) {
					array[j] = false;
				}
			}
		}

		return array[n];
	}

	// Implementation of "Sieve of Eratosthenes"
	public static Set<Long> getPrimeNumbers(int limit) {
		final boolean[] array = new boolean[limit + 1];

		Arrays.fill(array, true);

		for (int i = 2; i <= (int) Math.sqrt(limit); i++) {
			if (array[i]) {
				for (int j = i * i; j <= limit; j += i) {
					array[j] = false;
				}
			}
		}

		final Set<Long> primeNumbers = new TreeSet<>();

		for (int i = 2; i < array.length; i++) {
			if (array[i]) {
				primeNumbers.add(new Long(i));
			}
		}

		return primeNumbers;
	}

	public static Long getNthPrimeNumber(long index) {
		final Set<Long> primeNumbers = new TreeSet<>();
		primeNumbers.add(2L);

		// Only consider the odd numbers
		for (long n = 3; n <= Long.MAX_VALUE; n += 2) {
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

				if (primeNumbers.size() == index) {
					return n;
				}
			}
		}

		throw new UnsupportedOperationException();
	}

	public static List<Long> factor(long number) {
		final Set<Long> primeNumbers = getPrimeNumbers((int) number);

		final List<Long> primeFactors = new ArrayList<>();

		for (Long primeNumber : primeNumbers) {
			while (number % primeNumber == 0) {
				primeFactors.add(primeNumber);

				number /= primeNumber;
			}

			if (primeNumbers.contains(number)) {
				primeFactors.add(new Long(number));
				break;
			}
		}

		return primeFactors;
	}

	public static BigInteger factorial(int n) {
		return (n == 0) ? BigInteger.ONE : BigInteger.valueOf(n).multiply(factorial(n - 1));
	}
}