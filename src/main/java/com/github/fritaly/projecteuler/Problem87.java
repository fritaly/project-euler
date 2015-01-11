package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem "Prime power triples".
 *
 * @author francois_ritaly
 */
public class Problem87 {

	public static void main(String[] args) {
		final int limit = 50 * 1000 * 1000;
		final Set<Long> primes = Utils.getPrimeNumbers((int) Math.sqrt(limit));

		final List<Long> squares = new ArrayList<>();
		final List<Long> cubes = new ArrayList<>();
		final List<Long> fourthPowers = new ArrayList<>();

		for (Long prime : primes) {
			final long square = prime * prime;
			final long cube = square * prime;
			final long fourthPower = square * square;

			if (square < limit) {
				squares.add(square);

				if (cube < limit) {
					cubes.add(cube);

					if (fourthPower < limit) {
						fourthPowers.add(fourthPower);
					}
				}
			}
		}

		// Use a set because there are different ways to obtain a given number
		final Set<Long> numbers = new HashSet<>();

		for (int i = 0; i < squares.size(); i++) {
			final long square = squares.get(i);

			for (int j = 0; j < cubes.size(); j++) {
				final long cube = cubes.get(j);

				if (square + cube > limit) {
					break;
				}

				for (int k = 0; k < fourthPowers.size(); k++) {
					final long fourthPower = fourthPowers.get(k);

					final long n = square + cube + fourthPower;

					if (n > limit) {
						break;
					}

					numbers.add(n);
				}
			}
		}

		System.out.println("Result: " + numbers.size());
	}
}