package com.javacreed.api.domain.primitives.lang;

import org.junit.Assert;
import org.junit.Test;

public class BooleanBasedDomainPrimitiveTest {

  private static class Subject extends BooleanBasedDomainPrimitive {
    private Subject(final boolean value) {
      super(value);
    }
  }

  @Test
  public void should_return_1_when_true_0_when_false() {
    Assert.assertEquals(0, new Subject(false).asByte());
    Assert.assertEquals(1, new Subject(true).asByte());
  }

  @Test
  public void should_return_true_if_not_null_and_positive_false_otherwise() {
    Assert.assertFalse(BooleanBasedDomainPrimitive.trueIfNotNullAndPositive(null));
    Assert.assertFalse(BooleanBasedDomainPrimitive.trueIfNotNullAndPositive(Byte.valueOf((byte) 0)));
    Assert.assertFalse(BooleanBasedDomainPrimitive.trueIfNotNullAndPositive(Byte.MIN_VALUE));

    Assert.assertTrue(BooleanBasedDomainPrimitive.trueIfNotNullAndPositive(Byte.valueOf((byte) 1)));
    Assert.assertTrue(BooleanBasedDomainPrimitive.trueIfNotNullAndPositive(Byte.MAX_VALUE));
  }

  @Test
  public void should_sort_in_descending_order() {
    final Subject a = new Subject(false);
    final Subject b = new Subject(true);

    Assert.assertEquals(0, BooleanBasedDomainPrimitive.DESCENDING_ORDER.compare(a, a));
    Assert.assertEquals(1, BooleanBasedDomainPrimitive.DESCENDING_ORDER.compare(a, b));
    Assert.assertEquals(-1, BooleanBasedDomainPrimitive.DESCENDING_ORDER.compare(b, a));

    Assert.assertEquals(0, a.compareTo(a));
    Assert.assertEquals(-1, a.compareTo(b));
    Assert.assertEquals(1, b.compareTo(a));
  }
}
