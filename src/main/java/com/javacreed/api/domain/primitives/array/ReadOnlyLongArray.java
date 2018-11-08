package com.javacreed.api.domain.primitives.array;

import java.util.Arrays;
import java.util.Iterator;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ReadOnlyLongArray implements Iterable<Long> {

  public static ReadOnlyLongArray of(final long[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new ReadOnlyLongArray(Arrays.copyOf(data, data.length));
  }

  private final long[] data;
  /* Compute the hash code when requested */
  private transient int lazyHashCode;
  private transient boolean lazyHashCodeComputed = false;

  private ReadOnlyLongArray(final long[] data) {
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

    final ReadOnlyLongArray other = (ReadOnlyLongArray) object;
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
  public Iterator<Long> iterator() {
    return LongArrayIterator.create(data);
  }

  public int length() {
    return data.length;
  }

  public long valueAt(final int index) {
    return data[index];
  }

}
