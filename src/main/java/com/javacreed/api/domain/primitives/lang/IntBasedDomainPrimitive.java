package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;

import javax.annotation.concurrent.Immutable;

@Immutable
public class IntBasedDomainPrimitive implements Comparable<IntBasedDomainPrimitive>, IntSupplier {

  public static final Comparator<IntBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Integer.compare(b.get(), a.get());

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

  public int get() {
    return value;
  }

  @Override
  public int getAsInt() {
    return get();
  }

  @Deprecated
  public int getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value;
  }

  public boolean isSmaller(final IntBasedDomainPrimitive other) throws NullPointerException {
    return value < other.value;
  }

  public boolean isSmallerOrEqaul(final IntBasedDomainPrimitive other) throws NullPointerException {
    return value <= other.value;
  }

  public <T> T map(final IntFunction<T> map) throws NullPointerException {
    return map.apply(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
