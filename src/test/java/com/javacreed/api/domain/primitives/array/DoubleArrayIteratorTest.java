package com.javacreed.api.domain.primitives.array;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class DoubleArrayIteratorTest {

  @Test
  public void shouldReturnTheElementsInTheProperOrder() {
    final double[] source = new double[] { 1.1, 2.2, 3.3, 4.4 };
    final DoubleArrayIterator iterator = DoubleArrayIterator.create(source);
    for (int i = 0; i < source.length; i++) {
      Assert.assertTrue(iterator.hasNext());
      Assert.assertEquals(source[i], iterator.next().doubleValue(), 0.0);
    }
    Assert.assertFalse(iterator.hasNext());
  }

  @Test
  public void shouldThrowNoSuchElementExceptionWhenAccessingMoreElementsThanAvailable() {
    final DoubleArrayIterator iterator = DoubleArrayIterator.create(new double[0]);
    Assert.assertFalse(iterator.hasNext());
    Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
  }
}
