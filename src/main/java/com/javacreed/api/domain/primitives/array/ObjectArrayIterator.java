package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;

import com.google.common.base.Preconditions;

public class ObjectArrayIterator<T> implements Iterator<T> {

  public static <E> ObjectArrayIterator create(final E[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new ObjectArrayIterator(data);
  }

  private final T[] data;
  private int index;

  private ObjectArrayIterator(final T[] data) {
    this.data = data;
  }

  @Override
  public boolean hasNext() {
    return index < data.length;
  }

  @Override
  public T next() {
    return data[index++];
  }
}
