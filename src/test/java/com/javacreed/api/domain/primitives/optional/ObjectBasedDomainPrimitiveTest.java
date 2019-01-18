package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class ObjectBasedDomainPrimitiveTest {

  public static class T extends ObjectBasedDomainPrimitive<String> {

    public static T ofNullable(final String value) {
      return new T(Optional.ofNullable(value));
    }

    private T(final Optional<String> value) throws NullPointerException {
      super(value);
    }
  }

  @Test
  public void equality() {
    Assert.assertEquals(T.ofNullable(null), T.ofNullable(null));
    Assert.assertEquals(T.ofNullable(""), T.ofNullable(""));
  }
}
