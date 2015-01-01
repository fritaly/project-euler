package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Integer right triangles".
 *
 * @author francois_ritaly
 */
public class Problem39 {

	private static class Solution implements Comparable<Solution> {
		final int a, b, c;

		public Solution(int a, int b, int c) {
			// Normalize the values by sorting them
			this.a = Math.min(a, Math.min(b, c));
			this.c = Math.max(a, Math.max(b, c));

			// Infer the last value
			final List<Integer> list = new ArrayList<>(Arrays.asList(a, b, c));
			list.remove(Integer.valueOf(this.a));
			list.remove(Integer.valueOf(this.c));

			this.b = list.iterator().next();
		}

		@Override
		public int compareTo(Solution other) {
			if (this.a == other.a) {
				if (this.b == other.b) {
					return this.c - other.c;
				}

				return this.b - other.b;
			}

			return this.a - other.a;
		}

		@Override
		public String toString() {
			return String.format("{%d, %d, %d}", a, b, c);
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj instanceof Solution) {
				final Solution other = (Solution) obj;

				return (this.a == other.a) && (this.b == other.b) && (this.c == other.c);
			}

			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		int maxSolutions = 0;
		int result = 0;

		// p = a + b + c
		for (int p = 1; p <= 1000; p++) {
			final Set<Solution> solutions = new TreeSet<>();

			for (int a = 1; a < p; a++) {
				final int squareA = a * a;

				for (int b = 1; b < p - a; b++) {
					final int squareB = b * b;
					final int c = p - a - b;
					final int squareC = c * c;

					if (squareA + squareB == squareC) {
						solutions.add(new Solution(a, b, c));
					}
				}
			}

			// System.out.println(solutions);

			if (solutions.size() > maxSolutions) {
				maxSolutions = solutions.size();
				result = p;
			}
		}

		System.out.println("Result: " + result);
	}
}