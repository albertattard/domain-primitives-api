package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;
import java.util.function.DoubleFunction;

import javax.annotation.concurrent.Immutable;

@Immutable
public class DoubleBasedDomainPrimitive implements Comparable<DoubleBasedDomainPrimitive> {

  public static final Comparator<DoubleBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Double.compare(b.get(), a.get());

  protected final double value;
  private final long longBits;

  protected DoubleBasedDomainPrimitive(final double value) {
    this.value = value;
    longBits = Double.doubleToLongBits(value);
  }

  @Override
  public int compareTo(final DoubleBasedDomainPrimitive other) {
    return Double.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    return longBits == ((DoubleBasedDomainPrimitive) object).longBits;
  }

  public double get() {
    return value;
  }

  @Deprecated
  public double getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return (int) (longBits ^ longBits >>> 32);
  }

  public boolean isSmaller(final DoubleBasedDomainPrimitive other) {
    return value < other.value;
  }

  public boolean isSmallerOrEqaul(final DoubleBasedDomainPrimitive other) {
    return value <= other.value;
  }

  public <T> T map(final DoubleFunction<T> map) throws NullPointerException {
    return map.apply(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
