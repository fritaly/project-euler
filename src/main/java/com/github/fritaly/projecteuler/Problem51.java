package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Problem "Prime digit replacements".
 *
 * @author francois_ritaly
 */
public class Problem51 {

	private static int countMatches(String text, char c) {
		int count = 0;

		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == c) {
				count++;
			}
		}

		return count;
	}

	private static List<Integer> indicesOf(String text, int startIndex, char c) {
		final List<Integer> indices = new ArrayList<>();

		for (int i = startIndex; i < text.length(); i++) {
			if (text.charAt(i) == c) {
				indices.add(i);
			}
		}

		return indices;
	}

	private static String replace(String text, int index, char c) {
		return new StringBuilder(text.length()).append(text, 0, index).append(c).append(text, index + 1, text.length()).toString();
	}

	private static Set<String> getPatterns(String text, char c) {
		return getPatterns(text, 0, c);
	}

	private static Set<String> getPatterns(String text, int startIndex, char c) {
		final List<Integer> indices = indicesOf(text, startIndex, c);

		if (indices.isEmpty()) {
			return Collections.singleton(text);
		}

		final Set<String> patterns = new TreeSet<>();

		for (int i = 0; i < indices.size(); i++) {
			// Generate the patterns where the matched character isn't replaced
			patterns.addAll(getPatterns(text, startIndex + 1, c));

			// ... and those where the character is replaced by a '*'
			patterns.addAll(getPatterns(replace(text, indices.get(i), '*'), startIndex + 1, c));
		}

		return patterns;
	}

	public static void main(String[] args) {
		final Iterator<Long> primes = Utils.getPrimeNumbers(1000000).iterator();

		final Map<String, Set<Integer>> map = new TreeMap<>();

		while (true) {
			final int prime = primes.next().intValue();
			final String text = Integer.toString(prime);

			for (char c = '0'; c <= '9'; c++) {
				final int matches = countMatches(text, c);

				if (matches > 0) {
					final Set<String> patterns = getPatterns(text, c);

					// Ignore the pattern containing no star
					patterns.remove(text);

					for (String pattern : patterns) {
						if (!map.containsKey(pattern)) {
							map.put(pattern, new TreeSet<Integer>());
						}

						final Set<Integer> family = map.get(pattern);

						family.add(prime);

						// System.out.println(text + " -> " + pattern + " -> " + map.get(pattern));

						if (family.size() == 8) {
							System.out.println("Result: " + Collections.min(family) + " " + pattern + " -> " + family);
							return;
						}
					}

					// System.out.println(prime + " -> " + patterns);
				}
			}

			if (!primes.hasNext()) {
				break;
			}
		}
	}
}