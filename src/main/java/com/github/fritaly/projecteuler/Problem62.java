package com.github.fritaly.projecteuler;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Problem "Cubic permutations".
 *
 * @author francois_ritaly
 */
public class Problem62 {

	public static void main(String[] args) {
		final Map<String, Set<Long>> map = new TreeMap<>();

		final NumberGenerator generator = Utils.createCubeNumberGenerator();

		while (true) {
			final long number = generator.next();

			final String key = Utils.sort(Long.toString(number));

			if (!map.containsKey(key)) {
				map.put(key, new TreeSet<Long>());
			}

			map.get(key).add(number);

			if (map.get(key).size() == 5) {
				System.out.println("Result: " + Collections.min(map.get(key)));
				break;
			}
		}
	}
}