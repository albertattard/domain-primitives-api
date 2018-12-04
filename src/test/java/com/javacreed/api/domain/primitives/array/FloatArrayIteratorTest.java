package com.javacreed.api.domain.primitives.array;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class FloatArrayIteratorTest {

  @Test
  public void retrievingMoreElementsThanAvailable() {
    final FloatArrayIterator iterator = FloatArrayIterator.create(new float[] { 1 });
    Assert.assertTrue(iterator.hasNext());
    Assert.assertEquals(1, iterator.nextFloat(), 0.0001F);
    Assert.assertFalse(iterator.hasNext());
    Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
  }
}
