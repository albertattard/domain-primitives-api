package com.javacreed.api.domain.objects;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class ObjectBasedDomainObjectTest {

  public static class T extends ObjectBasedDomainObject<String> {

    public static T ofNullable(final String value) {
      return new T(Optional.ofNullable(value));
    }

    protected T(final Optional<String> value) throws NullPointerException {
      super(value);
    }
  }

  @Test
  public void equality() {
    Assert.assertEquals(T.ofNullable(null), T.ofNullable(null));
    Assert.assertEquals(T.ofNullable(""), T.ofNullable(""));
  }

}
