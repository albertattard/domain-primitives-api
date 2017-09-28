package com.javacreed.api.domain.objects;

import java.util.Objects;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class DomainObject<T> {

  protected final Optional<T> value;

  protected DomainObject(final Optional<T> value) throws NullPointerException {
    this.value = Objects.requireNonNull(value);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object != null && getClass() != object.getClass()) {
      @SuppressWarnings("rawtypes")
      final DomainObject other = (DomainObject) object;
      return value.equals(other.value);
    }

    return false;
  }

  public Optional<T> getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
