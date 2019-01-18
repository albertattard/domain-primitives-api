package com.javacreed.api.domain.primitives.mandatory;

import org.junit.Assert;
import org.junit.Test;

public class ObjectBasedDomainPrimitiveTest {

  @Test
  public void generalTests() {
    final ObjectBasedDomainPrimitive<String> a = new ObjectBasedDomainPrimitive<>("a");
    final ObjectBasedDomainPrimitive<String> b = new ObjectBasedDomainPrimitive<>("a");
    final ObjectBasedDomainPrimitive<String> c = new ObjectBasedDomainPrimitive<>("A");

    Assert.assertEquals(a, b);
    Assert.assertNotEquals(a, c);
    Assert.assertEquals(a.hashCode(), b.hashCode());
    Assert.assertNotEquals(a.hashCode(), c.hashCode());
    Assert.assertEquals("a", a.get());
    Assert.assertEquals("a", a.toString());
  }

  @Test
  public void handleNulls() {
    Assert.assertThrows(NullPointerException.class, () -> new ObjectBasedDomainPrimitive<>(null));
  }
}
