package com.javacreed.api.domain.primitives.array;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class ShortArrayIteratorTest {

  @Test
  public void shouldReturnTheElementsInTheProperOrder() {
    final short[] source = new short[] { 1, 2, 3, 4 };
    final ShortArrayIterator iterator = ShortArrayIterator.create(source);
    for (int i = 0; i < source.length; i++) {
      Assert.assertTrue(iterator.hasNext());
      Assert.assertEquals(source[i], iterator.next().shortValue());
    }
    Assert.assertFalse(iterator.hasNext());
  }

  @Test
  public void shouldThrowNoSuchElementExceptionWhenAccessingMoreElementsThanAvailable() {
    final ShortArrayIterator iterator = ShortArrayIterator.create(new short[0]);
    Assert.assertFalse(iterator.hasNext());
    Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
  }
}
