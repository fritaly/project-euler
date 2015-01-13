package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Problem "Prime summations".
 *
 * @author francois_ritaly
 */
public class Problem77 {

	private static final boolean DEBUG = false;

	private static String format(Stack<Long> stack) {
		final StringBuilder buffer = new StringBuilder();

		for (Long element : stack) {
			if (buffer.length() > 0) {
				buffer.append(" + ");
			}

			buffer.append(element);
		}

		return buffer.toString();
	}

	private static void solve(long remainder, List<Long> primes, AtomicInteger count, Stack<Long> stack) {
		final LinkedList<Long> list = new LinkedList<>(primes);

		while (!list.isEmpty()) {
			final long prime = list.removeFirst();

			if (prime > remainder) {
				continue;
			}

			final Stack<Long> localStack = new Stack<>();
			localStack.addAll(stack);

			for (int j = 1; remainder - j * prime >= 0; j++) {
				final long remainder2 = remainder - j * prime;

				localStack.push(prime);

				if (remainder2 == 0) {
					count.incrementAndGet();

					if (DEBUG) {
						System.out.println("Found: " + format(localStack));
					}
				} else {
					if (!list.isEmpty()) {
						solve(remainder2, list, count, localStack);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		final List<Long> primes = new ArrayList<>(Utils.getPrimeNumbers(1000));

		Collections.reverse(primes);

		for (long i = 2; ; i++) {
			final AtomicInteger count = new AtomicInteger();

			solve(i, primes, count, new Stack<Long>());

			if (count.get() > 5000) {
				System.out.println("Result: " + i + " (" + count.get() + ")");
				break;
			}

			if (DEBUG) {
				System.out.println(i + ": " + count.get());
			}
		}
	}
}