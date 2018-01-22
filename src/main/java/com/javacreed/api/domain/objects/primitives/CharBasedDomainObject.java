package com.javacreed.api.domain.objects.primitives;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

@Immutable
public class CharBasedDomainObject implements Comparable<CharBasedDomainObject> {

  public static final Comparator<CharBasedDomainObject> DESCENDING_ORDER = (a, b) -> Character.compare(b.getValue(),
      a.getValue());

  protected final char value;

  protected CharBasedDomainObject(final char value) {
    this.value = value;
  }

  @Override
  public int compareTo(final CharBasedDomainObject other) {
    return Character.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object != null && getClass() == object.getClass()) {
      return value == ((CharBasedDomainObject) object).value;
    }

    return false;
  }

  public char getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
