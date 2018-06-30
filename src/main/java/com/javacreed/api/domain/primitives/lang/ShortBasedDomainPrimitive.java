package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;
import java.util.function.Function;

import javax.annotation.concurrent.Immutable;

@Immutable
public class ShortBasedDomainPrimitive implements Comparable<ShortBasedDomainPrimitive> {

  /**
   * Represents a function that accepts a short-valued argument and produces a result. This is the
   * {@code short}-consuming primitive specialization for {@link Function}.
   *
   * @param <R>
   *          the type of the result of the function
   *
   * @see Function
   */
  @FunctionalInterface
  public static interface ShortFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value
     *          the function argument
     * @return the function result
     */
    R apply(short value);
  }

  public static final Comparator<ShortBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Short.compare(b.getValue(),
                                                                                                       a.getValue());

  protected final short value;

  protected ShortBasedDomainPrimitive(final short value) {
    this.value = value;
  }

  @Override
  public int compareTo(final ShortBasedDomainPrimitive other) {
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

    return value == ((ShortBasedDomainPrimitive) object).value;
  }

  public short getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value;
  }

  public boolean isSmaller(final ShortBasedDomainPrimitive other) {
    return value < other.value;
  }

  public boolean isSmallerOrEqaul(final ShortBasedDomainPrimitive other) {
    return value <= other.value;
  }

  public <T> T map(final ShortFunction<T> map) throws NullPointerException {
    return map.apply(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
