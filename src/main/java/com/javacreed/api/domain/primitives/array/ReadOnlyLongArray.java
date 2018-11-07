package com.javacreed.api.domain.primitives.array;

import java.util.Arrays;

import com.google.common.base.Preconditions;

public class ReadOnlyLongArray {

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

  public int length() {
    return data.length;
  }

}
