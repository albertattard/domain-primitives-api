package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

import com.javacreed.api.domain.primitives.function.CharFunction;

@Immutable
public class CharBasedDomainPrimitive implements Comparable<CharBasedDomainPrimitive> {

  public static final Comparator<CharBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Character.compare(b.get(), a.get());

  protected final char value;

  protected CharBasedDomainPrimitive(final char value) {
    this.value = value;
  }

  @Override
  public int compareTo(final CharBasedDomainPrimitive other) {
    return Character.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object != null && getClass() == object.getClass()) {
      return value == ((CharBasedDomainPrimitive) object).value;
    }

    return false;
  }

  public char get() {
    return value;
  }

  @Deprecated
  public char getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value;
  }

  public <T> T map(final CharFunction<T> map) throws NullPointerException {
    return map.apply(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
