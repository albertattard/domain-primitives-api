package com.javacreed.api.domain.primitives.lang;

import org.junit.Assert;
import org.junit.Test;

public class BooleanBasedDomainPrimitiveTest {

  @Test
  public void trueIfNotNullAndPositive() {
    Assert.assertFalse(BooleanBasedDomainPrimitive.trueIfNotNullAndPositive(null));
    Assert.assertFalse(BooleanBasedDomainPrimitive.trueIfNotNullAndPositive(Byte.valueOf((byte) 0)));
    Assert.assertFalse(BooleanBasedDomainPrimitive.trueIfNotNullAndPositive(Byte.MIN_VALUE));

    Assert.assertTrue(BooleanBasedDomainPrimitive.trueIfNotNullAndPositive(Byte.valueOf((byte) 1)));
    Assert.assertTrue(BooleanBasedDomainPrimitive.trueIfNotNullAndPositive(Byte.MAX_VALUE));
  }
}
