package com.github.fritaly.projecteuler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Problem "Distinct primes factors".
 *
 * @author francois_ritaly
 */
public class Problem47 {

	private static List<Long> combine(List<Long> list) {
		final Map<Long, AtomicInteger> map = new LinkedHashMap<>();

		for (Long number : list) {
			if (!map.containsKey(number)) {
				map.put(number, new AtomicInteger());
			}

			map.get(number).incrementAndGet();
		}

		final List<Long> result = new ArrayList<>();

		for (Map.Entry<Long, AtomicInteger> entry : map.entrySet()) {
			result.add((long) Math.pow(entry.getKey(), entry.getValue().get()));
		}

		return result;
	}

	public static void main(String[] args) {
		final int limit = 200 * 1000;

		final Set<Long> primes = Utils.getPrimeNumbers(limit);

		// Map containing the prime factors per composite number
		final Map<Long, List<Long>> composites = new LinkedHashMap<>();

		for (long i = 2; i < limit; i++) {
			if (primes.contains(i)) {
				continue;
			}

			final List<Long> factors = new ArrayList<>();

			long current = i;
			final int ceiling = (int) Math.sqrt(current);

			for (Long primeNumber : primes) {
				if (primeNumber > ceiling) {
					break;
				}

				while (current % primeNumber == 0) {
					factors.add(primeNumber);

					current /= primeNumber;
				}

				if (primes.contains(current)) {
					factors.add(new Long(current));
					break;
				}
			}

			final List<Long> combined = combine(factors);

			// We're only interested in the composite numbers with exactly 4 distinct prime factors
			if (combined.size() == 4) {
				composites.put(i, combined);

				// System.out.println(String.format("%d = %s", i, combined));
			}
		}

		for (Long n : composites.keySet()) {
			final long p = (n + 1), q = (n + 2), r = (n + 3);

			if (!composites.containsKey(p) || !composites.containsKey(q) || !composites.containsKey(r)) {
				continue;
			}

			// Identify the number's factors
			final List<Long> factors1 = composites.get(n);
			final List<Long> factors2 = composites.get(p);
			final List<Long> factors3 = composites.get(q);
			final List<Long> factors4 = composites.get(r);

			final Set<Long> set = new TreeSet<>();
			set.addAll(factors1);
			set.addAll(factors2);
			set.addAll(factors3);
			set.addAll(factors4);

			if (set.size() == 4 * 4) {
				System.out.println(String.format("%d = %s", n, factors1));
				System.out.println(String.format("%d = %s", p, factors2));
				System.out.println(String.format("%d = %s", q, factors3));
				System.out.println(String.format("%d = %s", r, factors4));

				System.out.println("Result: " + n);
				break;
			}
		}
	}
}