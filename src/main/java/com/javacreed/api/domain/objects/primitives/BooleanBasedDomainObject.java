package com.javacreed.api.domain.objects;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

@Immutable
public class BooleanBasedDomainObject implements Comparable<BooleanBasedDomainObject> {

  public static final Comparator<BooleanBasedDomainObject> DESCENDING_ORDER = (a, b) -> Boolean.compare(b.getValue(),
      a.getValue());

  protected final boolean value;

  protected BooleanBasedDomainObject(final boolean value) {
    this.value = value;
  }

  @Override
  public int compareTo(final BooleanBasedDomainObject other) {
    return Boolean.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object != null && getClass() == object.getClass()) {
      return value == ((BooleanBasedDomainObject) object).value;
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

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
