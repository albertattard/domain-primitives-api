package com.javacreed.api.domain.primitives.optional;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;
import com.javacreed.api.domain.primitives.utils.ComparatorUtils;

@Immutable
public class ObjectBasedDomainPrimitive<T> implements Supplier<Optional<T>> {

  protected final Optional<T> value;

  protected ObjectBasedDomainPrimitive(final Optional<T> value) throws NullPointerException {
    this.value = Preconditions.checkNotNull(value);
  }

  protected ObjectBasedDomainPrimitive(final T value) {
    this(Optional.ofNullable(value));
  }

  protected int compareTo(final ObjectBasedDomainPrimitive<T> other, final Comparator<T> comparator) {
    return ComparatorUtils.compare(orNull(), other.orNull(), comparator);
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

  @Override
  public Optional<T> get() {
    return value;
  }

  @Deprecated
  public Optional<T> getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  public void ifPresent(final Consumer<? super T> consumer) {
    value.ifPresent(consumer);
  }

  /**
   * Returns <code>true</code> if there is NO value present, otherwise <code>false</code>. This is the opposite of
   * {@link #isValuePresent()}.
   *
   * @return <code>true</code> if there is NO value present, otherwise <code>false</code>
   * @see #isValuePresent()
   */
  public boolean isValueMissing() {
    return value.isPresent() == false;
  }

  /**
   * Returns <code>true</code> if there is a value present, otherwise <code>false</code>.
   *
   * @return <code>true</code> if there is a value present, otherwise <code>false</code>
   * @see #isValueMissing()
   */
  public boolean isValuePresent() {
    return value.isPresent();
  }

  /**
   * If a value is present, apply the provided mapping function to it, and if the result is non-null, return an
   * {@link Optional} describing the result. Otherwise return an empty {@link Optional}.
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
  public <U> Optional<U> map(final Function<? super T, ? extends U> mapper) throws NullPointerException {
    Preconditions.checkNotNull(mapper);
    return value.map(mapper);
  }

  public <U> U mapOrNull(final Function<? super T, ? extends U> mapper) throws NullPointerException {
    return map(mapper).orElse(null);
  }

  /**
   * Returns the value if present, otherwise return <code>other</code>.
   *
   * @param other
   *          the value to be returned if there is no value present, may be <code>null</code>
   * @return the value, if present, otherwise <code>other</code>
   */
  public T orElse(final T other) {
    return value.orElse(other);
  }

  public T orElseGet(final Supplier<? extends T> supplier) throws NullPointerException {
    Preconditions.checkNotNull(supplier);
    return value.orElseGet(supplier);
  }

  public <X extends Throwable> T orElseThrow(final Supplier<? extends X> supplier) throws X {
    Preconditions.checkNotNull(supplier);
    return value.orElseThrow(supplier);
  }

  /**
   * Returns the value if one is present, otherwise <code>null</code>. Note that this method may return
   * <code>null</code>
   *
   * @return the value if one is present, otherwise <code>null</code>
   */
  public T orNull() {
    return orElse(null);
  }

  @Override
  public String toString() {
    return map(v -> v.toString()).orElse("empty");
  }
}
