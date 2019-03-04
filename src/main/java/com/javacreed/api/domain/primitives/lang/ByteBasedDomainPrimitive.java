package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

import com.javacreed.api.domain.primitives.function.ByteFunction;

@Immutable
public class ByteBasedDomainPrimitive implements Comparable<ByteBasedDomainPrimitive> {

  public static final Comparator<ByteBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Byte.compare(b.get(), a.get());

  protected final byte value;

  protected ByteBasedDomainPrimitive(final byte value) {
    this.value = value;
  }

  @Override
  public int compareTo(final ByteBasedDomainPrimitive other) {
    return Byte.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    return value == ((ByteBasedDomainPrimitive) object).value;
  }

  public byte get() {
    return value;
  }

  @Deprecated
  public byte getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value;
  }

  public boolean isSmaller(final ByteBasedDomainPrimitive other) {
    return value < other.value;
  }

  public boolean isSmallerOrEqaul(final ByteBasedDomainPrimitive other) {
    return value <= other.value;
  }

  public <T> T map(final ByteFunction<T> map) throws NullPointerException {
    return map.apply(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
