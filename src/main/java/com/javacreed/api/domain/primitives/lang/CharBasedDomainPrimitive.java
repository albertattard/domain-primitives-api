package com.javacreed.api.domain.primitives.lang;

import java.util.Comparator;
import java.util.function.Function;

import javax.annotation.concurrent.Immutable;

@Immutable
public class CharBasedDomainPrimitive implements Comparable<CharBasedDomainPrimitive> {

  /**
   * Represents a function that accepts a char-valued argument and produces a result. This is the {@code char}-consuming
   * primitive specialization for {@link Function}.
   *
   * @param <R>
   *          the type of the result of the function
   *
   * @see Function
   */
  @FunctionalInterface
  public interface CharFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value
     *          the function argument
     * @return the function result
     */
    R apply(char value);
  }

  public static final Comparator<CharBasedDomainPrimitive> DESCENDING_ORDER = (a, b) -> Character.compare(b.getValue(),
                                                                                                          a.getValue());

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
