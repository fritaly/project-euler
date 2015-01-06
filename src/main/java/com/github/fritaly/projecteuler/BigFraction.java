package com.github.fritaly.projecteuler;

import java.math.BigInteger;

public final class BigFraction implements Comparable<BigFraction> {

	private final BigInteger numerator, denominator;

	public BigFraction(long numerator) {
		this(BigInteger.valueOf(numerator), BigInteger.ONE);
	}

	public BigFraction(long numerator, long denominator) {
		this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
	}

	public BigFraction(BigInteger numerator, BigInteger denominator) {
		if (BigInteger.ZERO.equals(denominator)) {
			throw new IllegalArgumentException("The denominator can't be zero");
		}

		// Reduce the fraction upon creation
		final BigInteger gcd = numerator.gcd(denominator);

		if (denominator.signum() == -1) {
			// Ensure the denominator is always positive
			denominator = denominator.negate();
			numerator = numerator.negate();
		}

		this.numerator = numerator.divide(gcd);
		this.denominator = denominator.divide(gcd);
	}

	public BigInteger getDenominator() {
		return denominator;
	}

	public BigInteger getNumerator() {
		return numerator;
	}

	public BigFraction multiply(BigFraction other) {
		if (other == null) {
			throw new IllegalArgumentException("The given fraction is null");
		}

		return new BigFraction(this.numerator.multiply(other.numerator), this.denominator.multiply(other.denominator));
	}

	// lcm = "lowest common multiple"
	private static BigInteger lcm(BigInteger a, BigInteger b) {
		return b.multiply(a).divide(a.gcd(b));
	}

	public BigFraction add(BigFraction other) {
		if (other == null) {
			throw new IllegalArgumentException("The given fraction is null");
		}

		final BigInteger lcm = lcm(this.denominator, other.denominator);

		final BigInteger a = lcm.divide(this.denominator).multiply(this.numerator);
		final BigInteger b = lcm.divide(other.denominator).multiply(other.numerator);

		return new BigFraction(a.add(b), lcm);
	}

	public BigFraction reciprocal() {
		// The constructor will raise an IllegalArgumentException if the
		// numerator is 0
		return new BigFraction(denominator, numerator);
	}

	public BigFraction opposite() {
		// The denominator should never be negative
		return new BigFraction(numerator.negate(), denominator);
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

		hash = (hash * 11) + numerator.hashCode();
		hash = (hash * 11) + denominator.hashCode();

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
		if (obj instanceof BigFraction) {
			final BigFraction other = (BigFraction) obj;

			// The fractions are reduced upon instantiation
			return (this.numerator.equals(other.numerator) && this.denominator.equals(other.denominator));
		}

		return false;
	}

	@Override
	public int compareTo(BigFraction other) {
		return this.numerator.multiply(other.denominator).compareTo(other.numerator.multiply(this.denominator));
	}
}