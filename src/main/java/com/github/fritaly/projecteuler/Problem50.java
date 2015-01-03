package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem "Consecutive prime sum".
 *
 * @author francois_ritaly
 */
public class Problem50 {

	public static void main(String[] args) {
		final int limit = 1000 * 1000;

		final List<Long> primes = new ArrayList<>(Utils.getPrimeNumbers(limit));

		int longestSequence = 0;
		int result = -1;

		for (int i = 0; i < primes.size(); i++) {
			int sum = primes.get(i).intValue();

			int j = i + 1;

			while (j < primes.size()) {
				sum += primes.get(j);

				if (sum > limit) {
					break;
				}

				if (primes.contains(Long.valueOf(sum))) {
					final int length = j - i + 1;

					System.out.println(sum + " (" + length + ")");

					if (length > longestSequence) {
						longestSequence = length;
						result = sum;
					}
				}

				j++;
			}
		}

		System.out.println("Result: " + result);
	}
}