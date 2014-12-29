package com.github.fritaly.projecteuler;

import java.math.BigInteger;

/**
 * Problem "Lattice paths".
 *
 * @author francois_ritaly
 */
public class Problem15 {

	private static BigInteger combinations(int n, int count) {
		BigInteger result = BigInteger.ONE;

		for (int i = 1; i <= count; i++) {
			result = result.multiply(BigInteger.valueOf(n--));
		}

		return result;
	}

	private static BigInteger factorial(int n) {
		if (n == 0) {
			return BigInteger.ONE;
		}

		return BigInteger.valueOf(n).multiply(factorial(n - 1));
	}

	public static void main(String[] args) {
		// In a 20x20 grid, it takes 40 moves (20 right + 20 down) to move from
		// the top left corner to the bottom right corner. All the right moves
		// can be distinguished from each other. The same holds true for the
		// right moves.

		// Result = 40! / (20! x 20!) = (40 x 39 x 38 x ... x 22 x 21) / 20!
		System.out.println("Result: " + combinations(40, 20).divide(factorial(20)));
	}
}