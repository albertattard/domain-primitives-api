package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.annotation.concurrent.NotThreadSafe;

import com.google.common.base.Preconditions;

@NotThreadSafe
public class FloatArrayIterator implements Iterator<Float> {

  public static FloatArrayIterator create(final float[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new FloatArrayIterator(data);
  }

  private final float[] data;
  private int index;

  private FloatArrayIterator(final float[] data) {
    this.data = data;
  }

  @Override
  public boolean hasNext() {
    return index < data.length;
  }

  @Override
  public Float next() throws NoSuchElementException {
    return nextFloat();
  }

  public float nextFloat() throws NoSuchElementException {
    if (index == data.length) {
      throw new NoSuchElementException("No more elements are available");
    }

    return data[index++];
  }
}
