package com.github.fritaly.projecteuler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 * A generator of prime numbers (based on <a
 * href="http://www.thattommyhall.com/2010/10/18/prime-gen/">Infinite Prime
 * Number Generator in Python</a> by Tom Hall).
 * </p>
 * <p>
 * The class is an iterator of integers (not longs) because it would consume too much
 * memory to handle longs.
 * </p>
 *
 * @author francois_ritaly
 */
public final class PrimeGenerator implements Iterator<Integer> {

	private final Map<Integer, int[]> composites = new HashMap<>();

	private int current = 2;

	public PrimeGenerator() {
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	private int[] array(int element) {
		return new int[] { element };
	}

	private int[] concatenate(int[] array, int element) {
		final int[] result = Arrays.copyOf(array, array.length + 1);
		result[result.length - 1] = element;

		return result;
	}

	@Override
	public Integer next() {
		if (current == 2) {
			composites.put(4, array(2));

			return current++;
		}

		while (true) {
			if (!composites.containsKey(current)) {
				final int prime = current;

				composites.put(prime * prime, array(prime));

				current++;

				return prime;
			} else {
				// Generate the next composite on the fly and remove the
				// consumed one
				final int[] increments = composites.remove(current);

				for (int increment : increments) {
					final int key = current + increment;

					if (composites.containsKey(key)) {
						composites.put(key, concatenate(composites.get(key), increment));
					} else {
						composites.put(key, array(increment));
					}
				}
			}

			current++;
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {
		final PrimeGenerator generator = new PrimeGenerator();

		final long start = System.currentTimeMillis();

		while (generator.hasNext()) {
			final Integer prime = generator.next();

			// System.out.println(prime);

			if (prime >= 1000 * 1000 * 10) {
				break;
			}
		}

		System.out.println(System.currentTimeMillis() - start);
	}
}