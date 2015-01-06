package com.github.fritaly.projecteuler;

public final class Fraction implements Comparable<Fraction> {

	private final long numerator, denominator;

	public Fraction(long numerator) {
		this(numerator, 1);
	}

	public Fraction(long numerator, long denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("The denominator can't be zero");
		}

		// Reduce the fraction upon creation
		final long gcd = Utils.gcd(numerator, denominator);

		if (denominator < 0) {
			// Ensure the denominator is always positive
			denominator = -denominator;
			numerator = -numerator;
		}

		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
	}

	public float toFloat() {
		return ((float) numerator) / denominator;
	}

	public double toDouble() {
		return ((double) numerator) / denominator;
	}

	public long getDenominator() {
		return denominator;
	}

	public long getNumerator() {
		return numerator;
	}

	public Fraction multiply(Fraction other) {
		if (other == null) {
			throw new IllegalArgumentException("The given fraction is null");
		}

		return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
	}

	// lcm = "lowest common multiple"
	private static long lcm(long a, long b) {
		return (b * a) / (Utils.gcd(a, b));
	}

	public Fraction add(Fraction other) {
		if (other == null) {
			throw new IllegalArgumentException("The given fraction is null");
		}

		final long lcm = lcm(this.denominator, other.denominator);

		final long a = lcm / this.denominator * this.numerator;
		final long b = lcm / other.denominator * other.numerator;

		return new Fraction(a + b, lcm);
	}

	public Fraction reciprocal() {
		// The constructor will raise an IllegalArgumentException if the
		// numerator is 0
		return new Fraction(denominator, numerator);
	}

	public Fraction opposite() {
		// The denominator should never be negative
		return new Fraction(-numerator, denominator);
	}

	public String toText() {
		return String.format("%d/%d", numerator, denominator);
	}

	@Override
	public String toString() {
		return String.format("%d/%d", numerator, denominator);
	}

	@Override
	public int hashCode() {
		int hash = 13;

		hash = (hash * 11) + (int) (numerator >> 31);
		hash = (hash * 11) + (int) (denominator >> 31);

		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof Fraction) {
			final Fraction other = (Fraction) obj;

			// The fractions are reduced upon instantiation
			return (this.numerator == other.numerator) && (this.denominator == other.denominator);
		}

		return false;
	}

	@Override
	public int compareTo(Fraction other) {
		final long a = this.numerator * other.denominator;
		final long b = other.numerator * this.denominator;

		if (a < b) {
			return -1;
		} else if (a > b) {
			return +1;
		}

		return 0;
	}
}