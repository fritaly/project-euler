package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Problem "Smallest multiple".
 *
 * @author francois_ritaly
 */
public class Problem5 {

	private static long computeProduct(Collection<Long> factors) {
		long result = 1;

		for (Long factor : factors) {
			result *= factor;
		}

		return result;
	}

	private static int count(Collection<Long> collection, Long value) {
		int count = 0;

		for (Long element : collection) {
			if (element.intValue() == value.intValue()) {
				count++;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		final List<Long> factors = new ArrayList<>();

		for (int i = 1; i <= 20; i++) {
			final List<Long> factorPrimes = Utils.factor(i);

			System.out.println(i + " = " + factorPrimes);

			for (Long factor : factorPrimes) {
				final int expected = count(factorPrimes, factor);
				final int actual = count(factors, factor);

				if (actual < expected) {
					for (int j = actual; j < expected; j++) {
						factors.add(factor);
					}
				}
			}
		}

		Collections.sort(factors);

		System.out.println("Factors: " + factors);
		System.out.println("Result: " + computeProduct(factors));
	}
}