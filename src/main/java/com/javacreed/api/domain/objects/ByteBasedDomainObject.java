package com.javacreed.api.domain.objects;

import java.util.Comparator;

public class ByteBasedDomainObject implements Comparable<ByteBasedDomainObject> {

  public static final Comparator<ByteBasedDomainObject> DESCENDING_ORDER = (a, b) -> Byte.compare(b.getValue(),
      a.getValue());

  protected final byte value;

  protected ByteBasedDomainObject(final byte value) {
    this.value = value;
  }

  @Override
  public int compareTo(final ByteBasedDomainObject other) {
    return Byte.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object != null && getClass() == object.getClass()) {
      return value == ((ByteBasedDomainObject) object).value;
    }

    return false;
  }

  public byte getValue() {
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
