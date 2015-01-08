package com.github.fritaly.projecteuler;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Problem "Path sum: three ways".
 *
 * @author francois_ritaly
 */
public class Problem82 {

	private static final boolean DEBUG = false;

	private static class Cell {
		final int x, y;

		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return String.format("(%d,%d)", x, y);
		}
	}

	private static void render(long[][] table) {
		final StringBuilder builder = new StringBuilder();

		for (int y = 0; y < table.length; y++) {
			for (int x = 0; x < table.length; x++) {
				builder.append(String.format("%6d ", table[y][x] == Long.MAX_VALUE ? -1 : table[y][x]));
			}

			builder.append("\n");
		}

		System.out.println(builder.toString());
	}

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(Problem82.class.getResourceAsStream("p082_matrix.txt"));

		final int size = 80;
		final long[][] table = new long[size][size];
		final long[][] matrix = new long[size][size];

		int y = 0;

		while (scanner.hasNextLine()) {
			final String[] data = scanner.nextLine().split(",");

			for (int x = 0; x < data.length; x++) {
				matrix[y][x] = Integer.parseInt(data[x]);
				table[y][x] = Long.MAX_VALUE;
			}

			y++;
		}

		final LinkedList<Cell> queue = new LinkedList<>();

		for (int i = 0; i < size; i++) {
			queue.add(new Cell(0, i));
		}

		while (!queue.isEmpty()) {
			if (DEBUG) {
				System.out.println("Queue: " + queue);

				render(table);
			}

			final Cell cell = queue.removeFirst();

			if (cell.x == 0) {
				// Left column, only inspect the cell on the right
				table[cell.y][cell.x] = matrix[cell.y][cell.x];

				if (cell.x + 1 < size) {
					// Cell on the right
					queue.add(new Cell(cell.x + 1, cell.y));
				}
			} else {
				final long left = (cell.x > 0) ? table[cell.y][cell.x - 1] : Long.MAX_VALUE;
				final long top = (cell.y > 0) ? table[cell.y - 1][cell.x] : Long.MAX_VALUE;
				final long bottom = (cell.y < size - 1) ? table[cell.y + 1][cell.x] : Long.MAX_VALUE;

				final long min = Utils.min(left, top, bottom) + matrix[cell.y][cell.x];

				if (table[cell.y][cell.x] != min) {
					table[cell.y][cell.x] = min;

					if (cell.x + 1 < size) {
						// Cell on the right
						queue.add(new Cell(cell.x + 1, cell.y));
					}
					if (cell.y + 1 < size) {
						// Cell below
						queue.add(new Cell(cell.x, cell.y + 1));
					}
					if (cell.y > 0) {
						// Cell above
						queue.add(new Cell(cell.x, cell.y - 1));
					}
				}
			}
		}

		scanner.close();

		long min = Long.MAX_VALUE;

		for (int n = 0; n < size; n++) {
			min = Math.min(min, table[n][size - 1]);
		}

		System.out.println("Result: " + min);
	}
}