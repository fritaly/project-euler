package com.github.fritaly.projecteuler;

import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Pandigital products".
 *
 * @author francois_ritaly
 */
public class Problem32 {

	private static boolean isPandigital(String string) {
		for (char c = '1'; c < '1' + string.length(); c++) {
			if (string.indexOf(c) == -1) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		final Set<Integer> products = new TreeSet<>();

		int sum = 0;

		for (int a = 1; a < 10000; a++) {
			for (int b = 1;; b++) {
				final int product = a * b;

				final String text = String.format("%d%d%d", a, b, product);

				if ((text.length() == 9) && isPandigital(text)) {
					if (products.add(product)) {
						System.out.println(String.format("%d x %d = %d", a, b, product));

						sum += product;
					}
				} else if (text.length() > 9) {
					break;
				}
			}
		}

		System.out.println("Result: " + sum);
	}
}