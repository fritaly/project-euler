package com.github.fritaly.projecteuler;

import static com.github.fritaly.projecteuler.Utils.factorial;

/**
 * Problem "Red, green, and blue tiles".
 *
 * @author francois_ritaly
 */
public class Problem117 {

	public static void main(String[] args) {
		final int length = 50;
		long sum = 0;

		for (int blue = 0; length - blue * 4 >= 0; blue++) {
			final int length1 = length - blue * 4;

			for (int green = 0; length1 - green * 3 >= 0; green++) {
				final int length2 = length1 - green * 3;

				for (int red = 0; length2 - red * 2 >= 0; red++) {
					final int length3 = length2 - red * 2;

					for (int black = 0; length3 - black >= 0; black++) {
						final int length4 = length3 - black;

						if (length4 == 0) {
							final int total = blue + green + red + black;

							sum += factorial(total).divide(factorial(blue)).divide(factorial(green)).divide(factorial(red))
									.divide(factorial(black)).longValue();
						}
					}
				}
			}
		}

		System.out.println("Result: " + sum);
	}
}