package com.javacreed.api.domain.primitives.array;

import java.util.Arrays;
import java.util.Iterator;

import com.google.common.base.Preconditions;

public class ReadOnlyIntArray implements Iterable<Integer> {

  public static ReadOnlyIntArray of(final int[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new ReadOnlyIntArray(Arrays.copyOf(data, data.length));
  }

  private final int[] data;
  /* Compute the hash code when requested */
  private transient int lazyHashCode;
  private transient boolean lazyHashCodeComputed = false;

  private ReadOnlyIntArray(final int[] data) {
    this.data = data;
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    final ReadOnlyIntArray other = (ReadOnlyIntArray) object;
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
  public Iterator<Integer> iterator() {
    return IntArrayIterator.create(data);
  }

  public int length() {
    return data.length;
  }

  public boolean sameAs(final int[] other) throws NullPointerException {
    Preconditions.checkNotNull(other);
    return Arrays.equals(data, other);
  }

  public int valueAt(final int index) {
    return data[index];
  }
}
