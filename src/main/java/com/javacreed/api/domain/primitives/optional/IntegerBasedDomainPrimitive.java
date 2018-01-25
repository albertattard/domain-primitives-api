package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class IntegerBasedDomainPrimitive extends NumberBasedDomainPrimitive<Integer> {

  protected IntegerBasedDomainPrimitive(final Optional<Integer> value) {
    super(value);
  }
}
