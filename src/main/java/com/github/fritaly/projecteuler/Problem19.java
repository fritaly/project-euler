package com.github.fritaly.projecteuler;

/**
 * Problem "Counting Sundays".
 *
 * @author francois_ritaly
 */
public class Problem19 {

	private static boolean isLeapYear(int year) {
		return (year % 400 == 0) || ((year % 4 == 0) && !(year % 100 == 0));
	}

	private static int getDaysInMonth(int year, int month) {
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 2:
			return isLeapYear(year) ? 29: 28;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		}

		throw new IllegalArgumentException("Unknown month: " + month);
	}

	public static void main(String[] args) {
		// 1 Jan 1900 was a Monday -> 7 Jan 1900 was a Sunday
		int year = 1900;
		int month = 1;
		int dayOfMonth = 7;

		int result = 0;

		while (true) {
			if ((dayOfMonth == 1) && (year > 1900)) {
				// Only take into account the days as of 1901/01/01
				// System.out.println(String.format("%02d/%02d/%d", dayOfMonth, month, year));

				result++;
			}

			// Add one week to the current date
			final int daysInMonth = getDaysInMonth(year, month);

			dayOfMonth += 7;

			if (dayOfMonth > daysInMonth) {
				dayOfMonth -= daysInMonth;

				if (month == 12) {
					year++;
					month = 1;
				} else {
					month++;
				}
			}

			if (year > 2000) {
				break;
			}
		}

		System.out.println("Result: " + result);
	}
}