package com.javacreed.api.domain.objects;

import java.util.Optional;

public class StringBasedDomainObject extends DomainObject<String> {

  protected StringBasedDomainObject(final Optional<String> value) throws NullPointerException {
    super(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
