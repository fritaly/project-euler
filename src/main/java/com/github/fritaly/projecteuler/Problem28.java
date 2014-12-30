package com.github.fritaly.projecteuler;

import java.io.IOException;

/**
 * Problem "Number spiral diagonals".
 *
 * @author francois_ritaly
 */
public class Problem28 {
	public static void main(String[] args) throws IOException {
		final int size = 1001;
		long currentNumber = 1;
		long sum = currentNumber;

		// a NxN grid contains ((size - 1) / 2) concentric layers of numbers
		for (int n = 1; n <= (size - 1) / 2; n++) {
			// The difference between consecutive corner numbers for a layer is 2n
			final int delta = 2 * n;

			final long number1 = currentNumber + delta;
			final long number2 = currentNumber + (2 * delta);
			final long number3 = currentNumber + (3 * delta);
			final long number4 = currentNumber + (4 * delta);

			sum += number1 + number2 + number3 + number4;

			currentNumber = number4;
		}

		System.out.println("Result: " + sum);
	}
}