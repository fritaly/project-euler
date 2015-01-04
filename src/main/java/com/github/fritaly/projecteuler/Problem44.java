package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem "Pentagon numbers".
 *
 * @author francois_ritaly
 */
public class Problem44 {

	public static void main(String[] args) throws IOException {
		final List<Long> numbers = new ArrayList<>();

		final NumberGenerator generator = Utils.createPentagonalNumberGenerator();

		while (true) {
			final long n = generator.next();

			if (n > 1000 * 1000 * 10) {
				break;
			}

			numbers.add(n);
		}

		for (int i = 0; i < numbers.size() - 1; i++) {
			for (int j = i + 1; j < numbers.size(); j++) {
				final long n1 = numbers.get(i);
				final long n2 = numbers.get(j);
				final long difference = n2 - n1;
				final long sum = n1 + n2;

				if (numbers.contains(difference) && numbers.contains(sum)) {
					System.out.println(String.format("%d / %d - %d", n1, n2, n2 - n1));
				}
			}
		}

		System.out.println("Result: ");
	}
}