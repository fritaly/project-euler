package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Coded triangle numbers".
 *
 * @author francois_ritaly
 */
public class Problem42 {
	private static List<String> getWords() throws IOException {
		InputStreamReader reader = null;
		LineNumberReader lineNumberReader = null;

		try {
			reader = new InputStreamReader(Problem42.class.getResourceAsStream("p042_words.txt"));
			lineNumberReader = new LineNumberReader(reader);

			return Arrays.asList(lineNumberReader.readLine().replaceAll("\"", "").split(","));
		} finally {
			lineNumberReader.close();
			reader.close();
		}
	}

	private static int getWordValue(String word) {
		int sum = 0;

		for (char c : word.toCharArray()) {
			sum += c - 'A' + 1;
		}

		return sum;
	}

	public static void main(String[] args) throws IOException {
		final List<String> words = new ArrayList<>(getWords());

		System.out.println("Read " + words.size() + " word(s)");

		// Compute all the word values
		final List<Integer> wordValues = new ArrayList<>();

		for (String word : words) {
			wordValues.add(getWordValue(word));
		}

		System.out.println("Word values: " + wordValues.size());

		// Identify the max word value
		final int maxValue = Collections.max(wordValues);

		System.out.println("Max value: " + maxValue);

		// Compute triangle terms up to the max value
		final Set<Integer> terms = new TreeSet<>();

		int n = 1;

		while (true) {
			final int term = n * (n + 1) / 2;

			if (term > maxValue) {
				break;
			}

			terms.add(term);

			n++;
		}

		System.out.println("Computed " + terms.size() + " terms");

		int count = 0;

		for (Integer wordValue : wordValues) {
			if (terms.contains(wordValue)) {
				count++;
			}
		}

		System.out.println("Result: " + count);
	}
}