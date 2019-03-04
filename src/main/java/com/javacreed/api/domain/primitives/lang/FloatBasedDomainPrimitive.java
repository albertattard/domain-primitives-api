package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

import com.javacreed.api.domain.primitives.function.FloatFunction;

@Immutable
public class FloatBasedDomainPrimitive implements Comparable<FloatBasedDomainPrimitive> {

  public static final Comparator<FloatBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Float.compare(b.get(), a.get());

  protected final float value;
  private final int intBits;

  protected FloatBasedDomainPrimitive(final int value) {
    this.value = value;
    intBits = Float.floatToIntBits(value);
  }

  @Override
  public int compareTo(final FloatBasedDomainPrimitive other) {
    return Float.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    return intBits == ((FloatBasedDomainPrimitive) object).intBits;
  }

  public float get() {
    return value;
  }

  @Deprecated
  public float getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return intBits;
  }

  public boolean isSmaller(final FloatBasedDomainPrimitive other) {
    return value < other.value;
  }

  public boolean isSmallerOrEqaul(final FloatBasedDomainPrimitive other) {
    return value <= other.value;
  }

  public <T> T map(final FloatFunction<T> map) throws NullPointerException {
    return map.apply(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
