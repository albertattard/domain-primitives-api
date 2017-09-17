package com.javacreed.api.domain.objects;

import javax.annotation.concurrent.Immutable;

@Immutable
public class DomainObject<T> {

  /** TODO: consider using optional instead */
  protected final T value;

  protected DomainObject(final T value) {
    this.value = value;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    @SuppressWarnings("rawtypes")
    final DomainObject other = (DomainObject) obj;
    if (value == null) {
      if (other.value != null) {
        return false;
      }
    } else if (!value.equals(other.value)) {
      return false;
    }
    return true;
  }

  public T getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (value == null ? 0 : value.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "DomainObject [value=" + value + "]";
  }
}
