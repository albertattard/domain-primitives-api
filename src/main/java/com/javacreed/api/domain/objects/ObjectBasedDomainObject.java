package com.javacreed.api.domain.objects;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class ObjectBasedDomainObject<T> {

  protected final Optional<T> value;

  protected ObjectBasedDomainObject(final Optional<T> value) throws NullPointerException {
    this.value = Objects.requireNonNull(value);
  }

  protected int compareTo(final ObjectBasedDomainObject<T> other, final Comparator<T> comparator) {
    final T a = getNullable();
    final T b = other.getNullable();

    if (a == b) {
      return 0;
    }

    if (a == null) {
      return -1;
    }

    if (b == null) {
      return 1;
    }

    return comparator.compare(a, b);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object != null && getClass() == object.getClass()) {
      @SuppressWarnings("rawtypes")
      final ObjectBasedDomainObject other = (ObjectBasedDomainObject) object;
      return value.equals(other.value);
    }

    return false;
  }

  /**
   * Returns the value if one is present, otherwise <code>null</code>. Note that this method may return
   * <code>null</code>
   *
   * @return the value if one is present, otherwise <code>null</code>
   */
  public T getNullable() {
    return value.orElse(null);
  }

  public Optional<T> getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  /**
   * Returns <code>true</code> if there is a value present, otherwise <code>false</code>.
   *
   * @return <code>true</code> if there is a value present, otherwise <code>false</code>
   */
  public boolean isValuePresent() {
    return value.isPresent();
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

  @Override
  public String toString() {
    return value.map(v -> v.toString()).orElse("empty");
  }
}
