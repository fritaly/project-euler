package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Counting fractions in a range".
 *
 * @author francois_ritaly
 */
public class Problem73 {

	public static void main(String[] args) {
		final Set<Fraction> fractions = new TreeSet<>();

		final int limit = 12000;

		// We want the fractions such that 1/3 < f < 1/2 -> d/3 < n < d/2
		for (int d = 1; d <= limit; d++) {
			for (int n = d / 3; n <= d / 2; n++) {
				// The fraction is automatically reduced at instantiation
				fractions.add(new Fraction(n, d));

				// System.out.println(String.format("%d/%d", n, d));
			}
		}

		final Fraction oneThird = new Fraction(1, 3);
		final Fraction oneHalf = new Fraction(1, 2);

		// Use a list to find the fraction indices
		final List<Fraction> list = new ArrayList<>(fractions);

		Collections.sort(list);

		// System.out.println("Fractions: " + list);
		System.out.println("index(1/3): " + list.indexOf(oneThird));
		System.out.println("index(1/2): " + list.indexOf(oneHalf));
		System.out.println("Result: " + (list.indexOf(oneHalf) - list.indexOf(oneThird) - 1));
	}
}