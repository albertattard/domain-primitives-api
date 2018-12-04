package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.annotation.concurrent.NotThreadSafe;

import com.google.common.base.Preconditions;

@NotThreadSafe
public class CharArrayIterator implements Iterator<Character> {

  public static CharArrayIterator create(final char[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new CharArrayIterator(data);
  }

  private final char[] data;
  private int index;

  private CharArrayIterator(final char[] data) {
    this.data = data;
  }

  @Override
  public boolean hasNext() {
    return index < data.length;
  }

  @Override
  public Character next() throws NoSuchElementException {
    return nextChar();
  }

  public char nextChar() throws NoSuchElementException {
    if (index == data.length) {
      throw new NoSuchElementException("No more elements are available");
    }

    return data[index++];
  }
}
