package com.github.fritaly.projecteuler;

/**
 * Problem "Multiples of 3 and 5".
 *
 * @author francois_ritaly
 */
public class Problem1 {

	public static void main(String[] args) {
		int sum = 0;

		for (int i = 1; i < 1000; i++) {
			if ((i % 3 == 0) || (i % 5 == 0)) {
				sum += i;
			}
		}

		System.out.println("Sum: " + sum);
	}
}