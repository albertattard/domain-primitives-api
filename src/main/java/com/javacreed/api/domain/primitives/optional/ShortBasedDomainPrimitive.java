package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class ShortBasedDomainPrimitive extends NumberBasedDomainPrimitive<Short> {

  protected ShortBasedDomainPrimitive(final Optional<Short> value) {
    super(value);
  }
}
