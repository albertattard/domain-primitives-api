package com.javacreed.api.domain.primitives.mandatory;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class BigDecimalBasedDomainPrimitiveTest {

  private static class Testable extends BigDecimalBasedDomainPrimitive {
    private Testable(final BigDecimal value) throws NullPointerException {
      super(value);
    }
  }

  @Test
  public void hasXxxValue() {
    final Testable testable = new Testable(new BigDecimal("100"));
    Assert.assertTrue(testable.hasSameValue(new BigDecimal("100")));
    Assert.assertTrue(testable.hasSameValue(new BigDecimal("100.00")));
    Assert.assertTrue(testable.hasSmallerValue(new BigDecimal("100.01")));
    Assert.assertTrue(testable.hasLargerValue(new BigDecimal("99.99")));
  }
}
