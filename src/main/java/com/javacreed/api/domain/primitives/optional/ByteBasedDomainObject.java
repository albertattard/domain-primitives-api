package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class ByteBasedDomainObject extends NumberBasedDomainObject<Byte> {

  protected ByteBasedDomainObject(final Optional<Byte> value) {
    super(value);
  }
}
