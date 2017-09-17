package com.javacreed.api.domain.objects;

public class StringBasedDomainObject extends DomainObject<String> {

  protected StringBasedDomainObject(final String value) {
    super(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
