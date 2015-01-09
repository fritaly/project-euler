package com.github.fritaly.projecteuler;

import static com.github.fritaly.projecteuler.Utils.factorial;

/**
 * Problem "Red, green or blue tiles".
 *
 * @author francois_ritaly
 */
public class Problem116 {

	private static long count(int length, int size) {
		long count = 0;

		for (int color = 1; color * size <= length; color++) {
			final int black = length - size * color;
			final int total = black + color;

			count += factorial(total).divide(factorial(color)).divide(factorial(black)).longValue();
		}

		return count;
	}

	public static void main(String[] args) {
		final int length = 50;
		long sum = 0;

		sum += count(length, 2); // Red tiles
		sum += count(length, 3); // Green tiles
		sum += count(length, 4); // Blue tiles

		System.out.println("Result: " + sum);
	}
}