package com.javacreed.api.domain.primitives.array;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class ObjectArrayIteratorTest {

  @Test
  public void retrievingMoreElementsThanAvailable() {
    final ObjectArrayIterator<String> iterator = ObjectArrayIterator.create(new String[] { "a" });
    Assert.assertTrue(iterator.hasNext());
    Assert.assertEquals("a", iterator.next());
    Assert.assertFalse(iterator.hasNext());
    Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
  }
}
