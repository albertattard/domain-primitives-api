package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class DoubleBasedDomainPrimitive extends NumberBasedDomainPrimitive<Double> {

  protected DoubleBasedDomainPrimitive(final Optional<Double> value) {
    super(value);
  }
}
