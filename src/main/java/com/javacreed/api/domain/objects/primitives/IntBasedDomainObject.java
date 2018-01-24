package com.javacreed.api.domain.objects.primitives;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

@Immutable
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

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    return value == ((IntBasedDomainObject) object).value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value;
  }

  public boolean isSmaller(final IntBasedDomainObject other) {
    return value < other.value;
  }

  public boolean isSmallerOrEqaul(final IntBasedDomainObject other) {
    return value <= other.value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
