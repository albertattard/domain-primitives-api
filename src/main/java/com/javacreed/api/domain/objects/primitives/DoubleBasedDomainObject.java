package com.javacreed.api.domain.objects.primitives;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

@Immutable
public class DoubleBasedDomainObject implements Comparable<DoubleBasedDomainObject> {

  public static final Comparator<DoubleBasedDomainObject> DESCENDING_ORDER = (a, b) -> Double.compare(b.getValue(),
                                                                                                      a.getValue());

  protected final double value;
  private final long longBits;

  protected DoubleBasedDomainObject(final double value) {
    this.value = value;
    this.longBits = Double.doubleToLongBits(value);
  }

  @Override
  public int compareTo(final DoubleBasedDomainObject other) {
    return Double.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object != null && getClass() == object.getClass()) {
      return longBits == ((DoubleBasedDomainObject) object).longBits;
    }

    return false;
  }

  public double getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return (int) (longBits ^ longBits >>> 32);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
