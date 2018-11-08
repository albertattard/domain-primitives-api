package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;

import com.google.common.base.Preconditions;

public class IntArrayIterator implements Iterator<Integer> {

  public static IntArrayIterator create(final int[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new IntArrayIterator(data);
  }

  private final int[] data;
  private int index;

  private IntArrayIterator(final int[] data) {
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
