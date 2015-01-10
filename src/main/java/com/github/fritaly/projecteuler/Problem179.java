package com.github.fritaly.projecteuler;

import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Consecutive positive divisors".
 *
 * @author francois_ritaly
 */
public class Problem179 {

	private static Set<Long> getDivisors(long n) {
		final int limit = (int) Math.sqrt(n);

		final Set<Long> set = new TreeSet<>();

		for (long i = 1; i <= limit; i++) {
			if (n % i == 0) {
				set.add(i);
				set.add(n / i);
			}
		}

		return set;
	}

	public static void main(String[] args) {
		long result = 0;

		// The number of divisors for 1 is 1
		int previousDivisors = 1;

		for (int n = 2; n < 10 * 1000 * 1000; n++) {
			final Set<Long> divisors = getDivisors(n);

			if (divisors.size() == previousDivisors) {
				System.out.println(String.format("%d / %d", n, n-1));

				result++;
			}

			previousDivisors = divisors.size();
		}

		System.out.println("Result: " + result);
	}
}