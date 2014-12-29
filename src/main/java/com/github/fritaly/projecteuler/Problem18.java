package com.github.fritaly.projecteuler;

/**
 * Problem "Maximum path sum I".
 *
 * @author francois_ritaly
 */
public class Problem18 {

	public static void main(String[] args) {
		final String[] input = new String[] {
				"75", //
				"95 64", //
				"17 47 82", //
				"18 35 87 10", //
				"20 04 82 47 65", //
				"19 01 23 75 03 34", //
				"88 02 77 73 07 63 67", //
				"99 65 04 28 06 16 70 92", //
				"41 41 26 56 83 40 80 70 33", //
				"41 48 72 33 47 32 37 16 94 29", //
				"53 71 44 65 25 43 91 52 97 51 14", //
				"70 11 33 28 77 73 17 78 39 68 17 57", //
				"91 71 52 38 17 14 91 43 58 50 27 29 48", //
				"63 66 04 68 89 53 67 30 73 16 69 87 40 31", //
				"04 62 98 27 23 09 70 98 73 93 38 53 60 04 23",
		};

		// numbers[y][x]
		final int[][] numbers = new int[15][15];

		for (int y = 0; y < 15; y++) {
			final String[] strings = input[y].split(" ");

			for (int x = 0; x <= y; x++) {
				numbers[y][x] = Integer.parseInt(strings[x]);
			}
		}

		final int[][] array = new int[15][15];

		for (int y = 0; y < 15; y++) {
			for (int x = 0; x <= y; x++) {
				if (y == 0) {
					// No parent number
					array[y][x] = numbers[y][x];
				} else if (x == 0) {
					// Only one parent number
					array[y][x] = numbers[y][x] + array[y - 1][x];
				} else if (x == y) {
					// Only one parent number
					array[y][x] = numbers[y][x] + array[y - 1][x - 1];
				} else {
					array[y][x] = numbers[y][x] + Math.max(array[y - 1][x - 1], array[y - 1][x]);
				}
			}
		}

		int result = 0;

		for (int x = 0; x < 15; x++) {
			result = Math.max(result, array[14][x]);
		}

		System.out.println("Result: " + result);
	}
}