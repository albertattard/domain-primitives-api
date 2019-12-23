package com.javacreed.api.domain.primitives.array;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class FloatArrayIteratorTest {

  @Test
  public void shouldReturnTheElementsInTheProperOrder() {
    final float[] source = new float[] { 1.1F, 2.2F, 3.3F, 4.4F };
    final FloatArrayIterator iterator = FloatArrayIterator.create(source);
    for (int i = 0; i < source.length; i++) {
      Assert.assertTrue(iterator.hasNext());
      Assert.assertEquals(source[i], iterator.next().floatValue(), 0.0F);
    }
    Assert.assertFalse(iterator.hasNext());
  }

  @Test
  public void shouldThrowNoSuchElementExceptionWhenAccessingMoreElementsThanAvailable() {
    final FloatArrayIterator iterator = FloatArrayIterator.create(new float[0]);
    Assert.assertFalse(iterator.hasNext());
    Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
  }
}
