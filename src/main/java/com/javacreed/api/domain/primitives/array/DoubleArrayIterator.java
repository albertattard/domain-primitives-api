package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.annotation.concurrent.NotThreadSafe;

import com.google.common.base.Preconditions;

@NotThreadSafe
public class DoubleArrayIterator implements Iterator<Double> {

  public static DoubleArrayIterator create(final double[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new DoubleArrayIterator(data);
  }

  private final double[] data;
  private int index;

  private DoubleArrayIterator(final double[] data) {
    this.data = data;
  }

  @Override
  public boolean hasNext() {
    return index < data.length;
  }

  @Override
  public Double next() throws NoSuchElementException {
    return nextDouble();
  }

  public double nextDouble() throws NoSuchElementException {
    if (index == data.length) {
      throw new NoSuchElementException("No more elements are available");
    }

    return data[index++];
  }
}
