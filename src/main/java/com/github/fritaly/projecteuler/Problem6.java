package com.github.fritaly.projecteuler;

/**
 * Problem "Sum square difference".
 *
 * @author francois_ritaly
 */
public class Problem6 {

	public static void main(String[] args) {
		long a = 0, b = 0;

		for (int n = 1; n <= 100; n++) {
			a += (n * n);
			b += n;
		}

		System.out.println("Result: " + ((b * b) - a));
	}
}