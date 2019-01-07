package com.javacreed.api.domain.primitives.array;

import java.util.Arrays;
import java.util.Iterator;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ReadOnlyShortArray implements Iterable<Short> {

  public static ReadOnlyShortArray of(final short[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new ReadOnlyShortArray(Arrays.copyOf(data, data.length));
  }

  private final short[] data;
  /* Compute the hash code when requested */
  private transient int lazyHashCode;
  private transient boolean lazyHashCodeComputed = false;

  private ReadOnlyShortArray(final short[] data) {
    this.data = data;
  }

  /**
   * Returns a copy array every time this method is called. This is a defensive copy in order to protect the array
   * enclosed by this class. Any changes made to the returned array do not have any effects on the array enclosed by
   * this class.
   *
   * @return a copy of the array enclosed by this class
   */
  public short[] copyArray() {
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

    final ReadOnlyShortArray other = (ReadOnlyShortArray) object;
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
  public Iterator<Short> iterator() {
    return ShortArrayIterator.create(data);
  }

  public int length() {
    return data.length;
  }

  public boolean sameAs(final short[] other) throws NullPointerException {
    Preconditions.checkNotNull(other);
    return Arrays.equals(data, other);
  }

  public short valueAt(final int index) {
    return data[index];
  }
}
