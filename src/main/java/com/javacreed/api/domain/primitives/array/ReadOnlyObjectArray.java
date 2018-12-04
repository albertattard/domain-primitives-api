package com.javacreed.api.domain.primitives.array;

import java.util.Arrays;
import java.util.Iterator;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ReadOnlyObjectArray<T> implements Iterable<T> {

  public static <E> ReadOnlyObjectArray<E> of(final E[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new ReadOnlyObjectArray<>(Arrays.copyOf(data, data.length));
  }

  private final T[] data;
  /* Compute the hash code when requested */
  private transient int lazyHashCode;
  private transient boolean lazyHashCodeComputed = false;

  private ReadOnlyObjectArray(final T[] data) {
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

    @SuppressWarnings("rawtypes")
    final ReadOnlyObjectArray other = (ReadOnlyObjectArray) object;
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
  public Iterator<T> iterator() {
    return ObjectArrayIterator.create(data);
  }

  public int length() {
    return data.length;
  }

  public boolean sameAs(final T[] other) throws NullPointerException {
    Preconditions.checkNotNull(other);
    return Arrays.equals(data, other);
  }

  public T valueAt(final int index) {
    return data[index];
  }
}
