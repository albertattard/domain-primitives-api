package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class BooleanBasedDomainPrimitive extends ComparableBasedDomainPrimitive<Boolean> {

  protected BooleanBasedDomainPrimitive(final Optional<Boolean> value) {
    super(value);
  }
}
