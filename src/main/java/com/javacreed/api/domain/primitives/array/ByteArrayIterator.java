package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.annotation.concurrent.NotThreadSafe;

import com.google.common.base.Preconditions;

@NotThreadSafe
public class ByteArrayIterator implements Iterator<Byte> {

  public static ByteArrayIterator create(final byte[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new ByteArrayIterator(data);
  }

  private final byte[] data;
  private int index;

  private ByteArrayIterator(final byte[] data) {
    this.data = data;
  }

  @Override
  public boolean hasNext() {
    return index < data.length;
  }

  @Override
  public Byte next() throws NoSuchElementException {
    return nextByte();
  }

  public byte nextByte() throws NoSuchElementException {
    if (index == data.length) {
      throw new NoSuchElementException("No more elements are available");
    }

    return data[index++];
  }
}
