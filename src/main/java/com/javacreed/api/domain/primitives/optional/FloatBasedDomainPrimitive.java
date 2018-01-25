package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class FloatBasedDomainObject extends NumberBasedDomainObject<Float> {

  protected FloatBasedDomainObject(final Optional<Float> value) {
    super(value);
  }
}
