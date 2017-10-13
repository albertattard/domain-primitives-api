package com.javacreed.api.domain.objects;

import java.util.Comparator;

public class IntBasedDomainObject implements Comparable<IntBasedDomainObject> {

  public static final Comparator<IntBasedDomainObject> DESCENDING_ORDER = (a, b) -> Integer.compare(b.getValue(),
      a.getValue());

  protected final int value;

  protected IntBasedDomainObject(final int value) {
    this.value = value;
  }

  @Override
  public int compareTo(final IntBasedDomainObject other) {
    return Integer.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object != null && getClass() == object.getClass()) {
      return value == ((IntBasedDomainObject) object).value;
    }

    return false;
  }

  public int getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
