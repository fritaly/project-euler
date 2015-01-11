package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Non-abundant sums".
 *
 * @author francois_ritaly
 */
public class Problem23 {

	private static Set<Long> getProperDivisors(long number) {
		final int limit = (int) Math.sqrt(number);

		final Set<Long> set = new TreeSet<>();

		for (long i = 1; i <= limit; i++) {
			if (number % i == 0) {
				set.add(i);
				set.add(number / i);
			}
		}

		// Remove the number itself
		set.remove(number);

		return set;
	}

	public static void main(String[] args) {
		final int limit = 28123;

		final List<Integer> abundantNumbers = new ArrayList<>();

		for (int n = 1; n < limit; n++) {
			final long sum = Utils.sum(getProperDivisors(n));

			if (sum > n) {
				abundantNumbers.add(n);
			}
		}

		System.out.println("Abundant numbers: " + abundantNumbers);

		// Compute all the possible sums < 28123 from 2 abundant numbers
		final Set<Integer> sums = new TreeSet<>();

		for (int i = 0; i < abundantNumbers.size(); i++) {
			final int number1 = abundantNumbers.get(i);

			for (int j = 0; j < abundantNumbers.size(); j++) {
				final int number2 = abundantNumbers.get(j);

				final int sum = number1 + number2;

				if (sum > limit) {
					break;
				}

				sums.add(sum);
			}
		}

		long sum = 0;

		for (int n = 1; n <= limit; n++) {
			if (!sums.contains(n)) {
				System.out.println(String.format("%d found", n));

				sum += n;
			}
		}


		System.out.println("Result: " + sum);
	}
}