package com.github.fritaly.projecteuler;

import static com.github.fritaly.projecteuler.Utils.sum;

import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Amicable numbers".
 *
 * @author francois_ritaly
 */
public class Problem21 {

	private static Set<Long> getDivisors(long n) {
		final int limit = (int) Math.sqrt(n);

		final Set<Long> set = new TreeSet<>();

		for (long i = 1; i <= limit; i++) {
			if (n % i == 0) {
				set.add(i);
				set.add(n / i);
			}
		}

		// Remove the number itself from the divisors
		set.remove(n);

		return set;
	}

	private static long sumDivisors(int n) {
		return sum(getDivisors(n));
	}

	public static void main(String[] args) {
		long result = 0;

		for (int a = 2; a < 10000; a++) {
			final long b = sumDivisors(a);
			final long c = sumDivisors((int) b);

			if (a == b) {
				continue;
			}

			if (c == a) {
				result += a;

				System.out.println(a + " - " + b);
			}
		}

		System.out.println("Result: " + result);
	}
}