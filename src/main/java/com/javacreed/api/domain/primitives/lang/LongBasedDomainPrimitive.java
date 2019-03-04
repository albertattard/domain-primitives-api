package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;
import java.util.function.LongFunction;
import java.util.function.LongSupplier;

import javax.annotation.concurrent.Immutable;

@Immutable
public class LongBasedDomainPrimitive implements Comparable<LongBasedDomainPrimitive>, LongSupplier {

  public static final Comparator<LongBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Long.compare(b.get(), a.get());

  protected final long value;

  protected LongBasedDomainPrimitive(final long value) {
    this.value = value;
  }

  @Override
  public int compareTo(final LongBasedDomainPrimitive other) {
    return Long.compare(value, other.value);
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

  public long get() {
    return value;
  }

  @Override
  public long getAsLong() {
    return get();
  }

  @Deprecated
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

  public <T> T map(final LongFunction<T> map) throws NullPointerException {
    return map.apply(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
