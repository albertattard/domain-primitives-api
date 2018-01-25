package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class FloatBasedDomainPrimitive extends NumberBasedDomainPrimitive<Float> {

  protected FloatBasedDomainPrimitive(final Optional<Float> value) {
    super(value);
  }
}
