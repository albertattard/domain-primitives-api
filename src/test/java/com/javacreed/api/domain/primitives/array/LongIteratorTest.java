package com.javacreed.api.domain.primitives.array;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class LongIteratorTest {

  @Test
  public void shouldReturnTheElementsInTheProperOrder() {
    final long[] source = new long[] { 1, 2, 3, 4 };
    final LongArrayIterator iterator = LongArrayIterator.create(source);
    for (int i = 0; i < source.length; i++) {
      Assert.assertTrue(iterator.hasNext());
      Assert.assertEquals(source[i], iterator.next().longValue());
    }
    Assert.assertFalse(iterator.hasNext());
  }

  @Test
  public void shouldThrowNoSuchElementExceptionWhenAccessingMoreElementsThanAvailable() {
    final LongArrayIterator iterator = LongArrayIterator.create(new long[0]);
    Assert.assertFalse(iterator.hasNext());
    Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
  }
}
