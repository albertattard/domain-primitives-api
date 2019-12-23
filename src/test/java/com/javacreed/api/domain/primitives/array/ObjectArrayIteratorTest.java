package com.javacreed.api.domain.primitives.array;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class ObjectArrayIteratorTest {

  @Test
  public void shouldReturnTheElementsInTheProperOrder() {
    final Object[] source = new Object[] { "1", "2", "3", "4" };
    final ObjectArrayIterator iterator = ObjectArrayIterator.create(source);
    for (int i = 0; i < source.length; i++) {
      Assert.assertTrue(iterator.hasNext());
      Assert.assertEquals(source[i], iterator.next());
    }
    Assert.assertFalse(iterator.hasNext());
  }

  @Test
  public void shouldThrowNoSuchElementExceptionWhenAccessingMoreElementsThanAvailable() {
    final ObjectArrayIterator iterator = ObjectArrayIterator.create(new Object[0]);
    Assert.assertFalse(iterator.hasNext());
    Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
  }
}
