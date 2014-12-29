package com.github.fritaly.projecteuler;

/**
 * Problem "Number letter counts".
 *
 * @author francois_ritaly
 */
public class Problem17 {

	private static String integerToText(int n) {
		switch (n) {
		case 0:
			return "zero";
		case 1:
			return "one";
		case 2:
			return "two";
		case 3:
			return "three";
		case 4:
			return "four";
		case 5:
			return "five";
		case 6:
			return "six";
		case 7:
			return "seven";
		case 8:
			return "eight";
		case 9:
			return "nine";
		case 10:
			return "ten";
		case 11:
			return "eleven";
		case 12:
			return "twelve";
		case 13:
			return "thirteen";
		case 14:
			return "fourteen";
		case 15:
			return "fifteen";
		case 16:
			return "sixteen";
		case 17:
			return "seventeen";
		case 18:
			return "eighteen";
		case 19:
			return "nineteen";
		case 20:
			return "twenty";
		case 30:
			return "thirty";
		case 40:
			return "forty";
		case 50:
			return "fifty";
		case 60:
			return "sixty";
		case 70:
			return "seventy";
		case 80:
			return "eighty";
		case 90:
			return "ninety";
		case 100:
			return "one hundred";
		case 200:
			return "two hundred";
		case 300:
			return "three hundred";
		case 400:
			return "four hundred";
		case 500:
			return "five hundred";
		case 600:
			return "six hundred";
		case 700:
			return "seven hundred";
		case 800:
			return "eight hundred";
		case 900:
			return "nine hundred";
		case 1000:
			return "one thousand";
		}

		if ((21 <= n) && (n <= 29)) {
			return integerToText(20) + "-" + integerToText(n % 20);
		}
		if ((31 <= n) && (n <= 39)) {
			return integerToText(30) + "-" + integerToText(n % 30);
		}
		if ((41 <= n) && (n <= 49)) {
			return integerToText(40) + "-" + integerToText(n % 40);
		}
		if ((51 <= n) && (n <= 59)) {
			return integerToText(50) + "-" + integerToText(n % 50);
		}
		if ((61 <= n) && (n <= 69)) {
			return integerToText(60) + "-" + integerToText(n % 60);
		}
		if ((71 <= n) && (n <= 79)) {
			return integerToText(70) + "-" + integerToText(n % 70);
		}
		if ((81 <= n) && (n <= 89)) {
			return integerToText(80) + "-" + integerToText(n % 80);
		}
		if ((91 <= n) && (n <= 99)) {
			return integerToText(90) + "-" + integerToText(n % 90);
		}
		if ((101 <= n) && (n <= 999)) {
			return integerToText(n / 100) + " hundred and " + integerToText(n % 100);
		}

		throw new IllegalArgumentException("Invalid number: " + n);
	}

	public static void main(String[] args) {
		int sum = 0;

		for (int i = 1; i <= 1000; i++) {
			final String text = integerToText(i);

			// System.out.println(text);

			sum += text.replaceAll("[ -]", "").length();
		}

		System.out.println("Result: " + sum);
	}
}