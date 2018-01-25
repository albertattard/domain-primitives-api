package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class ByteBasedDomainPrimitive extends NumberBasedDomainPrimitive<Byte> {

  protected ByteBasedDomainPrimitive(final Optional<Byte> value) {
    super(value);
  }
}
