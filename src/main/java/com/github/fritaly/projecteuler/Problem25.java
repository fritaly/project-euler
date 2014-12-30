package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Problem "1000-digit Fibonacci number".
 *
 * @author francois_ritaly
 */
public class Problem25 {
	public static void main(String[] args) throws IOException {
		BigInteger a = BigInteger.ONE;
		BigInteger b = BigInteger.ONE;
		BigInteger c = null;

		int count = 3;

		while (true) {
			c = a.add(b);

			if (c.toString().length() >= 1000) {
				break;
			}

			a = b;
			b = c;
			count++;
		}

		System.out.println("Result: " + count);
	}
}