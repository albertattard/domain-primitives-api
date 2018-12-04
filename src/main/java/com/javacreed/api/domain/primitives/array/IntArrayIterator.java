package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.annotation.concurrent.NotThreadSafe;

import com.google.common.base.Preconditions;

@NotThreadSafe
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
  public Integer next() throws NoSuchElementException {
    return nextInt();
  }

  public int nextInt() throws NoSuchElementException {
    if (index == data.length) {
      throw new NoSuchElementException("No more elements are available");
    }

    return data[index++];
  }
}
