package com.github.fritaly.projecteuler;

import java.util.Iterator;

public abstract class NumberGenerator implements Iterator<Long> {

	private long n = 1;

	public NumberGenerator() {
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public final Long next() {
		return generate(n++);
	}

	protected abstract long generate(long n);

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}