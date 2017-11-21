package com.javacreed.api.domain.objects.mandatory;

import javax.annotation.concurrent.Immutable;

@Immutable
public class ComparableBasedDomainObject<T extends Comparable<T>> extends ObjectBasedDomainObject<T>
    implements Comparable<ComparableBasedDomainObject<T>> {

  protected ComparableBasedDomainObject(final T value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final ComparableBasedDomainObject<T> other) {
    return compareTo(other, (a, b) -> a.compareTo(b));
  }
}
