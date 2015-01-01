package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.util.Iterator;

/**
 * Problem "Triangular, pentagonal, and hexagonal".
 *
 * @author francois_ritaly
 */
public class Problem45 {

	public static void main(String[] args) throws IOException {
		final Iterator<Long> triangleNumbers = Utils.createTriangleNumberGenerator();
		final Iterator<Long> pentagonNumbers = Utils.createPentagonNumberGenerator();
		final Iterator<Long> hexagonNumbers = Utils.createHexagonalNumberGenerator();

		long t = triangleNumbers.next(), p = pentagonNumbers.next(), h = hexagonNumbers.next();

		long max = Utils.max(t, p, h);

		while (true) {
			while (t < max) {
				t = triangleNumbers.next();
			}
			while (p < max) {
				p = pentagonNumbers.next();
			}
			while (h < max) {
				h = hexagonNumbers.next();
			}
			if ((t == p) && (p == h)) {
				System.out.println(t + " is triangle, pentagonal & hexagonal");

				if (t > 40755) {
					break;
				}

				t = triangleNumbers.next();
			}

			max = Utils.max(t, p, h);
		}
	}
}