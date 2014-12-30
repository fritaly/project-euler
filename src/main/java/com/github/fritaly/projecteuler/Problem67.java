package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem "Maximum path sum II".
 *
 * @author francois_ritaly
 */
public class Problem67 {

	private static List<String> readLines() throws IOException {
		InputStreamReader reader = null;
		LineNumberReader lineNumberReader = null;

		final List<String> list = new ArrayList<>();

		try {
			reader = new InputStreamReader(Problem22.class.getResourceAsStream("p067_triangle.txt"));
			lineNumberReader = new LineNumberReader(reader);

			String line = null;

			while ((line = lineNumberReader.readLine()) != null) {
				list.add(line);
			}

			return list;
		} finally {
			lineNumberReader.close();
			reader.close();
		}
	}

	public static void main(String[] args) throws IOException {
		final List<String> lines = readLines();
		final String[] input = lines.toArray(new String[lines.size()]);

		// numbers[y][x]
		final int[][] numbers = new int[input.length][input.length];

		for (int y = 0; y < input.length; y++) {
			final String[] strings = input[y].split(" ");

			for (int x = 0; x <= y; x++) {
				numbers[y][x] = Integer.parseInt(strings[x]);
			}
		}

		final int[][] array = new int[input.length][input.length];

		for (int y = 0; y < input.length; y++) {
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

		for (int x = 0; x < input.length; x++) {
			result = Math.max(result, array[input.length - 1][x]);
		}

		System.out.println("Result: " + result);
	}
}