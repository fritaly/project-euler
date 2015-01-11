package com.github.fritaly.projecteuler;

import java.util.Scanner;

/**
 * Problem "Largest exponential".
 *
 * @author francois_ritaly
 */
public class Problem99 {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(Problem99.class.getResourceAsStream("p099_base_exp.txt"));

		double max = 0.0f;
		int solution = 0;
		int line = 1;

		while (scanner.hasNextLine()) {
			final String[] array = scanner.nextLine().split(",");
			final int base = Integer.parseInt(array[0]);
			final int exponent = Integer.parseInt(array[1]);

			final double value = exponent * Math.log(base);

			if (value > max) {
				max = value;
				solution = line;
			}

			line++;
		}

		scanner.close();

		System.out.println("Result: " + solution);
	}
}