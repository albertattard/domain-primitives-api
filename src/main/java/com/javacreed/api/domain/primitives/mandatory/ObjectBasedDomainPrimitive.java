package com.javacreed.api.domain.primitives.mandatory;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

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
public class ObjectBasedDomainPrimitive<T> implements Supplier<T> {

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
    return comparator.compare(get(), other.get());
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
    return get().equals(other.get());
  }

  @Override
  public T get() {
    return value;
  }

  /**
   * Returns the value contained by the primitive object, which value is never {@code null}
   *
   * @return the value contained by the primitive object, which value is never {@code null}
   * @deprecated use {@link #get()} instead
   */
  @Deprecated
  public T getValue() {
    return get();
  }

  @Override
  public int hashCode() {
    return get().hashCode();
  }

  /**
   * Convert the value using the given <code> mapper</code>. The value returned could be anything, including
   * <code>null</code>, as this is determined by the <code>mapper</code> function.
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
    return mapper.apply(get());
  }

  /**
   * Returns <code>true</code> if the value returned by the given <code>supplier</code> is equal to the value of this
   * primitive, <code>false</code> otherwise.
   *
   * @param supplier
   *          supplier of the value that will be compared (which can be <code>null</code> and can return a
   *          <code>null</code> value)
   * @return <code>true</code> if the value returned by the given <code>supplier</code> is equal to the value of this
   *         primitive, <code>false</code> otherwise
   */
  public boolean sameValue(final Supplier<T> supplier) {
    return supplier != null && sameValue(supplier.get());
  }

  /**
   * Returns <code>true</code> if the given object is equal to the value of this primitive, <code>false</code> otherwise
   *
   * @param other
   *          the value to compare against (which can be <code>null</code>)
   * @return <code>true</code> if the given object is equal to the value of this primitive, <code>false</code> otherwise
   */
  public boolean sameValue(final T other) {
    return other != null && get().equals(other);
  }

  @Override
  public String toString() {
    return String.valueOf(get());
  }
}
