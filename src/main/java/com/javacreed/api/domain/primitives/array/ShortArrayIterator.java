package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.annotation.concurrent.NotThreadSafe;

import com.google.common.base.Preconditions;

@NotThreadSafe
public class ShortArrayIterator implements Iterator<Short> {

  public static ShortArrayIterator create(final short[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new ShortArrayIterator(data);
  }

  private final short[] data;
  private int index;

  private ShortArrayIterator(final short[] data) {
    this.data = data;
  }

  @Override
  public boolean hasNext() {
    return index < data.length;
  }

  @Override
  public Short next() throws NoSuchElementException {
    return nextShort();
  }

  public short nextShort() throws NoSuchElementException {
    if (index == data.length) {
      throw new NoSuchElementException("No more elements are available");
    }

    return data[index++];
  }
}
