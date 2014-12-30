package com.github.fritaly.projecteuler;

import java.io.IOException;

/**
 * Problem "Quadratic primes".
 *
 * @author francois_ritaly
 */
public class Problem27 {

	private static class Formula {
		final int a, b;

		public Formula(int a, int b) {
			this.a = a;
			this.b = b;
		}

		int compute(int n) {
			return n * (n + a) + b;
		}
	}

	public static void main(String[] args) throws IOException {
		int result = -1;
		int maxCount = 0;

		for (int a = -999; a <= 999; a++) {
			for (int b = -999; b <= 999; b++) {
				final Formula formula = new Formula(a, b);

				int count = 0;

				for (int n = 0; n < Integer.MAX_VALUE; n++) {
					final int number = formula.compute(n);

					if ((number <= 0) || !Utils.isPrimeNumber(number)) {
						break;
					}

					count++;
				}

				if (count > 0) {
					// System.out.println("Found a sequence of " + count + " prime numbers for (a,b) = (" + a + "," + b + ")");
				}

				if (count > maxCount) {
					maxCount = count;
					result = a * b;
				}
			}
		}

		System.out.println("Result: " + result);
	}
}