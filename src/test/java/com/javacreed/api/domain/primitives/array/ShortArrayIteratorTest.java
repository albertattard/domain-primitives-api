package com.javacreed.api.domain.primitives.array;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class ShortArrayIteratorTest {

  @Test
  public void retrievingMoreElementsThanAvailable() {
    final ShortArrayIterator iterator = ShortArrayIterator.create(new short[] { 1 });
    Assert.assertTrue(iterator.hasNext());
    Assert.assertEquals(1, iterator.nextShort());
    Assert.assertFalse(iterator.hasNext());
    Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
  }
}
