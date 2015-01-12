package com.github.fritaly.projecteuler;

import java.util.Scanner;

/**
 * Problem "Roman numerals".
 *
 * @author francois_ritaly
 */
public class Problem89 {

	private static enum Numeral {
		I(1),
		V(5),
		X(10),
		L(50),
		C(100),
		D(500),
		M(1000);

		private int value;

		private Numeral(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	private static class RomanNumber {

		private final String representation;

		public RomanNumber(String string) {
			this.representation = string;
		}

		@Override
		public String toString() {
			return representation;
		}

		public String toRoman() {
			final StringBuilder buffer = new StringBuilder();

			int value = getValue();

			while (value > 0) {
				if (value >= 1000) {
					buffer.append("M");

					value -= 1000;
				} else if (value >= 900) {
					buffer.append("CM");

					value -= 900;
				} else if (value >= 500) {
					buffer.append("D");

					value -= 500;
				} else if (value >= 400) {
					buffer.append("CD");

					value -= 400;
				} else if (value >= 100) {
					buffer.append("C");

					value -= 100;
				} else if (value >= 90) {
					buffer.append("XC");

					value -= 90;
				} else if (value >= 50) {
					buffer.append("L");

					value -= 50;
				} else if (value >= 40) {
					buffer.append("XL");

					value -= 40;
				} else if (value >= 10) {
					buffer.append("X");

					value -= 10;
				} else if (value == 9) {
					buffer.append("IX");

					value -= 9;
				} else if (value >= 5) {
					buffer.append("V");

					value -= 5;
				} else if (value == 4) {
					buffer.append("IV");

					value -= 4;
				} else if (value == 3) {
					buffer.append("III");

					value -= 3;
				} else if (value == 2) {
					buffer.append("II");

					value -= 2;
				} else if (value == 1) {
					buffer.append("I");

					value -= 1;
				}
			}

			return buffer.toString();
		}

		int getValue() {
			int value = 0;

			Numeral previousNumeral = null;

			for (char c : representation.toCharArray()) {
				final Numeral numeral = Numeral.valueOf(Character.toString(c));

				if ((previousNumeral == null) || (numeral.compareTo(previousNumeral) <= 0)) {
					value += numeral.getValue();
				} else {
					value -= previousNumeral.getValue();
					value += (numeral.getValue() - previousNumeral.getValue());
				}

				previousNumeral = numeral;
			}

			return value;
		}
	}

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(Problem89.class.getResourceAsStream("p089_roman.txt"));

		int saved = 0;

		while (scanner.hasNextLine()) {
			final RomanNumber number = new RomanNumber(scanner.nextLine());
			final String optimal = number.toRoman();

			saved += (number.representation.length() - optimal.length());

			System.out.println(number + " = " + number.getValue() + " (" + number.toRoman() + ")");
		}

		scanner.close();

		System.out.println("Result: " + saved);
	}
}