package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.annotation.concurrent.NotThreadSafe;

import com.google.common.base.Preconditions;

@NotThreadSafe
public class ObjectArrayIterator<T> implements Iterator<T> {

  public static <E> ObjectArrayIterator<E> create(final E[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new ObjectArrayIterator<>(data);
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
  public T next() throws NoSuchElementException {
    if (index == data.length) {
      throw new NoSuchElementException("No more elements are available");
    }

    return data[index++];
  }
}
