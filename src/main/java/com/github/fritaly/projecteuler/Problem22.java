package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Problem "Names scores".
 *
 * @author francois_ritaly
 */
public class Problem22 {
	private static List<String> getNames() throws IOException {
		InputStreamReader reader = null;
		LineNumberReader lineNumberReader = null;

		try {
			reader = new InputStreamReader(Problem22.class.getResourceAsStream("p022_names.txt"));
			lineNumberReader = new LineNumberReader(reader);

			return Arrays.asList(lineNumberReader.readLine().replaceAll("\"", "").split(","));
		} finally {
			lineNumberReader.close();
			reader.close();
		}
	}

	private static int getScore(String name) {
		int score = 0;

		for (int i = 0; i < name.length(); i++) {
			score += name.charAt(i) - 'A' + 1;
		}

		return score;
	}

	public static void main(String[] args) throws IOException {
		final List<String> names = new ArrayList<>(getNames());

		Collections.sort(names);

		System.out.println("Read " + names.size() + " name(s)");

		long result = 0;

		for (String name : names) {
			result += getScore(name) * (names.indexOf(name) + 1);
		}

		System.out.println("Result: " + result);
	}
}