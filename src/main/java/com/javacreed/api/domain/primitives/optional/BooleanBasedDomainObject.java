package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class BooleanBasedDomainObject extends ComparableBasedDomainObject<Boolean> {

  protected BooleanBasedDomainObject(final Optional<Boolean> value) {
    super(value);
  }
}
