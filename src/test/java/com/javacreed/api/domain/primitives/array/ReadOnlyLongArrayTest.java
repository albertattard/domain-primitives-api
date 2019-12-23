package com.javacreed.api.domain.primitives.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

public class ReadOnlyLongArrayTest {
  @Test
  public void should_be_immune_from_source_modification() {
    final long[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyLongArray array = ReadOnlyLongArray.of(source);

    /* Verify that modifying the source will not effect the read-only version */
    source[0] = 10;
    Assert.assertFalse(array.sameAs(source));
    Assert.assertEquals(0, array.valueAt(0));
  }

  @Test
  public void should_create_array_with_given_content() {
    final long[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyLongArray array = ReadOnlyLongArray.of(source);
    Assert.assertEquals(source.length, array.length());
    Assert.assertTrue(array.sameAs(source));
    for (int i = 0; i < source.length; i++) {
      Assert.assertEquals(source[i], array.valueAt(i));
    }
  }

  @Test
  public void should_create_new_iterator() {
    final long[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyLongArray array = ReadOnlyLongArray.of(source);

    final Iterator<Long> iterator = array.iterator();
    final Iterator<Long> secondIterator = array.iterator();
    Assert.assertNotSame(iterator, secondIterator);

    for (int i = 0; i < source.length; i++) {
      Assert.assertTrue(iterator.hasNext());
      Assert.assertEquals(source[i], iterator.next().intValue());

      Assert.assertTrue(secondIterator.hasNext());
      Assert.assertEquals(source[i], secondIterator.next().intValue());
    }
    Assert.assertFalse(iterator.hasNext());
    Assert.assertFalse(secondIterator.hasNext());
  }

  @Test
  public void should_return_a_new_copy() {
    final long[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyLongArray array = ReadOnlyLongArray.of(source);
    final long[] copy = array.copyArray();
    Assert.assertArrayEquals(source, copy);

    /* Should return a new instance */
    final long[] secondCopy = array.copyArray();
    Assert.assertNotSame(copy, secondCopy);
    Assert.assertArrayEquals(source, secondCopy);
  }

  @Test
  public void should_return_empty_instance() {
    final ReadOnlyLongArray array = ReadOnlyLongArray.of(new long[0]);
    Assert.assertSame(ReadOnlyLongArray.empty(), array);
    Assert.assertEquals(0, array.length());
  }

  @Test
  public void should_return_the_same_hashcode_for_the_same_source() {
    final long[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyLongArray array = ReadOnlyLongArray.of(source);
    Assert.assertEquals(Arrays.hashCode(source), array.hashCode());
    Assert.assertEquals(array.hashCode(), ReadOnlyLongArray.of(source).hashCode());
  }

  @Test
  public void should_return_true_only_when_equals_another_instance_of_the_same_content() {
    final long[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyLongArray array = ReadOnlyLongArray.of(source);

    Assert.assertFalse(array.equals(null));
    Assert.assertFalse(array.equals(new Object()));
    Assert.assertFalse(array.equals(ReadOnlyLongArray.of(new long[0])));

    Assert.assertTrue(array.equals(array));
    Assert.assertTrue(array.equals(ReadOnlyLongArray.of(source)));
  }

  @Test
  public void should_throw_NullPointerException_when_given_nulls() {
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyLongArray.of(null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyLongArray.empty().sameAs(null));
  }
}
