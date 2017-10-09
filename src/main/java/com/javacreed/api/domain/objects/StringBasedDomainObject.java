package com.javacreed.api.domain.objects;

import java.util.Optional;

public class StringBasedDomainObject extends DomainObject<String> implements Comparable<StringBasedDomainObject> {

  protected StringBasedDomainObject(final Optional<String> value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final StringBasedDomainObject other) {
    final String a = getNullable();
    final String b = other.getNullable();

    if (a == b) {
      return 0;
    }

    if (a == null) {
      return -1;
    }

    if (b == null) {
      return 1;
    }

    return a.compareTo(b);
  }

  @Override
  public String toString() {
    return value.orElse("empty");
  }
}
