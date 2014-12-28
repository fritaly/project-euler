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
		for (int c = 1; c < 1000; c++) {
			for (int b = 1; (b < c) && (b + c < 1000); b++) {
				for (int a = 1; (a < b) && (a + b + c <= 1000); a++) {
					if ((a + b + c == 1000) && isSpecialTriplet(a, b, c)) {
						System.out.println("Result: " + (a * b * c));
						break;
					}
				}
			}
		}
	}
}