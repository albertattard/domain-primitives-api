package com.javacreed.api.domain.primitives.mandatory;

import javax.annotation.concurrent.Immutable;

@Immutable
public class ComparableBasedDomainPrimitive<T extends Comparable<T>> extends ObjectBasedDomainPrimitive<T>
    implements Comparable<ComparableBasedDomainPrimitive<T>> {

  protected ComparableBasedDomainPrimitive(final T value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final ComparableBasedDomainPrimitive<T> other) {
    return compareTo(other, (a, b) -> a.compareTo(b));
  }
}
