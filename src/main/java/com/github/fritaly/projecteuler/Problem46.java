package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Problem "Goldbach's other conjecture".
 *
 * @author francois_ritaly
 */
public class Problem46 {

	public static void main(String[] args) {
		final Set<Long> primes = Utils.getPrimeNumbers(1000 * 1000);

		final List<Long> squares = new ArrayList<>();

		final NumberGenerator generator = Utils.createSquareNumberGenerator();

		while (true) {
			final long square = generator.next();

			if (square > 1000 * 1000) {
				break;
			}

			squares.add(square);
		}

		for (long i = 3; i < Integer.MAX_VALUE; i += 2) {
			if (primes.contains(i)) {
				continue;
			}

			boolean found = false;

			for (int j = 0; j < squares.size(); j++) {
				final long square = squares.get(j);

				if (square << 1 > i) {
					continue;
				}

				final long remainder = i - (square << 1);

				if (primes.contains(remainder)) {
					found = true;
					System.out.println(String.format("%d = %d + 2 x (%d^2)", i, remainder, square));
					break;
				}
			}

			if (!found) {
				System.out.println(i);
				break;
			}
		}
	}
}