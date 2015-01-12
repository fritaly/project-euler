package com.github.fritaly.projecteuler;

/**
 * Problem "Using up to one million tiles how many different "hollow
 * " square laminae can be formed ?".
 *
 * @author francois_ritaly
 */
public class Problem173 {

	private static final boolean DEBUG = false;

	private static int createSquare(int size) {
		if (size < 3) {
			throw new IllegalArgumentException(String.format("The size (%d) must be at least 3", size));
		}

		// 2 * (size + (size - 2))
		return (size - 1) << 2;
	}

	public static void main(String[] args) {
		final int budget = 1000 * 1000; // 100;
		int count = 0;

		for (int size = 3; ; size++) {
			int remainder = budget;

			final int squares = createSquare(size);

			if (squares > remainder) {
				break;
			}

			if (squares <= remainder) {
				remainder -= squares;

				if (DEBUG) {
					System.out.println(String.format("Outer size: %d / Outer square (%d squares) / Remainder: %d", size, squares,
							remainder));
				}

				count++;
			}

			for (int j = size - 2; j >= 3; j -= 2) {
				final int squares2 = createSquare(j);

				if (squares2 > remainder) {
					break;
				}

				if (squares2 <= remainder) {
					remainder -= squares2;

					if (DEBUG) {
						System.out.println(String.format("Inner size: %d / Inner square (%d squares) / Remainder: %d", j,
								squares2, remainder));
					}

					count++;
				}
			}

			if (DEBUG) {
				System.out.println("");
			}
		}

		System.out.println("Result: " + count);
	}
}