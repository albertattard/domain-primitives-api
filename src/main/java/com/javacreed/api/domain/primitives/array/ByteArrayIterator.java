package com.javacreed.api.domain.primitives.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.annotation.concurrent.NotThreadSafe;

import com.google.common.base.Preconditions;

/**
 * A basic implementation of an iterator that supports primitive values
 *
 * @author Albert Attard
 */
@NotThreadSafe
public class ByteArrayIterator implements Iterator<Byte> {

  /**
   * Creates an iterator based on the given array. Note that the given array is not copied, thus any changes to the
   * array's content are shared by the iterator. This iterator does not modify the array's content and does not support
   * the {@link #remove()} operation.
   *
   * @param data
   *          the array based on which the iterator is created (which cannot be {@code null})
   * @return an instance of the iterator
   * @throws NullPointerException
   *           if the given parameter is {@code null}
   */
  public static ByteArrayIterator create(final byte[] data) {
    Preconditions.checkNotNull(data);
    return new ByteArrayIterator(data);
  }

  private final byte[] data;
  private int index;

  private ByteArrayIterator(final byte[] data) {
    this.data = data;
  }

  @Override
  public boolean hasNext() {
    return index < data.length;
  }

  @Override
  public Byte next() {
    return nextByte();
  }

  /**
   * Returns the next byte value if one is available, otherwise throws a {@link NoSuchElementException}. This method
   * should be called after first verifying the iterator's availability through the {@link #hasNext()} method.
   *
   * <pre>
   * final ByteArrayIterator iterator = ByteArrayIterator.create(new byte[] { 1, 2, 3, 4 });
   * while (iterator.hasNext()) {
   *   final byte b = nextByte();
   * }
   * </pre>
   *
   * @return the next byte value
   * @throws NoSuchElementException
   *           if no more elements are available
   * @see {@link #hasNext()}
   */
  public byte nextByte() {
    if (index == data.length) {
      throw new NoSuchElementException("No more elements are available");
    }

    return data[index++];
  }
}
