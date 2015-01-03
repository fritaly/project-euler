package com.github.fritaly.projecteuler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Utils {

	public static String sort(String string) {
		if ((string == null) || "".equals(string)) {
			return string;
		}

		final char[] chars = string.toCharArray();

		Arrays.sort(chars);

		return new String(chars);
	}

	public static boolean isPalindrome(long n) {
		return isPalindrome(String.format("%d", n));
	}

	public static boolean isPalindrome(BigInteger n) {
		return isPalindrome(n.toString());
	}

	public static boolean isPalindrome(String text) {
		boolean palindrome = true;

		for (int i = 0; i < text.length() / 2; i++) {
			if (text.charAt(i) != text.charAt(text.length() - 1 - i)) {
				palindrome = false;
				break;
			}
		}

		return palindrome;
	}

	public static int max(int... values) {
		int max = Integer.MIN_VALUE;

		for (int value : values) {
			max = Math.max(max, value);
		}

		return max;
	}

	public static long max(long... values) {
		long max = Long.MIN_VALUE;

		for (long value : values) {
			max = Math.max(max, value);
		}

		return max;
	}

	public static long sum(Collection<Long> values) {
		long sum = 0;

		for (Long value : values) {
			sum += value;
		}

		return sum;
	}

	public static NumberGenerator createTriangleNumberGenerator() {
		return new NumberGenerator() {
			@Override
			protected long generate(long n) {
				return n * (n + 1) / 2;
			}
		};
	}

	public static NumberGenerator createSquareNumberGenerator() {
		return new NumberGenerator() {
			@Override
			protected long generate(long n) {
				return n * n;
			}
		};
	}

	public static NumberGenerator createCubeNumberGenerator() {
		return new NumberGenerator() {
			@Override
			protected long generate(long n) {
				return n * n * n;
			}
		};
	}

	public static NumberGenerator createPentagonalNumberGenerator() {
		return new NumberGenerator() {
			@Override
			protected long generate(long n) {
				return n * (3 * n - 1) / 2;
			}
		};
	}

	public static NumberGenerator createHexagonalNumberGenerator() {
		return new NumberGenerator() {
			@Override
			protected long generate(long n) {
				return n * (2 * n - 1);
			}
		};
	}

	public static NumberGenerator createHeptagonalNumberGenerator() {
		return new NumberGenerator() {
			@Override
			protected long generate(long n) {
				return n * (5 * n - 3) / 2;
			}
		};
	}

	public static NumberGenerator createOctagonalNumberGenerator() {
		return new NumberGenerator() {
			@Override
			protected long generate(long n) {
				return n * (3 * n - 2);
			}
		};
	}

	public static String padLeft(String string, int length, char c) {
		if (string == null) {
			throw new IllegalArgumentException("The given string is null");
		}
		if (length < 0) {
			throw new IllegalArgumentException(String.format("The given length (%d) must be positive or zero", length));
		}
		if (string.length() >= length) {
			return string;
		}

		final StringBuilder buffer = new StringBuilder(length);

		for (int i = 0; i < length - string.length(); i++) {
			buffer.append(c);
		}

		return buffer.append(string).toString();
	}

	public static String padRight(String string, int length, char c) {
		if (string == null) {
			throw new IllegalArgumentException("The given string is null");
		}
		if (length < 0) {
			throw new IllegalArgumentException(String.format("The given length (%d) must be positive or zero", length));
		}
		if (string.length() >= length) {
			return string;
		}

		final StringBuilder buffer = new StringBuilder(length);
		buffer.append(string);

		for (int i = 0; i < length - string.length(); i++) {
			buffer.append(c);
		}

		return buffer.toString();
	}

	public static String reverse(String string) {
		if ((string == null) || "".equals(string) || (string.length() == 1)) {
			return string;
		}

		final StringBuilder buffer = new StringBuilder(string.length());

		for (int i = 0; i < string.length(); i++) {
			buffer.append(string.charAt(string.length() - 1 - i));
		}

		return buffer.toString();
	}

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