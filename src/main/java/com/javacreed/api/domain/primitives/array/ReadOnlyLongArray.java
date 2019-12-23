package com.javacreed.api.domain.primitives.array;

import java.util.Arrays;
import java.util.Iterator;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ReadOnlyLongArray implements Iterable<Long> {

  private static final ReadOnlyLongArray EMPTY = new ReadOnlyLongArray(new long[0]);

  /**
   * Returns the empty array. This is a shared instance and the same empty array is always returned.
   *
   * @return the empty array
   */
  public static ReadOnlyLongArray empty() {
    return ReadOnlyLongArray.EMPTY;
  }

  public static ReadOnlyLongArray of(final long[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);

    if (data.length == 0) {
      return ReadOnlyLongArray.empty();
    }

    return new ReadOnlyLongArray(Arrays.copyOf(data, data.length));
  }

  private final long[] data;

  /* Compute the hash code when requested */
  private transient int lazyHashCode;
  private transient boolean lazyHashCodeComputed = false;

  private ReadOnlyLongArray(final long[] data) {
    this.data = data;
  }

  /**
   * Returns a copy array every time this method is called. This is a defensive copy in order to protect the array
   * enclosed by this class. Any changes made to the returned array do not have any effects on the array enclosed by
   * this class.
   *
   * @return a copy of the array enclosed by this class
   */
  public long[] copyArray() {
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

  public boolean sameAs(final long[] other) throws NullPointerException {
    Preconditions.checkNotNull(other);
    return Arrays.equals(data, other);
  }

  public long valueAt(final int index) {
    return data[index];
  }

}
