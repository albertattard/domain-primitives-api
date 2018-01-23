package com.javacreed.api.domain.objects.primitives;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

@Immutable
public class ShortBasedDomainObject implements Comparable<ShortBasedDomainObject> {

  public static final Comparator<ShortBasedDomainObject> DESCENDING_ORDER = (a, b) -> Short.compare(b.getValue(),
      a.getValue());

  protected final short value;

  protected ShortBasedDomainObject(final short value) {
    this.value = value;
  }

  @Override
  public int compareTo(final ShortBasedDomainObject other) {
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

    return value == ((ShortBasedDomainObject) object).value;
  }

  public short getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value;
  }

  public boolean isSmaller(final ShortBasedDomainObject other) {
    return value < other.value;
  }

  public boolean isSmallerOrEqaulTo(final ShortBasedDomainObject other) {
    return value <= other.value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
