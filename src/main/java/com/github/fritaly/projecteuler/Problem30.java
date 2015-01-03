package com.github.fritaly.projecteuler;

import java.io.IOException;

/**
 * Problem "Digit fifth powers".
 *
 * @author francois_ritaly
 */
public class Problem30 {

	private static int power5(int n) {
		return n * n * n * n * n;
	}

	public static void main(String[] args) throws IOException {
		int total = 0;

		for (int i = 2; i <= 999999; i++) {
			final int a = i / 100000;
			final int b = (i % 100000) / 10000;
			final int c = (i % 10000) / 1000;
			final int d = (i % 1000) / 100;
			final int e = (i % 100) / 10;
			final int f = (i % 10);

			// System.out.println(String.format("%d = %d / %d / %d / %d / %d", i, a, b, c, d, e));

			final int sum = power5(a) + power5(b) + power5(c) + power5(d) + power5(e) + power5(f);

			if (sum == i) {
				System.out.println(i);
				total += sum;
			}
		}

		System.out.println("Result: " + total);
	}
}