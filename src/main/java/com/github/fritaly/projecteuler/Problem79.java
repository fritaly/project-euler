package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Passcode derivation".
 *
 * @author francois_ritaly
 */
public class Problem79 {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(Problem79.class.getResourceAsStream("p079_keylog.txt"));

		final List<String> codes = new ArrayList<>();

		while (scanner.hasNextLine()) {
			codes.add(scanner.nextLine());
		}

		scanner.close();

		// Identify all the digits present in the codes
		final Set<Character> allDigits = new TreeSet<>();

		for (String code : codes) {
			for (char c : code.toCharArray()) {
				allDigits.add(c);
			}
		}

		System.out.println("Digits: " + allDigits);

		final List<Character> list = new ArrayList<>(allDigits);

		// Sort the digits according to the successful login attempts
		Collections.sort(list, new Comparator<Character>() {
			@Override
			public int compare(Character c1, Character c2) {
				for (String code : codes) {
					if (code.contains(c1.toString()) && code.contains(c2.toString())) {
						return code.indexOf(c1) - code.indexOf(c2);
					}
				}

				throw new RuntimeException("Unable to compare the 2 characters");
			}
		});

		final StringBuilder buffer = new StringBuilder();

		for (Character character : list) {
			buffer.append(character);
		}

		System.out.println("Result: " + buffer);
	}
}