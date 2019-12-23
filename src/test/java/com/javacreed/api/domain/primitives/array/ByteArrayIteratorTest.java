package com.javacreed.api.domain.primitives.array;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class ByteArrayIteratorTest {

  @Test
  public void shouldReturnTheElementsInTheProperOrder() {
    final byte[] source = new byte[] { 1, 2, 3, 4 };
    final ByteArrayIterator iterator = ByteArrayIterator.create(source);
    for (int i = 0; i < source.length; i++) {
      Assert.assertTrue(iterator.hasNext());
      Assert.assertEquals(source[i], iterator.next().byteValue());
    }
    Assert.assertFalse(iterator.hasNext());
  }

  @Test
  public void shouldThrowNoSuchElementExceptionWhenAccessingMoreElementsThanAvailable() {
    final ByteArrayIterator iterator = ByteArrayIterator.create(new byte[0]);
    Assert.assertFalse(iterator.hasNext());
    Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
  }
}
