package com.javacreed.api.domain.primitives.array;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class ByteArrayIteratorTest {

  @Test
  public void retrievingMoreElementsThanAvailable() {
    final ByteArrayIterator iterator = ByteArrayIterator.create(new byte[] { 1 });
    Assert.assertTrue(iterator.hasNext());
    Assert.assertEquals(1, iterator.nextByte());
    Assert.assertFalse(iterator.hasNext());
    Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
  }
}
