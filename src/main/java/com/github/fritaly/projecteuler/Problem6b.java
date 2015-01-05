package com.github.fritaly.projecteuler;

/**
 * Problem "Sum square difference".
 *
 * @author francois_ritaly
 */
public class Problem6b {

	public static void main(String[] args) {
		final int n = 100;

		// The sum of all integers from 1 to n
		final int sum = n * (n + 1) / 2;

		// The sum of all squares from 1 to n
		final int sumOfSquares = n * (n + 1) * (2 * n + 1) / 6;

		System.out.println("Result: " + (sum * sum - sumOfSquares));
	}
}