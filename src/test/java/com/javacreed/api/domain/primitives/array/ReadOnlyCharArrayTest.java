package com.javacreed.api.domain.primitives.array;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class ReadOnlyCharArrayTest {
  @Test
  public void should_be_immune_from_source_modification() {
    final char[] source = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    final ReadOnlyCharArray array = ReadOnlyCharArray.of(source);

    /* Verify that modifying the source will not effect the read-only version */
    source[0] = 'x';
    Assert.assertFalse(array.sameAs(source));
    Assert.assertEquals('0', array.valueAt(0));
  }

  @Test
  public void should_create_array_with_given_content() {
    final char[] source = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    final ReadOnlyCharArray array = ReadOnlyCharArray.of(source);
    Assert.assertEquals(source.length, array.length());
    Assert.assertTrue(array.sameAs(source));
    for (int i = 0; i < source.length; i++) {
      Assert.assertEquals(source[i], array.valueAt(i));
    }
  }

  @Test
  public void should_create_new_iterator() {
    final char[] source = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    final ReadOnlyCharArray array = ReadOnlyCharArray.of(source);

    final Iterator<Character> iterator = array.iterator();
    final Iterator<Character> secondIterator = array.iterator();
    Assert.assertNotSame(iterator, secondIterator);

    for (int i = 0; i < source.length; i++) {
      Assert.assertTrue(iterator.hasNext());
      Assert.assertEquals(source[i], iterator.next().charValue());

      Assert.assertTrue(secondIterator.hasNext());
      Assert.assertEquals(source[i], secondIterator.next().charValue());
    }
    Assert.assertFalse(iterator.hasNext());
    Assert.assertFalse(secondIterator.hasNext());
  }

  @Test
  public void should_return_a_new_copy() {
    final char[] source = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    final ReadOnlyCharArray array = ReadOnlyCharArray.of(source);
    final char[] copy = array.copyArray();
    Assert.assertArrayEquals(source, copy);

    /* Should return a new instance */
    final char[] secondCopy = array.copyArray();
    Assert.assertNotSame(copy, secondCopy);
    Assert.assertArrayEquals(source, secondCopy);
  }

  @Test
  public void should_return_empty_instance() {
    final ReadOnlyCharArray array = ReadOnlyCharArray.of(new char[0]);
    Assert.assertSame(ReadOnlyCharArray.empty(), array);
    Assert.assertEquals(0, array.length());
  }

  @Test
  public void should_return_the_same_hashcode_for_the_same_source() {
    final char[] source = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    final ReadOnlyCharArray array = ReadOnlyCharArray.of(source);
    Assert.assertEquals(Arrays.hashCode(source), array.hashCode());
    Assert.assertEquals(array.hashCode(), ReadOnlyCharArray.of(source).hashCode());
  }

  @Test
  public void should_return_true_only_when_equals_another_instance_with_the_same_content() {
    final char[] source = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    final ReadOnlyCharArray array = ReadOnlyCharArray.of(source);

    Assert.assertFalse(array.equals(null));
    Assert.assertFalse(array.equals(new Object()));
    Assert.assertFalse(array.equals(ReadOnlyCharArray.of(new char[0])));

    Assert.assertTrue(array.equals(array));
    Assert.assertTrue(array.equals(ReadOnlyCharArray.of(source)));
  }

  @Test
  public void should_throw_NullPointerException_when_given_nulls() {
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyCharArray.of(null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyCharArray.empty().sameAs(null));
  }
}
