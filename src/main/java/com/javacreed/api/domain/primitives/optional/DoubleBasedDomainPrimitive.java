package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class DoubleBasedDomainObject extends NumberBasedDomainObject<Double> {

  protected DoubleBasedDomainObject(final Optional<Double> value) {
    super(value);
  }
}
