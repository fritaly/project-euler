package com.github.fritaly.projecteuler;

import java.util.LinkedList;

/**
 * Problem "Convergents of e".
 *
 * @author francois_ritaly
 */
public class Problem65 {

	public static void main(String[] args) {
		// Lists the digits used for computing the continued fraction representing e
		final LinkedList<Integer> terms = new LinkedList<>();

		// The first term is 2
		terms.add(2);

		// We need to generate 99 terms. The digits follow a pattern: [1,2,1][1,4,1][1,6,1]...
		for (int i = 1; i <= 33; i++) {
			terms.add(1);
			terms.add(i << 1);
			terms.add(1);
		}

		System.out.println(terms);

		BigFraction fraction = new BigFraction(terms.removeLast());

		while (!terms.isEmpty()) {
			fraction = new BigFraction(terms.removeLast()).add(fraction.reciprocal());
		}

		int sum = 0;

		for (char c : fraction.getNumerator().toString().toCharArray()) {
			sum += c - '0';
		}

		System.out.println("Result: " + sum);
	}
}