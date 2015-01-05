package com.github.fritaly.projecteuler;

/**
 * Problem "Special Pythagorean triplet".
 *
 * @author francois_ritaly
 */
public class Problem9 {

	private static boolean isSpecialTriplet(int a, int b, int c) {
		return (a * a) + (b * b) == (c * c);
	}

	public static void main(String[] args) {
		// "a < b < c" && "a + b + c = 1000"
		for (int a = 1; a < 1000; a++) {
			for (int b = a + 1; (a + b) < 1000; b++) {
				final int c = 1000 - a - b;

				if (isSpecialTriplet(a, b, c)) {
					System.out.println("Result: " + (a * b * c));
					break;
				}
			}
		}
	}
}