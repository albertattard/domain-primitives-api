package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;

import com.google.common.base.Preconditions;

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
  public Character next() {
    return nextChar();
  }

  public char nextChar() {
    return data[index++];
  }
}
