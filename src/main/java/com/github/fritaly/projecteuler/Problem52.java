package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.util.Arrays;

/**
 * Problem "Permuted multiples".
 *
 * @author francois_ritaly
 */
public class Problem52 {

	private static String sort(String string) {
		if ((string == null) || "".equals(string)) {
			return string;
		}

		final char[] chars = string.toCharArray();

		Arrays.sort(chars);

		return new String(chars);
	}

	private static boolean isPermutationOf(long number1, long number2) {
		return sort(Long.toString(number1)).equals(sort(Long.toString(number2)));
	}

	public static void main(String[] args) throws IOException {
		for (int n = 1; n < Integer.MAX_VALUE; n++) {
			final int doubleN = n + n;
			final int tripleN = doubleN + n;
			final int quadrupleN = tripleN + n;
			final int quintupleN = quadrupleN + n;
			final int sextupleN = quintupleN + n;

			if (isPermutationOf(doubleN, tripleN) && isPermutationOf(tripleN, quadrupleN)
					&& isPermutationOf(quadrupleN, quintupleN) && isPermutationOf(quintupleN, sextupleN)) {

				System.out.println(n + " / " + doubleN);
			}
		}
	}
}