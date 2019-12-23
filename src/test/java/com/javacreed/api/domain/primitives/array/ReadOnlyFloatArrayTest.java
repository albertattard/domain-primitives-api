package com.javacreed.api.domain.primitives.array;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class ReadOnlyFloatArrayTest {
  @Test
  public void should_be_immune_from_source_modification() {
    final float[] source = { 0.0F, 1.1F, 2.2F, 3.3F, 4.4F, 5.5F, 6.6F, 7.7F, 8.8F, 9.9F };
    final ReadOnlyFloatArray array = ReadOnlyFloatArray.of(source);

    /* Verify that modifying the source will not effect the read-only version */
    source[0] = 10.10F;
    Assert.assertFalse(array.sameAs(source));
    Assert.assertEquals(0.0, array.valueAt(0), 0.0);
  }

  @Test
  public void should_create_array_with_given_content() {
    final float[] source = { 0.0F, 1.1F, 2.2F, 3.3F, 4.4F, 5.5F, 6.6F, 7.7F, 8.8F, 9.9F };
    final ReadOnlyFloatArray array = ReadOnlyFloatArray.of(source);
    Assert.assertEquals(source.length, array.length());
    Assert.assertTrue(array.sameAs(source));
    for (int i = 0; i < source.length; i++) {
      Assert.assertEquals(source[i], array.valueAt(i), 0.0);
    }
  }

  @Test
  public void should_create_new_iterator() {
    final float[] source = { 0.0F, 1.1F, 2.2F, 3.3F, 4.4F, 5.5F, 6.6F, 7.7F, 8.8F, 9.9F };
    final ReadOnlyFloatArray array = ReadOnlyFloatArray.of(source);

    final Iterator<Float> iterator = array.iterator();
    final Iterator<Float> secondIterator = array.iterator();
    Assert.assertNotSame(iterator, secondIterator);

    for (int i = 0; i < source.length; i++) {
      Assert.assertTrue(iterator.hasNext());
      Assert.assertEquals(source[i], iterator.next().floatValue(), 0.0);

      Assert.assertTrue(secondIterator.hasNext());
      Assert.assertEquals(source[i], secondIterator.next().floatValue(), 0.0);
    }
    Assert.assertFalse(iterator.hasNext());
    Assert.assertFalse(secondIterator.hasNext());
  }

  @Test
  public void should_return_a_new_copy() {
    final float[] source = { 0.0F, 1.1F, 2.2F, 3.3F, 4.4F, 5.5F, 6.6F, 7.7F, 8.8F, 9.9F };
    final ReadOnlyFloatArray array = ReadOnlyFloatArray.of(source);
    final float[] copy = array.copyArray();
    Assert.assertArrayEquals(source, copy, 0.0F);

    /* Should return a new instance */
    final float[] secondCopy = array.copyArray();
    Assert.assertNotSame(copy, secondCopy);
    Assert.assertArrayEquals(source, secondCopy, 0.0F);
  }

  @Test
  public void should_return_empty_instance() {
    final ReadOnlyFloatArray array = ReadOnlyFloatArray.of(new float[0]);
    Assert.assertSame(ReadOnlyFloatArray.empty(), array);
    Assert.assertEquals(0, array.length());
  }

  @Test
  public void should_return_the_same_hashcode_for_the_same_source() {
    final float[] source = { 0.0F, 1.1F, 2.2F, 3.3F, 4.4F, 5.5F, 6.6F, 7.7F, 8.8F, 9.9F };
    final ReadOnlyFloatArray array = ReadOnlyFloatArray.of(source);
    Assert.assertEquals(Arrays.hashCode(source), array.hashCode());
    Assert.assertEquals(array.hashCode(), ReadOnlyFloatArray.of(source).hashCode());
  }

  @Test
  public void should_return_true_only_when_equals_another_instance_of_the_same_content() {
    final float[] source = { 0.0F, 1.1F, 2.2F, 3.3F, 4.4F, 5.5F, 6.6F, 7.7F, 8.8F, 9.9F };
    final ReadOnlyFloatArray array = ReadOnlyFloatArray.of(source);

    Assert.assertFalse(array.equals(null));
    Assert.assertFalse(array.equals(new Object()));
    Assert.assertFalse(array.equals(ReadOnlyFloatArray.of(new float[0])));

    Assert.assertTrue(array.equals(array));
    Assert.assertTrue(array.equals(ReadOnlyFloatArray.of(source)));
  }

  @Test
  public void should_throw_NullPointerException_when_given_nulls() {
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyFloatArray.of(null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyFloatArray.empty().sameAs(null));
  }
}
