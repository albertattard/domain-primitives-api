package com.javacreed.api.domain.primitives.array;

import java.util.Arrays;
import java.util.Iterator;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ReadOnlyFloatArray implements Iterable<Float> {

  public static ReadOnlyFloatArray of(final float[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new ReadOnlyFloatArray(Arrays.copyOf(data, data.length));
  }

  private final float[] data;
  /* Compute the hash code when requested */
  private transient int lazyHashCode;
  private transient boolean lazyHashCodeComputed = false;

  private ReadOnlyFloatArray(final float[] data) {
    this.data = data;
  }

  /**
   * Returns a copy array every time this method is called. This is a defensive copy in order to protect the array
   * enclosed by this class. Any changes made to the returned array do not have any effects on the array enclosed by
   * this class.
   *
   * @return a copy of the array enclosed by this class
   */
  public float[] copyArray() {
    return Arrays.copyOf(data, data.length);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    final ReadOnlyFloatArray other = (ReadOnlyFloatArray) object;
    return Arrays.equals(data, other.data);
  }

  @Override
  public int hashCode() {
    if (false == lazyHashCodeComputed) {
      lazyHashCode = Arrays.hashCode(data);
      lazyHashCodeComputed = true;
    }
    return lazyHashCode;
  }

  @Override
  public Iterator<Float> iterator() {
    return FloatArrayIterator.create(data);
  }

  public int length() {
    return data.length;
  }

  public boolean sameAs(final float[] other) throws NullPointerException {
    Preconditions.checkNotNull(other);
    return Arrays.equals(data, other);
  }

  public float valueAt(final int index) {
    return data[index];
  }
}
