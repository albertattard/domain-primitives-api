package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;
import java.util.function.Function;

import javax.annotation.concurrent.Immutable;

@Immutable
public class FloatBasedDomainPrimitive implements Comparable<FloatBasedDomainPrimitive> {

  /**
   * Represents a function that accepts a float-valued argument and produces a result. This is the
   * {@code float}-consuming primitive specialization for {@link Function}.
   *
   * @param <R>
   *          the type of the result of the function
   *
   * @see Function
   */
  @FunctionalInterface
  public static interface FloatFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value
     *          the function argument
     * @return the function result
     */
    R apply(float value);
  }

  public static final Comparator<FloatBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Float.compare(b.getValue(),
                                                                                                       a.getValue());

  protected final float value;
  private final int intBits;

  protected FloatBasedDomainPrimitive(final int value) {
    this.value = value;
    intBits = Float.floatToIntBits(value);
  }

  @Override
  public int compareTo(final FloatBasedDomainPrimitive other) {
    return Float.compare(value, other.value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    return intBits == ((FloatBasedDomainPrimitive) object).intBits;
  }

  public float getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return intBits;
  }

  public boolean isSmaller(final FloatBasedDomainPrimitive other) {
    return value < other.value;
  }

  public boolean isSmallerOrEqaul(final FloatBasedDomainPrimitive other) {
    return value <= other.value;
  }

  public <T> T map(final FloatFunction<T> map) throws NullPointerException {
    return map.apply(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
