package com.javacreed.api.domain.primitives.mandatory;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

public class UuidBasedDomainPrimitiveTest {

  private static class T extends UuidBasedDomainPrimitive {

    public static T of(final String value) {
      return new T(UUID.fromString(value));
    }

    private T(final UUID value) throws NullPointerException {
      super(value);
    }
  }

  @Test
  public void toStringShouldFormatted() {
    final String uuid = "7a9837c2-de8c-4e38-a603-cdad4d157838";
    final T t = T.of(uuid);
    Assert.assertEquals(uuid, t.toString());
  }
}
