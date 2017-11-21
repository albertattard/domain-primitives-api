package com.javacreed.api.domain.objects.mandatory;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ObjectBasedDomainObject<T> {

  protected final T value;

  protected ObjectBasedDomainObject(final T value) throws NullPointerException {
    this.value = Preconditions.checkNotNull(value);
  }

  protected int compareTo(final ObjectBasedDomainObject<T> other, final Comparator<T> comparator) {
    final T a = getValue();
    final T b = other.getValue();

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

  public T getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value.hashCode();
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
  public <U> U map(final Function<? super T, ? extends U> mapper) throws NullPointerException {
    Preconditions.checkNotNull(mapper);
    return mapper.apply(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
