package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class StringBasedDomainPrimitiveTest {

  @Test
  public void equalsIgnoreCase() {
    final StringBasedDomainPrimitive aLower = new StringBasedDomainPrimitive("a");
    final StringBasedDomainPrimitive aUpper = new StringBasedDomainPrimitive("A");

    /* Compare using different case (upper/lower) */
    Assert.assertTrue(aLower.equalsIgnoreCase(aLower));
    Assert.assertTrue(aLower.equalsIgnoreCase(aUpper));
    Assert.assertTrue(aUpper.equalsIgnoreCase(aLower));

    /* Compare with blanks */
    final StringBasedDomainPrimitive empty = new StringBasedDomainPrimitive(Optional.empty());
    Assert.assertFalse(empty.equalsIgnoreCase(empty));
    Assert.assertFalse(aLower.equalsIgnoreCase(empty));
    Assert.assertFalse(empty.equalsIgnoreCase(aLower));
  }
}
