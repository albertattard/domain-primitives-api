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
  public void asBase64StringShouldEncode() {
    final T t = T.of("003c3872-f74a-4d4e-98e9-674a287575ca");
    Assert.assertEquals("ADw4cvdKTU6Y6WdKKHV1yg==", t.asBase64String());
  }

  @Test
  public void asStringShouldFormatted() {
    final String uuid = "7a9837c2-de8c-4e38-a603-cdad4d157838";
    final T t = T.of(uuid);
    Assert.assertEquals(uuid, t.asString());
  }
}
