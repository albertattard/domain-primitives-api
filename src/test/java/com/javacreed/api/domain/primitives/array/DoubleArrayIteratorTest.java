package com.javacreed.api.domain.primitives.array;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class DoubleArrayIteratorTest {

  @Test
  public void retrievingMoreElementsThanAvailable() {
    final DoubleArrayIterator iterator = DoubleArrayIterator.create(new double[] { 1 });
    Assert.assertTrue(iterator.hasNext());
    Assert.assertEquals(1, iterator.nextDouble(), 0.0001);
    Assert.assertFalse(iterator.hasNext());
    Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
  }
}
