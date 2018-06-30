package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;
import java.util.function.Function;

import javax.annotation.concurrent.Immutable;

@Immutable
public class BooleanBasedDomainPrimitive implements Comparable<BooleanBasedDomainPrimitive> {

  /**
   * Represents a function that accepts a boolean-valued argument and produces a result. This is the
   * {@code boolean}-consuming primitive specialization for {@link Function}.
   *
   * @param <R>
   *          the type of the result of the function
   *
   * @see Function
   */
  @FunctionalInterface
  public static interface BooleanFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value
     *          the function argument
     * @return the function result
     */
    R apply(boolean value);
  }

  public static final Comparator<BooleanBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Boolean.compare(b.getValue(),
                                                                                                           a.getValue());

  protected final boolean value;

  protected BooleanBasedDomainPrimitive(final boolean value) {
    this.value = value;
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

  public boolean getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value ? 1231 : 1237;
  }

  public <T> T map(final BooleanFunction<T> map) throws NullPointerException {
    return map.apply(value);
  }

  public byte toByte() {
    return (byte) (value ? 1 : 0);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
