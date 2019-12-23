package com.javacreed.api.domain.primitives.array;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class ReadOnlyDoubleArrayTest {
  @Test
  public void should_be_immune_from_source_modification() {
    final double[] source = { 0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9 };
    final ReadOnlyDoubleArray array = ReadOnlyDoubleArray.of(source);

    /* Verify that modifying the source will not effect the read-only version */
    source[0] = 10.10;
    Assert.assertFalse(array.sameAs(source));
    Assert.assertEquals(0.0, array.valueAt(0), 0.0);
  }

  @Test
  public void should_create_array_with_given_content() {
    final double[] source = { 0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9 };
    final ReadOnlyDoubleArray array = ReadOnlyDoubleArray.of(source);
    Assert.assertEquals(source.length, array.length());
    Assert.assertTrue(array.sameAs(source));
    for (int i = 0; i < source.length; i++) {
      Assert.assertEquals(source[i], array.valueAt(i), 0.0);
    }
  }

  @Test
  public void should_create_new_iterator() {
    final double[] source = { 0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9 };
    final ReadOnlyDoubleArray array = ReadOnlyDoubleArray.of(source);

    final Iterator<Double> iterator = array.iterator();
    final Iterator<Double> secondIterator = array.iterator();
    Assert.assertNotSame(iterator, secondIterator);

    for (int i = 0; i < source.length; i++) {
      Assert.assertTrue(iterator.hasNext());
      Assert.assertEquals(source[i], iterator.next().doubleValue(), 0.0);

      Assert.assertTrue(secondIterator.hasNext());
      Assert.assertEquals(source[i], secondIterator.next().doubleValue(), 0.0);
    }
    Assert.assertFalse(iterator.hasNext());
    Assert.assertFalse(secondIterator.hasNext());
  }

  @Test
  public void should_return_a_new_copy() {
    final double[] source = { 0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9 };
    final ReadOnlyDoubleArray array = ReadOnlyDoubleArray.of(source);
    final double[] copy = array.copyArray();
    Assert.assertArrayEquals(source, copy, 0.0);

    /* Should return a new instance */
    final double[] secondCopy = array.copyArray();
    Assert.assertNotSame(copy, secondCopy);
    Assert.assertArrayEquals(source, secondCopy, 0.0);
  }

  @Test
  public void should_return_empty_instance() {
    final ReadOnlyDoubleArray array = ReadOnlyDoubleArray.of(new double[0]);
    Assert.assertSame(ReadOnlyDoubleArray.empty(), array);
    Assert.assertEquals(0, array.length());
  }

  @Test
  public void should_return_the_same_hashcode_for_the_same_source() {
    final double[] source = { 0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9 };
    final ReadOnlyDoubleArray array = ReadOnlyDoubleArray.of(source);
    Assert.assertEquals(Arrays.hashCode(source), array.hashCode());
    Assert.assertEquals(array.hashCode(), ReadOnlyDoubleArray.of(source).hashCode());
  }

  @Test
  public void should_return_true_only_when_equals_another_instance_of_the_same_content() {
    final double[] source = { 0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9 };
    final ReadOnlyDoubleArray array = ReadOnlyDoubleArray.of(source);

    Assert.assertFalse(array.equals(null));
    Assert.assertFalse(array.equals(new Object()));
    Assert.assertFalse(array.equals(ReadOnlyDoubleArray.of(new double[0])));

    Assert.assertTrue(array.equals(array));
    Assert.assertTrue(array.equals(ReadOnlyDoubleArray.of(source)));
  }

  @Test
  public void should_throw_NullPointerException_when_given_nulls() {
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyDoubleArray.of(null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyDoubleArray.empty().sameAs(null));
  }
}
