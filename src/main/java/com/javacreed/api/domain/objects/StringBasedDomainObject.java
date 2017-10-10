package com.javacreed.api.domain.objects;

import java.util.Optional;

public class StringBasedDomainObject extends DomainObject<String> implements Comparable<StringBasedDomainObject> {

  @FunctionalInterface
  public static interface StringComparator {
    int compare(String a, String b);
  }

  protected StringBasedDomainObject(final Optional<String> value) throws NullPointerException {
    super(value);
  }

  protected int compare(final String a, final String b, final StringComparator comparator) {
    if (a == b) {
      return 0;
    }

    if (a == null) {
      return -1;
    }

    if (b == null) {
      return 1;
    }

    return comparator.compare(a, b);

  }

  @Override
  public int compareTo(final StringBasedDomainObject other) {
    return compare(getNullable(), other.getNullable(), (a, b) -> a.compareTo(b));
  }

  public int compareToIgnoreCase(final StringBasedDomainObject other) {
    return compare(getNullable(), other.getNullable(), (a, b) -> a.compareToIgnoreCase(b));
  }

  @Override
  public String toString() {
    return value.orElse("empty");
  }
}
