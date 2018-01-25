package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class NumberBasedDomainPrimitive<T extends Number & Comparable<T>> extends ComparableBasedDomainPrimitive<T> {

  protected NumberBasedDomainPrimitive(final Optional<T> value) throws NullPointerException {
    super(value);
  }

  public String orBlank() {
    return map(String::valueOf).orElse("");
  }
}
