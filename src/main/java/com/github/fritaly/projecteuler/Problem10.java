package com.github.fritaly.projecteuler;

import java.util.Arrays;

/**
 * Problem "Summation of primes".
 *
 * @author francois_ritaly
 */
public class Problem10 {

	public static void main(String[] args) {
		final int limit = 2000000;

		final boolean[] array = new boolean[limit + 1];

		Arrays.fill(array, true);

		for (int i = 2; i <= (int) Math.sqrt(limit); i++) {
			if (array[i]) {
				for (int j = i * i; j <= limit; j += i) {
					array[j] = false;
				}
			}
		}

		long sum = 0;

		for (int i = 2; i < array.length; i++) {
			if (array[i]) {
				sum += i;
			}
		}

		System.out.println("Sum: " + sum);
	}
}