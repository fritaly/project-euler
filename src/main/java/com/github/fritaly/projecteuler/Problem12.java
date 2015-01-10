package com.github.fritaly.projecteuler;

import static com.github.fritaly.projecteuler.Utils.createTriangleNumberGenerator;

import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Highly divisible triangular number".
 *
 * @author francois_ritaly
 */
public class Problem12 {

	private static int getDivisors(long n) {
		final int limit = (int) Math.sqrt(n);

		final Set<Long> set = new TreeSet<>();

		for (long i = 1; i <= limit; i++) {
			if (n % i == 0) {
				set.add(i);
				set.add(n / i);
			}
		}

		return set.size();
	}

	public static void main(String[] args) {
		final NumberGenerator generator = createTriangleNumberGenerator();

		while (true) {
			final Long number = generator.next();

			if (getDivisors(number) > 500) {
				System.out.println("Result: " + number);
				break;
			}
		}
	}
}