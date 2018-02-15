package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

@Immutable
public class LongBasedDomainPrimitive implements Comparable<LongBasedDomainPrimitive> {

  public static final Comparator<LongBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Long.compare(b.getValue(),
                                                                                                     a.getValue());

  protected final long value;

  protected LongBasedDomainPrimitive(final long value) {
    this.value = value;
  }

  @Override
  public int compareTo(final LongBasedDomainPrimitive other) {
    return Long.compare(value, other.getValue());
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    return value == ((LongBasedDomainPrimitive) object).value;
  }

  public long getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return (int) (value ^ value >>> 32);
  }

  public boolean isSmaller(final LongBasedDomainPrimitive other) {
    return value < other.value;
  }

  public boolean isSmallerOrEqaul(final LongBasedDomainPrimitive other) {
    return value <= other.value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
