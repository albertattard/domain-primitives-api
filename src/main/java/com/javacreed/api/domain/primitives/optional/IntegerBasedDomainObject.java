package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class IntegerBasedDomainObject extends NumberBasedDomainObject<Integer> {

  protected IntegerBasedDomainObject(final Optional<Integer> value) {
    super(value);
  }
}
