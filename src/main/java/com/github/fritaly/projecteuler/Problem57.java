package com.github.fritaly.projecteuler;

/**
 * Problem "Square root convergents".
 *
 * @author francois_ritaly
 */
public class Problem57 {

	public static void main(String[] args) {
		final BigFraction one = new BigFraction(1);
		final BigFraction two = new BigFraction(2);
		BigFraction fraction = two;

		int count = 0;

		for (int i = 1; i <= 1000; i++) {
			final BigFraction result = one.add(fraction.reciprocal());

			System.out.println(result);

			if (result.getNumerator().toString().length() > result.getDenominator().toString().length()) {
				count++;
			}

			fraction = two.add(fraction.reciprocal());
		}

		System.out.println("Result: " + count);
	}
}