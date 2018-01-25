package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class CharacterBasedDomainPrimitive extends ComparableBasedDomainPrimitive<Character> {

  protected CharacterBasedDomainPrimitive(final Optional<Character> value) {
    super(value);
  }
}
