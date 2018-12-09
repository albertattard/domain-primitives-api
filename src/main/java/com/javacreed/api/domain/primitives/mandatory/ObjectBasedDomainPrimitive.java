package com.javacreed.api.domain.primitives.mandatory;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

/**
 * A domain primitive base class that holds non-null value. This domain primitive will always have a value, and the
 * value cannot be {@code null}
 *
 * @author Albert Attard
 *
 * @param <T>
 *          the type of the value that is contained by this domain primitive
 */
@Immutable
public class ObjectBasedDomainPrimitive<T> {

  /** The raw value wrapped within this domain primitive */
  protected final T value;

  /**
   * Creates an instance of this base domain primitive
   *
   * @param value
   *          the raw value contained by this domain primitive
   * @throws NullPointerException
   *           if the given {@code value} is {@code null}
   */
  protected ObjectBasedDomainPrimitive(final T value) {
    this.value = Preconditions.checkNotNull(value, "Value of " + getClass().getSimpleName() + " cannot be null");
  }

  /**
   * Compares this value with the given one using the given comparator
   *
   * @param other
   *          the value against which we will be comparing (which cannot be {@code null})
   * @param comparator
   *          the comparator to be used (which cannot be {@code null})
   * @return a negative integer, zero, or a positive integer as the this value is less than, equal to, or greater than
   *         the given value.
   * @throws NullPointerException
   *           if the given parameters are {@code null}
   */
  protected int compareTo(final ObjectBasedDomainPrimitive<T> other, final Comparator<T> comparator) {
    return comparator.compare(getValue(), other.getValue());
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    @SuppressWarnings("rawtypes")
    final ObjectBasedDomainPrimitive other = (ObjectBasedDomainPrimitive) object;
    return value.equals(other.value);
  }

  /**
   * Returns the value contained by the primitive object, which value is never {@code null}
   *
   * @return the value contained by the primitive object, which value is never {@code null}
   */
  public T getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  /**
   * Convert the value using the given {@code mapper}. The value returned could be anything, including {@code null}, as
   * this is determined by the {@code mapper} function.
   *
   * @param <U>
   *          The type of the result of the mapping function
   * @param mapper
   *          a mapping function to apply to the value, if present (which cannot be <code>null</code>)
   * @return an {@link Optional} describing the result of applying a mapping function to the value of this
   *         {@link Optional}, if a value is present, otherwise an empty {@link Optional}
   * @throws NullPointerException
   *           if the mapping function is <code>null</code>
   */
  public <U> U map(final Function<? super T, ? extends U> mapper) {
    Preconditions.checkNotNull(mapper);
    return mapper.apply(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
