package com.github.fritaly.projecteuler;

import java.math.BigInteger;

/**
 * Problem "Lychrel numbers".
 *
 * @author francois_ritaly
 */
public class Problem55 {

	private static BigInteger reverse(BigInteger n) {
		return new BigInteger(Utils.reverse(n.toString()));
	}

	public static void main(String[] args) {
		int count = 0;

		for (int i = 1; i < 10000; i++) {
			int iterations = 0;

			BigInteger current = BigInteger.valueOf(i);

			do {
				current = current.add(reverse(current));

				if (++iterations == 50) {
					// A number is considered a Lychrel number after more than
					// 50 iterations
					System.out.println(String.format("%d is a Lychrel number", i));

					count++;
					break;
				}
			} while (!Utils.isPalindrome(current));
		}

		System.out.println("Result: " + count);
	}
}