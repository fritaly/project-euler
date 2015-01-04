package com.github.fritaly.projecteuler;

import java.io.IOException;

/**
 * Problem "Coin sums".
 *
 * @author francois_ritaly
 */
public class Problem31 {

	public static void main(String[] args) throws IOException {
		int amount = 200;
		int count = 0;

		for (int a = 0; amount - (a * 200) >= 0; a++) {
			final int remainder = amount - (a * 200);

			for (int b = 0; remainder - (b * 100) >= 0; b++) {
				final int remainder2 = remainder - (b * 100);

				for (int c = 0; remainder2 - (c * 50) >= 0; c++) {
					final int remainder3 = remainder2 - (c * 50);

					for (int d = 0; remainder3 - (d * 20) >= 0; d++) {
						final int remainder4 = remainder3 - (d * 20);

						for (int e = 0; remainder4 - (e * 10) >= 0; e++) {
							final int remainder5 = remainder4 - (e * 10);

							for (int f = 0; remainder5 - (f * 5) >= 0; f++) {
								final int remainder6 = remainder5 - (f * 5);

								for (int g = 0; remainder6 - (g * 2) >= 0; g++) {
									final int remainder7 = remainder6 - (g * 2);

									for (int h = 0; remainder7 - (h * 1) >= 0; h++) {
										final int remainder8 = remainder7 - (h * 1);

										if (remainder8 == 0) {
											count++;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		System.out.println("Result: " + count);
	}
}