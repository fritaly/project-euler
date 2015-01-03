package com.github.fritaly.projecteuler;

import java.util.Set;
import java.util.TreeSet;


/**
 * Problem "Digit factorial chains".
 *
 * @author francois_ritaly
 */
public class Problem74 {

	// We know that for this puzzle, 0 <= n <= 9
	private static int factorial(int n) {
		switch (n) {
		case 0:
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 6;
		case 4:
			return 24;
		case 5:
			return 120;
		case 6:
			return 720;
		case 7:
			return 7 * 720;
		case 8:
			return 8 * 7 * 720;
		case 9:
			return 9 * 8 * 7 * 720;
		default:
			throw new IllegalArgumentException("Unexpected value: " + n);
		}
	}

	private static int sumFactorials(int n) {
		int result = 0;

		for (char c : Integer.toString(n).toCharArray()) {
			result += factorial(c - '0');
		}

		return result;
	}

	public static void main(String[] args) {
		int count = 0;

		for (int i = 1; i < 1000 * 1000; i++) {
			int current = i;

			final StringBuilder builder = new StringBuilder();
			builder.append(i);

			final Set<Integer> visited = new TreeSet<>();

			int length = 1;

			do {
				current = sumFactorials(current);

				if (visited.contains(current)) {
					builder.append(" ( -> ").append(current).append(")");
					break;
				}

				builder.append(" -> ").append(current);

				visited.add(current);
				length++;
			} while (current != i);

			builder.append(" (x").append(length).append(")");

			if (length == 60) {
				System.out.println(builder.toString());
				count++;
			}
		}

		System.out.println("Result: " + count);
	}
}