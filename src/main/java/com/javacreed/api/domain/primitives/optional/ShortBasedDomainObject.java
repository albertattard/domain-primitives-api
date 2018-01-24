package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class ShortBasedDomainObject extends NumberBasedDomainObject<Short> {

  protected ShortBasedDomainObject(final Optional<Short> value) {
    super(value);
  }
}
