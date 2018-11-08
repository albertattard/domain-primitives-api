package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;

import com.google.common.base.Preconditions;

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
  public Float next() {
    return nextFloat();
  }

  public float nextFloat() {
    return data[index++];
  }
}
