package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * Problem "XOR decryption".
 *
 * @author francois_ritaly
 */
public class Problem59 {

	private static class Frequencies {

		final Map<Integer, AtomicInteger> frequencies = new TreeMap<Integer, AtomicInteger>();

		Frequencies register(int number) {
			if (!frequencies.containsKey(number)) {
				frequencies.put(number, new AtomicInteger());
			}

			frequencies.get(number).incrementAndGet();

			return this;
		}

		int getKey() {
			// Because the ciphered text is plain english, the most frequent
			// character will be the blank character
			return (getMostFrequentValue() ^ ' ');
		}

		char decrypt(int number) {
			return (char) (getKey() ^ number);
		}

		Integer getMostFrequentValue() {
			Map.Entry<Integer, AtomicInteger> result = null;

			for (Map.Entry<Integer, AtomicInteger> entry : frequencies.entrySet()) {
				if ((result == null) || (result.getValue().intValue() < entry.getValue().intValue())) {
					result = entry;
				}
			}

			return result.getKey();
		}

		@Override
		public String toString() {
			final List<Map.Entry<Integer, AtomicInteger>> entries = new ArrayList<>(frequencies.entrySet());

			Collections.sort(entries, new Comparator<Map.Entry<Integer, AtomicInteger>>() {
				@Override
				public int compare(Entry<Integer, AtomicInteger> e1, Entry<Integer, AtomicInteger> e2) {
					return e2.getValue().intValue() - e1.getValue().intValue();
				}
			});

			return entries.toString();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(Problem59.class.getResourceAsStream("p059_cipher.txt")).useDelimiter(Pattern
				.compile("[,\n]"));

		// The input consists in 1201 characters. The encryption key is only 3
		// character long. We can perform a frequency analysis on 3 sets of 400
		// characters. This should be enough to identify the encryption key
		final Frequencies f1 = new Frequencies();
		final Frequencies f2 = new Frequencies();
		final Frequencies f3 = new Frequencies();

		// Cyclic buffer of frequencies
		final LinkedList<Frequencies> list = new LinkedList<>(Arrays.asList(f1, f2, f3));

		while (scanner.hasNext()) {
			list.addLast(list.removeFirst().register(scanner.nextInt()));
		}

		// Dump the frequencies
		System.out.println("F1: " + f1);
		System.out.println("F2: " + f2);
		System.out.println("F3: " + f3);

		list.clear();
		list.addAll(Arrays.asList(f1, f2, f3));

		scanner = new Scanner(Problem59.class.getResourceAsStream("p059_cipher.txt")).useDelimiter(Pattern
				.compile("[,\n]"));

		final StringBuilder buffer = new StringBuilder();

		long sum = 0;

		while (scanner.hasNext()) {
			final Frequencies frequencies = list.removeFirst();

			final char plain = frequencies.decrypt(scanner.nextInt());

			sum += plain;

			buffer.append(plain);

			list.addLast(frequencies);
		}

		System.out.println("Plain: " + buffer);

		scanner.close();

		System.out.println("Result: " + sum);
	}
}