package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class CharacterBasedDomainObject extends ComparableBasedDomainObject<Character> {

  protected CharacterBasedDomainObject(final Optional<Character> value) {
    super(value);
  }
}
