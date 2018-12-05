package com.javacreed.api.domain.primitives.optional;

import java.util.Comparator;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class StringBasedDomainPrimitive extends ComparableBasedDomainPrimitive<String> {

  public static final Comparator<StringBasedDomainPrimitive> CASE_INSENSITIVE = (a, b) -> a.compareToIgnoreCase(b);

  protected StringBasedDomainPrimitive(final Optional<String> value) throws NullPointerException {
    super(value);
  }

  protected StringBasedDomainPrimitive(final String value) throws NullPointerException {
    super(value);
  }

  public int compareToIgnoreCase(final StringBasedDomainPrimitive other) {
    return compareTo(other, (a, b) -> a.compareToIgnoreCase(b));
  }

  public boolean equalsIgnoreCase(final StringBasedDomainPrimitive other) {
    return value.map(a -> other.map(b -> a.equalsIgnoreCase(b)).orElse(false)).orElse(false);
  }

  public String orBlank() {
    return orElse("");
  }
}
