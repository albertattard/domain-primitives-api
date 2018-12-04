package com.javacreed.api.domain.primitives.array;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class IntIteratorTest {

  @Test
  public void retrievingMoreElementsThanAvailable() {
    final IntArrayIterator iterator = IntArrayIterator.create(new int[] { 1 });
    Assert.assertTrue(iterator.hasNext());
    Assert.assertEquals(1, iterator.nextInt());
    Assert.assertFalse(iterator.hasNext());
    Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
  }

  @Test
  public void runIterator() {
    final IntArrayIterator iterator = IntArrayIterator.create(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 });
    for (int i = 0; i < 10; i++) {
      Assert.assertTrue(iterator.hasNext());
      Assert.assertEquals(i, iterator.nextInt());
    }
  }
}
