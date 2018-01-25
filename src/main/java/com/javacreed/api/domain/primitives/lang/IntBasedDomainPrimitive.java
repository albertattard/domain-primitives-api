package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

@Immutable
public class IntBasedDomainPrimitive implements Comparable<IntBasedDomainPrimitive> {

  public static final Comparator<IntBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Integer.compare(b.getValue(),
      a.getValue());

  protected final int value;

  protected IntBasedDomainPrimitive(final int value) {
    this.value = value;
  }

  @Override
  public int compareTo(final IntBasedDomainPrimitive other) {
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

    return value == ((IntBasedDomainPrimitive) object).value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value;
  }

  public boolean isSmaller(final IntBasedDomainPrimitive other) {
    return value < other.value;
  }

  public boolean isSmallerOrEqaul(final IntBasedDomainPrimitive other) {
    return value <= other.value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
