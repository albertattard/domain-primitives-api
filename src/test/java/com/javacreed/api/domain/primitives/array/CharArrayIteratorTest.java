package com.javacreed.api.domain.primitives.array;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class CharArrayIteratorTest {

  @Test
  public void retrievingMoreElementsThanAvailable() {
    final CharArrayIterator iterator = CharArrayIterator.create(new char[] { 'a' });
    Assert.assertTrue(iterator.hasNext());
    Assert.assertEquals('a', iterator.nextChar());
    Assert.assertFalse(iterator.hasNext());
    Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
  }
}
