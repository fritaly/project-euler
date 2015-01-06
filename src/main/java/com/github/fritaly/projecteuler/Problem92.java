package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem "Square digit chains".
 *
 * @author francois_ritaly
 */
public class Problem92 {

	private static int square(int n) {
		switch (n) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 4;
		case 3:
			return 9;
		case 4:
			return 16;
		case 5:
			return 25;
		case 6:
			return 36;
		case 7:
			return 49;
		case 8:
			return 64;
		case 9:
			return 81;
		default:
			throw new IllegalArgumentException("Unsupported value: " + n);
		}
	}

	private static int sumOfSquareDigits(int n) {
		int sum = 0;

		for (char c : Integer.toString(n).toCharArray()) {
			sum += square(c - '0');
		}

		return sum;
	}

	public static void main(String[] args) {
		final int limit = 10 * 1000 * 1000;

		// Note: There are fewer chains ending with 1 than 89 so only store the
		// results for numbers whose chain ends with 1
		final boolean[] table1 = new boolean[limit + 1];
		table1[1] = true;

		int count = 0;

		// The numbers in the current chain
		final List<Integer> chain = new ArrayList<>();

		for (int n = 1; n < limit; n++) {
			int current = n;

			chain.clear();

			while (true) {
				if (table1[current]) {
					// The current number belongs to a 1-chain
					for (Integer number : chain) {
						table1[number] = true;
					}

					break;
				} else if ((current < n) || (current == 89)) {
					// This number has already been processed and doesn't belong
					// to a 1-chain so it leads to 89
					count++;

					break;
				}

				chain.add(current);

				current = sumOfSquareDigits(current);
			}
		}

		System.out.println("Result: " + count);
	}
}