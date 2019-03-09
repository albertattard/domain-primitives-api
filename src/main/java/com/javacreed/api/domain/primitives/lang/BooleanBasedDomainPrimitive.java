package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

import com.javacreed.api.domain.primitives.function.BooleanFunction;

@Immutable
public class BooleanBasedDomainPrimitive implements Comparable<BooleanBasedDomainPrimitive> {

  public static final Comparator<BooleanBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Boolean.compare(b.get(), a.get());

  public static boolean trueIfNotNullAndPositive(final Byte value) {
    return value != null && value > 0;
  }

  protected final boolean value;

  protected BooleanBasedDomainPrimitive(final boolean value) {
    this.value = value;
  }

  public byte asByte() {
    return (byte) (value ? 1 : 0);
  }

  @Override
  public int compareTo(final BooleanBasedDomainPrimitive other) {
    return Boolean.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object != null && getClass() == object.getClass()) {
      return value == ((BooleanBasedDomainPrimitive) object).value;
    }

    return false;
  }

  public boolean get() {
    return value;
  }

  @Deprecated
  public boolean getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value ? 1231 : 1237;
  }

  public boolean isFalse() {
    return false == value;
  }

  public boolean isTrue() {
    return value;
  }

  public <T> T map(final BooleanFunction<T> map) throws NullPointerException {
    return map.apply(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
