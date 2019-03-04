package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

import com.javacreed.api.domain.primitives.function.ShortFunction;

@Immutable
public class ShortBasedDomainPrimitive implements Comparable<ShortBasedDomainPrimitive> {

  public static final Comparator<ShortBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Short.compare(b.get(), a.get());

  protected final short value;

  protected ShortBasedDomainPrimitive(final short value) {
    this.value = value;
  }

  @Override
  public int compareTo(final ShortBasedDomainPrimitive other) {
    return Short.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    return value == ((ShortBasedDomainPrimitive) object).value;
  }

  public short get() {
    return value;
  }

  @Deprecated
  public short getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value;
  }

  public boolean isSmaller(final ShortBasedDomainPrimitive other) {
    return value < other.value;
  }

  public boolean isSmallerOrEqaul(final ShortBasedDomainPrimitive other) {
    return value <= other.value;
  }

  public <T> T map(final ShortFunction<T> map) throws NullPointerException {
    return map.apply(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
