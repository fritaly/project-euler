package com.github.fritaly.projecteuler;

/**
 * Problem "Pandigital multiples".
 *
 * @author francois_ritaly
 */
public class Problem38 {

	private static boolean isPandigital(String string) {
		for (char c = '1'; c < '1' + string.length(); c++) {
			if (string.indexOf(c) == -1) {
				return false;
			}
		}

		return true;
	}

	private static String sequence(int n) {
		final StringBuilder builder = new StringBuilder();

		for (int i = 0; i < n; i++) {
			if (i > 0) {
				builder.append(", ");
			}

			builder.append(i + 1);
		}

		return builder.toString();
	}

	public static void main(String[] args) {
		int result = 0;

		for (int n = 1; n < 1000 * 1000; n++) {
			final StringBuilder buffer = new StringBuilder();

			for (int i = 1;; i++) {
				final int product = n * i;

				buffer.append(product);

				if (buffer.length() > 9) {
					break;
				}

				if (buffer.length() == 9) {
					final String text = buffer.toString();

					if (isPandigital(text)) {
						System.out.println(String.format("%d x (%s) = %s", n, sequence(i), text));

						result = Math.max(result, Integer.parseInt(text));
					}

					break;
				}
			}
		}

		System.out.println("Result: " + result);
	}
}