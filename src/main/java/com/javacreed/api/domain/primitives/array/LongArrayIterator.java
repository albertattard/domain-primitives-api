package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.annotation.concurrent.NotThreadSafe;

import com.google.common.base.Preconditions;

@NotThreadSafe
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
  public Long next() throws NoSuchElementException {
    return nextLong();
  }

  public long nextLong() throws NoSuchElementException {
    if (index == data.length) {
      throw new NoSuchElementException("No more elements are available");
    }

    return data[index++];
  }
}
