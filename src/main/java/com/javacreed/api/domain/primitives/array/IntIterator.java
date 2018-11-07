package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;

import com.google.common.base.Preconditions;

public class IntIterator implements Iterator<Integer> {

  public static IntIterator create(final int[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new IntIterator(data);
  }

  private final int[] data;
  private int index;

  private IntIterator(final int[] data) {
    this.data = data;
  }

  @Override
  public boolean hasNext() {
    return index < data.length;
  }

  @Override
  public Integer next() {
    return nextInt();
  }

  public int nextInt() {
    return data[index++];
  }
}
