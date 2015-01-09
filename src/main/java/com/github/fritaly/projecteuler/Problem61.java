package com.github.fritaly.projecteuler;

import static com.github.fritaly.projecteuler.Utils.createHeptagonalNumberGenerator;
import static com.github.fritaly.projecteuler.Utils.createHexagonalNumberGenerator;
import static com.github.fritaly.projecteuler.Utils.createOctagonalNumberGenerator;
import static com.github.fritaly.projecteuler.Utils.createPentagonalNumberGenerator;
import static com.github.fritaly.projecteuler.Utils.createSquareNumberGenerator;
import static com.github.fritaly.projecteuler.Utils.createTriangleNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Cyclical figurate numbers".
 *
 * @author francois_ritaly
 */
public class Problem61 {

	private static enum Type {
		TRIANGLE, SQUARE, PENTAGONAL, HEXAGONAL, HEPTAGONAL, OCTAGONAL;
	}

	private static class TypedNumber implements Comparable<TypedNumber> {
		final long number;

		final Type type;

		public TypedNumber(long number, Type type) {
			this.number = number;
			this.type = type;
		}

		@Override
		public String toString() {
			return String.format("%d (%s)", number, type.name());
		}

		@Override
		public int compareTo(TypedNumber other) {
			if (this.number == other.number) {
				return this.type.compareTo(other.type);
			}

			return (this.number < other.number) ? -1 : (this.number > other.number) ? +1 : 0;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj instanceof TypedNumber) {
				final TypedNumber other = (TypedNumber) obj;

				return (this.number == other.number) && (this.type == other.type);
			}

			return false;
		}
	}

	private static Set<TypedNumber> extract(NumberGenerator generator, Type type) {
		final Set<TypedNumber> set = new TreeSet<>();

		while (true) {
			final long number = generator.next();

			if (number >= 10000) {
				break;
			}
			if ((1000 < number)) {
				set.add(new TypedNumber(number, type));
			}
		}

		return set;
	}

	private static boolean isChainValid(List<TypedNumber> chain) {
		final Set<Type> types = new TreeSet<>();

		for (int i = 0; i < chain.size() - 1; i++) {
			final TypedNumber number1 = chain.get(i);
			final TypedNumber number2 = chain.get(i + 1);

			types.add(number1.type);
			types.add(number2.type);

			final String n1 = Long.toString(number1.number);
			final String n2 = Long.toString(number2.number);

			if (!n1.endsWith(n2.substring(0, 2))) {
				return false;
			}
		}

		final String first = Long.toString(chain.get(0).number);
		final String last = Long.toString(chain.get(chain.size() - 1).number);

		return (types.size() == 6) && last.endsWith(first.substring(0, 2));
	}

	private static List<TypedNumber> solve(List<TypedNumber> chain, Set<TypedNumber> pool) {
		if (chain.size() == 6) {
			return isChainValid(chain) ? chain : null;
		}

		for (TypedNumber candidate : getCandidates(chain, pool)) {
			final List<TypedNumber> newList = new ArrayList<>(chain);
			newList.add(candidate);

			final List<TypedNumber> solution = solve(newList, pool);

			if (solution != null) {
				return solution;
			}
		}

		return isChainValid(chain) ? chain : null;
	}

	private static List<TypedNumber> getCandidates(List<TypedNumber> chain, Set<TypedNumber> pool) {
		if (chain.isEmpty()) {
			return new ArrayList<>(pool);
		}

		final Set<Type> types = new TreeSet<>();

		for (TypedNumber number : chain) {
			types.add(number.type);
		}

		final String lastNumber = Long.toString(chain.get(chain.size() - 1).number);

		final List<TypedNumber> candidates = new ArrayList<>();

		for (TypedNumber candidate : pool) {
			final String text = Long.toString(candidate.number);
			final Type type = candidate.type;

			if (!types.contains(type) && lastNumber.endsWith(text.substring(0, 2))) {
				candidates.add(candidate);
			}
		}

		return candidates;
	}

	public static void main(String[] args) {
		final Set<TypedNumber> allNumbers = new TreeSet<>();
		allNumbers.addAll(extract(createTriangleNumberGenerator(), Type.TRIANGLE));
		allNumbers.addAll(extract(createSquareNumberGenerator(), Type.SQUARE));
		allNumbers.addAll(extract(createPentagonalNumberGenerator(), Type.PENTAGONAL));
		allNumbers.addAll(extract(createHeptagonalNumberGenerator(), Type.HEPTAGONAL));
		allNumbers.addAll(extract(createHexagonalNumberGenerator(), Type.HEXAGONAL));
		allNumbers.addAll(extract(createOctagonalNumberGenerator(), Type.OCTAGONAL));

		final List<TypedNumber> solution = solve(new ArrayList<TypedNumber>(), allNumbers);

		System.out.println("Solution: " + solution);

		long sum = 0;

		for (TypedNumber number : solution) {
			sum += number.number;
		}

		System.out.println("Result: " + sum);
	}
}