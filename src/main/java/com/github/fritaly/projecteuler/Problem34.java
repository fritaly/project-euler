package com.github.fritaly.projecteuler;

import java.io.IOException;

/**
 * Problem "Digit factorials".
 *
 * @author francois_ritaly
 */
public class Problem34 {

	private static int factorial(int n) {
		switch (n) {
		case 0:
			return 1;
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
			return 5040;
		case 8:
			return 40320;
		case 9:
			return 362880;
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

	public static void main(String[] args) throws IOException {
		long sum = 0;

		for (int i = 3; i < 1000 * 1000; i++) {
			if (sumFactorials(i) == i) {
				System.out.println(i);

				sum += i;
			}
		}

		System.out.println("Result: " + sum);
	}
}