package com.github.fritaly.projecteuler;

/**
 * Problem "Even Fibonacci numbers".
 *
 * @author francois_ritaly
 */
public class Problem2 {

	public static void main(String[] args) {
		int i = 1, j = 2;
		long sum = 2;

		while (true) {
			int k = i + j;

			if (k > 4000000) {
				break;
			}

			if ((k % 2 == 1)) {
				sum += k;
			}

			i = j;
			j = k;
		}

		System.out.println("Sum: " + sum);
	}
}