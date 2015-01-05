package com.github.fritaly.projecteuler;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Problem "Path sum: two ways".
 *
 * @author francois_ritaly
 */
public class Problem81 {

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

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(Problem81.class.getResourceAsStream("p081_matrix.txt"));

		final int size = 80;
		final long[][] table = new long[size][size];
		final long[][] matrix = new long[size][size];

		int y = 0;

		while (scanner.hasNextLine()) {
			final String[] data = scanner.nextLine().split(",");

			for (int x = 0; x < data.length; x++) {
				matrix[y][x] = Integer.parseInt(data[x]);
				table[y][x] = -1;
			}

			y++;
		}

		final LinkedList<Cell> queue = new LinkedList<>();
		queue.add(new Cell(0, 0));

		while (!queue.isEmpty()) {
			final Cell cell = queue.removeFirst();

			if ((cell.x == 0) && (cell.y == 0)) {
				table[cell.y][cell.x] = matrix[cell.y][cell.x];

				if (cell.x + 1 < size) {
					queue.add(new Cell(cell.x + 1, cell.y));
				}
				if (cell.y + 1 < size) {
					queue.add(new Cell(cell.x, cell.y + 1));
				}
			} else {
				final long left = (cell.x > 0) ? table[cell.y][cell.x - 1] : Long.MAX_VALUE;
				final long top = (cell.y > 0) ? table[cell.y - 1][cell.x] : Long.MAX_VALUE;

				if (table[cell.y][cell.x] == -1) {
					table[cell.y][cell.x] = Math.min(left, top) + matrix[cell.y][cell.x];

					if (cell.x + 1 < size) {
						queue.add(new Cell(cell.x + 1, cell.y));
					}
					if (cell.y + 1 < size) {
						queue.add(new Cell(cell.x, cell.y + 1));
					}
				}
			}
		}

		scanner.close();

		System.out.println("Result: " + table[size - 1][size - 1]);
	}
}