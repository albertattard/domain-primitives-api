package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class LongBasedDomainPrimitive extends NumberBasedDomainPrimitive<Long> {

  protected LongBasedDomainPrimitive(final Optional<Long> value) {
    super(value);
  }
}
