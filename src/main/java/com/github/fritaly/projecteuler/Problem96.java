package com.github.fritaly.projecteuler;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Problem "Su Doku".
 *
 * @author francois_ritaly
 */
public class Problem96 {

	private static final boolean DEBUG = false;

	private static class Cell {
		final int x, y;

		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return String.format("(%d, %d)", x, y);
		}
	}

	private static class Grid {
		final char[][] cells = new char[9][9];

		Grid(Scanner scanner) {
			// Skip the line "Grid xy"
			scanner.nextLine();

			for (int y = 0; y < 9; y++) {
				final String line = scanner.nextLine();

				for (int x = 0; x < 9; x++) {
					final char c = line.charAt(x);

					// Use a dot to denote an empty cell
					this.cells[y][x] = (c == '0') ? '.' : c;
				}
			}
		}

		public Grid(Grid grid) {
			if (grid == null) {
				throw new IllegalArgumentException("The given grid is null");
			}

			for (int y = 0; y < 9; y++) {
				System.arraycopy(grid.cells[y], 0, this.cells[y], 0, 9);
			}
		}

		// Tells whether the grid is complete (not necessarily valid !)
		boolean isComplete() {
			for (int n = 0; n < 81; n++) {
				if (cells[n / 9][n % 9] == '.') {
					return false;
				}
			}

			return true;
		}

		boolean isValid() {
			for (int n = 0; n < 9; n++) {
				if (!isValid(getRow(n)) || !isValid(getColumn(n)) || !isValid(getSquare(n))) {
					return false;
				}
			}

			return true;
		}

		Set<Character> getRow(int y) {
			final Set<Character> set = new TreeSet<>();

			for (char c : cells[y]) {
				if (c != '.') {
					set.add(c);
				}
			}

			return set;
		}

		Set<Character> getColumn(int x) {
			final Set<Character> set = new TreeSet<>();

			for (int y = 0; y < 9; y++) {
				final char c = cells[y][x];

				if (c != '.') {
					set.add(c);
				}
			}

			return set;
		}

		// TODO Simplify this code
		Set<Character> getSquare(int x, int y) {
			return getSquare((y / 3) * 3 + (x / 3));
		}

		Set<Character> getSquare(int n) {
			final Set<Character> set = new TreeSet<>();

			for (int y = 3 * (n / 3); y < 3 * ((n / 3) + 1); y++) {
				for (int x = 3 * (n % 3); x < 3 * ((n % 3) + 1); x++) {
					final char c = cells[y][x];

					if (c != '.') {
						set.add(c);
					}
				}
			}

			return set;
		}

		Set<Character> getCandidates(Set<Character> set) {
			final Set<Character> result = new TreeSet<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
			result.removeAll(set);

			return result;
		}

		// Tells whether the given set is complete and valid
		private boolean isValid(Set<Character> set) {
			if (set.size() != 9) {
				return false;
			}

			for (char c = '1'; c <= '9'; c++) {
				if (!set.contains(c)) {
					return false;
				}
			}

			return true;
		}

		Cell getCellWithLeastCandidates() {
			Cell result = null;
			int candidateCount = Integer.MAX_VALUE;

			for (int y = 0; y < 9; y++) {
				final Set<Character> row = getRow(y);

				for (int x = 0; x < 9; x++) {
					if (cells[y][x] != '.') {
						continue;
					}

					final Set<Character> column = getColumn(x);
					final Set<Character> square = getSquare(x, y);

					final Set<Character> candidates = getCandidates(union(row, column, square));

					if (candidates.size() < candidateCount) {
						candidateCount = candidates.size();
						result = new Cell(x, y);
					}
				}
			}

			return result;
		}

		Set<Character> union(Set<Character> set1, Set<Character> set2, Set<Character> set3) {
			final Set<Character> union = new TreeSet<>(set1);
			union.addAll(set2);
			union.addAll(set3);

			return union;
		}

		boolean solve() {
			while (!isComplete()) {
				boolean update = false;

				for (int y = 0; (y < 9) && !update; y++) {
					final Set<Character> row = getRow(y);

					for (int x = 0; (x < 9) && !update; x++) {
						if (cells[y][x] != '.') {
							continue;
						}

						final Set<Character> column = getColumn(x);
						final Set<Character> square = getSquare(x, y);

						final Set<Character> candidates = getCandidates(union(row, column, square));

						if (candidates.size() == 1) {
							final Character c = candidates.iterator().next();
							cells[y][x] = c;

							if (DEBUG) {
								System.out.println(String.format("Stored %s at (%d,%d)", c, x, y));
							}

							update = true;
							break;
						}
					}
				}

				if (!update) {
					// No obvious update, start guessing
					final Cell cell = getCellWithLeastCandidates();

					final Set<Character> row = getRow(cell.y);
					final Set<Character> column = getColumn(cell.x);
					final Set<Character> square = getSquare(cell.x, cell.y);

					final Set<Character> candidates = getCandidates(union(row, column, square));

					if (candidates.isEmpty()) {
						// No candidate left, the grid is inconsistent (invalid)
						return false;
					}

					if (DEBUG) {
						System.out.println(String.format("---> Candidates: %s at (%d,%d)", candidates, cell.x, cell.y));
						System.out.println(this);
					}

					for (Character candidate : candidates) {
						if (DEBUG) {
							System.out.println(String.format("Testing %s at (%d,%d)", candidate, cell.x, cell.y));
						}

						final Grid clone = new Grid(this);
						clone.cells[cell.y][cell.x] = candidate;

						if (clone.solve()) {
							for (int n = 0; n < 9; n++) {
								System.arraycopy(clone.cells[n], 0, this.cells[n], 0, 9);
							}

							return isValid();
						} else {
							if (DEBUG) {
								System.out.println("Testing failed");
							}
						}
					}

					// All candidates tested, nothing worked
					return false;
				}
			}

			return isValid();
		}

		@Override
		public String toString() {
			final StringBuilder builder = new StringBuilder();

			for (int y = 0; y < 9; y++) {
				if (y % 3 == 0) {
					builder.append("+---+---+---+").append("\n");
				}

				for (int x = 0; x < 9; x++) {
					if (x % 3 == 0) {
						builder.append("|");
					}

					builder.append(cells[y][x]);
				}

				builder.append("|").append("\n");
			}

			builder.append("+---+---+---+").append("\n");
			return builder.toString();
		}

		public int getValue() {
			if (solve()) {
				return ((cells[0][0] - '0') * 100) + ((cells[0][1] - '0') * 10) + (cells[0][2] - '0');
			}

			System.err.println(this);

			throw new UnsupportedOperationException("Unable to resolve grid");
		}
	}

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(Problem81.class.getResourceAsStream("p096_sudoku.txt"));

		int sum = 0;

		while (scanner.hasNext()) {
			final Grid grid = new Grid(scanner);

			System.out.println(grid);

			sum += grid.getValue();

			System.out.println(grid);
		}

		scanner.close();

		System.out.println("Result: " + sum);
	}
}