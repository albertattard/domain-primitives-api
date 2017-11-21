package com.javacreed.api.domain.objects;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

@Immutable
public class FloatBasedDomainObject implements Comparable<FloatBasedDomainObject> {

  public static final Comparator<FloatBasedDomainObject> DESCENDING_ORDER = (a, b) -> Float.compare(b.getValue(),
      a.getValue());

  protected final float value;
  private final int intBits;

  protected FloatBasedDomainObject(final int value) {
    this.value = value;
    this.intBits = Float.floatToIntBits(value);
  }

  @Override
  public int compareTo(final FloatBasedDomainObject other) {
    return Float.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object != null && getClass() == object.getClass()) {
      return intBits == ((FloatBasedDomainObject) object).intBits;
    }

    return false;
  }

  public float getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return intBits;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
