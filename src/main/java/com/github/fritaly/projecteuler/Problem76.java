package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem "Counting summations".
 *
 * @author francois_ritaly
 */
public class Problem76 {

	public static void main(String[] args) {
		final int amount = 100;

		// Generate values from 1 to 99 (because we search all sums using at
		// least 2 numbers to get 100)
		final List<Integer> values = new ArrayList<>();

		for (int i = 1; i < amount; i++) {
			values.add(i);
		}

		final int[] table = new int[amount + 1];

		// Only 1 way to get a sum of zero
		table[0] = 1;

		// Loop over all the available values to generate sums
		for (int i = 0; i < values.size(); i++) {
			final int value = values.get(i);

			for (int j = value; j < table.length; j++) {
				table[j] += table[j - value];
			}
		}

		System.out.println("Result: " + table[100]);
	}
}