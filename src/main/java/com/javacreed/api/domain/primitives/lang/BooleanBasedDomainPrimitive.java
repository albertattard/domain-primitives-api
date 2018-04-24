package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

@Immutable
public class BooleanBasedDomainPrimitive implements Comparable<BooleanBasedDomainPrimitive> {

  public static final Comparator<BooleanBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Boolean.compare(b.getValue(),
                                                                                                           a.getValue());

  protected final boolean value;

  protected BooleanBasedDomainPrimitive(final boolean value) {
    this.value = value;
  }

  @Override
  public int compareTo(final BooleanBasedDomainPrimitive other) {
    return Boolean.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object != null && getClass() == object.getClass()) {
      return value == ((BooleanBasedDomainPrimitive) object).value;
    }

    return false;
  }

  public boolean getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value ? 1231 : 1237;
  }

  public byte toByte() {
    return (byte) (value ? 1 : 0);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
