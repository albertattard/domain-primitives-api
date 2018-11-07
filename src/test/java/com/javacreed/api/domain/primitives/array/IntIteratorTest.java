package com.javacreed.api.domain.primitives.array;

import org.junit.Assert;
import org.junit.Test;

public class IntIteratorTest {

  @Test
  public void runIterator() {
    final IntIterator iterator = IntIterator.create(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 });
    for (int i = 0; i < 10; i++) {
      Assert.assertTrue(iterator.hasNext());
      Assert.assertEquals(i, iterator.nextInt());
    }
  }
}
