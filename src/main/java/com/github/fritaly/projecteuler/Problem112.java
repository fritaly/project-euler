package com.github.fritaly.projecteuler;

/**
 * Problem "Bouncy numbers".
 *
 * @author francois_ritaly
 */
public class Problem112 {

	private static enum Type {
		BOUNCY, NOT_BOUNCY;
	}

	private static Type getType(long n) {
		if (n < 100) {
			return Type.NOT_BOUNCY;
		}

		final String text = Long.toString(n);

		boolean increasing = true;
		boolean decreasing = true;

		char previousChar = text.charAt(0);

		for (int i = 1; i < text.length(); i++) {
			final char c = text.charAt(i);

			if (c > previousChar) {
				decreasing = false;
			}
			if (c < previousChar) {
				increasing = false;
			}

			previousChar = c;
		}

		return !increasing && !decreasing ? Type.BOUNCY : Type.NOT_BOUNCY;
	}

	public static void main(String[] args) {
		int bouncy = 0;

		for (int i = 1; i <= Integer.MAX_VALUE; i++) {
			final Type type = getType(i);

			if (Type.BOUNCY.equals(type)) {
				bouncy++;
			}

			if (bouncy * 100 == 99 * i) {
				System.out.println("Result: " + i);

				break;
			}
		}
	}
}