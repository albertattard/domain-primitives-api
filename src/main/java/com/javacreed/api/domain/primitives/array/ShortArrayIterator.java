package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;

import com.google.common.base.Preconditions;

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
  public Short next() {
    return nextShort();
  }

  public short nextShort() {
    return data[index++];
  }
}
