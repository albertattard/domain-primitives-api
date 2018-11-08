package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;

import com.google.common.base.Preconditions;

public class LongArrayIterator implements Iterator<Long> {

  public static LongArrayIterator create(final long[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new LongArrayIterator(data);
  }

  private final long[] data;
  private int index;

  private LongArrayIterator(final long[] data) {
    this.data = data;
  }

  @Override
  public boolean hasNext() {
    return index < data.length;
  }

  @Override
  public Long next() {
    return nextLong();
  }

  public long nextLong() {
    return data[index++];
  }
}
