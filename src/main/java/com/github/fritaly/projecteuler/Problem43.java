package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem "Sub-string divisibility".
 *
 * @author francois_ritaly
 */
public class Problem43 {

	private static boolean isPandigital(long n) {
		final String text = Long.toString(n);

		if (text.length() != 10) {
			return false;
		}

		for (int c = '0'; c <= '9'; c++) {
			if (text.indexOf(c) == -1) {
				return false;
			}
		}

		return true;
	}

	private static List<Integer> filter(List<Integer> list, String prefix) {
		final List<Integer> result = new ArrayList<>();

		for (Integer number : list) {
			if (String.format("%03d", number).startsWith(prefix)) {
				result.add(number);
			}
		}

		return result;
	}

	private static char getComplementaryDigit(String text) {
		for (char c = '0'; c <= '9'; c++) {
			if (text.indexOf(c) == -1) {
				return c;
			}
		}

		throw new IllegalArgumentException();
	}

	public static void main(String[] args) {
		final List<Integer> list2 = new ArrayList<>();
		final List<Integer> list3 = new ArrayList<>();
		final List<Integer> list5 = new ArrayList<>();
		final List<Integer> list7 = new ArrayList<>();
		final List<Integer> list11 = new ArrayList<>();
		final List<Integer> list13 = new ArrayList<>();
		final List<Integer> list17 = new ArrayList<>();

		for (int i = 1; i <= 999; i++) {
			final int a = i / 100;
			final int b = (i % 100) / 10;
			final int c = (i % 10);

			if ((a != b) && (b != c)) {
				if (i % 2 == 0) {
					list2.add(i);
				}
				if (i % 3 == 0) {
					list3.add(i);
				}
				if (i % 5 == 0) {
					list5.add(i);
				}
				if (i % 7 == 0) {
					list7.add(i);
				}
				if (i % 11 == 0) {
					list11.add(i);
				}
				if (i % 13 == 0) {
					list13.add(i);
				}
				if (i % 17 == 0) {
					list17.add(i);
				}
			}
		}

		long sum = 0;

		for (Integer i : list2) {
			final String suffix2 = String.format("%03d", i).substring(1);

			for (Integer j : filter(list3, suffix2)) {
				final String suffix3 = String.format("%03d", j).substring(1);

				for (Integer k : filter(list5, suffix3)) {
					final String suffix5 = String.format("%03d", k).substring(1);

					for (Integer l : filter(list7, suffix5)) {
						final String suffix7 = String.format("%03d", l).substring(1);

						for (Integer m : filter(list11, suffix7)) {
							final String suffix11 = String.format("%03d", m).substring(1);

							for (Integer n : filter(list13, suffix11)) {
								final String suffix13 = String.format("%03d", n).substring(1);

								for (Integer o : filter(list17, suffix13)) {
									final String suffix = String.format("%d%d%d", i, l, o);
									final String solution = getComplementaryDigit(suffix) + suffix;

									if (isPandigital(Long.parseLong(solution))) {
										System.out.println(solution);

										sum += Long.parseLong(solution);
									}
								}
							}
						}
					}
				}
			}
		}

		System.out.println("Result: " + sum);
	}
}