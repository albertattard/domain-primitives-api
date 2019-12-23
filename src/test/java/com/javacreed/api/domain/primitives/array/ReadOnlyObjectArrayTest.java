package com.javacreed.api.domain.primitives.array;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class ReadOnlyObjectArrayTest {
  @Test
  public void should_be_immune_from_source_modification() {
    final Object[] source = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
    final ReadOnlyObjectArray array = ReadOnlyObjectArray.of(source);

    /* Verify that modifying the source will not effect the read-only version */
    source[0] = "10";
    Assert.assertFalse(array.sameAs(source));
    Assert.assertEquals("0", array.valueAt(0));
  }

  @Test
  public void should_create_array_with_given_content() {
    final Object[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyObjectArray array = ReadOnlyObjectArray.of(source);
    Assert.assertEquals(source.length, array.length());
    Assert.assertTrue(array.sameAs(source));
    for (int i = 0; i < source.length; i++) {
      Assert.assertEquals(source[i], array.valueAt(i));
    }
  }

  @Test
  public void should_create_new_iterator() {
    final Object[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyObjectArray array = ReadOnlyObjectArray.of(source);

    final Iterator<Integer> iterator = array.iterator();
    final Iterator<Integer> secondIterator = array.iterator();
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
    final Object[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyObjectArray array = ReadOnlyObjectArray.of(source);
    final Object[] copy = array.copyArray();
    Assert.assertArrayEquals(source, copy);

    /* Should return a new instance */
    final Object[] secondCopy = array.copyArray();
    Assert.assertNotSame(copy, secondCopy);
    Assert.assertArrayEquals(source, secondCopy);
  }

  @Test
  public void should_return_the_same_hashcode_for_the_same_source() {
    final Object[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyObjectArray array = ReadOnlyObjectArray.of(source);
    Assert.assertEquals(Arrays.hashCode(source), array.hashCode());
    Assert.assertEquals(array.hashCode(), ReadOnlyObjectArray.of(source).hashCode());
  }

  @Test
  public void should_return_true_only_when_equals_another_instance_with_the_same_content() {
    final Object[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyObjectArray array = ReadOnlyObjectArray.of(source);

    Assert.assertFalse(array.equals(null));
    Assert.assertFalse(array.equals(new Object()));
    Assert.assertFalse(array.equals(ReadOnlyObjectArray.of(new Object[0])));

    Assert.assertTrue(array.equals(array));
    Assert.assertTrue(array.equals(ReadOnlyObjectArray.of(source)));
  }

  @Test
  public void should_throw_NullPointerException_when_given_nulls() {
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyObjectArray.of(null));
  }
}
