package com.javacreed.api.domain.objects;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

@Immutable
public class LongBasedDomainObject implements Comparable<LongBasedDomainObject> {

  public static final Comparator<LongBasedDomainObject> DESCENDING_ORDER = (a, b) -> Long.compare(b.getValue(),
      a.getValue());

  protected final long value;

  protected LongBasedDomainObject(final long value) {
    this.value = value;
  }

  @Override
  public int compareTo(final LongBasedDomainObject other) {
    return Long.compare(value, other.getValue());
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object != null && getClass() == object.getClass()) {
      return value != ((LongBasedDomainObject) object).value;
    }

    return false;
  }

  public long getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return (int) (value ^ value >>> 32);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}