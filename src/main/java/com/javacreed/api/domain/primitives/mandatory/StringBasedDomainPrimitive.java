package com.javacreed.api.domain.primitives.mandatory;

import java.util.Comparator;

import javax.annotation.concurrent.Immutable;

@Immutable
public class StringBasedDomainPrimitive extends ComparableBasedDomainPrimitive<String> {

  public static final Comparator<StringBasedDomainPrimitive> CASE_INSENSITIVE = (a, b) -> a.compareToIgnoreCase(b);

  protected StringBasedDomainPrimitive(final String value) throws NullPointerException {
    super(value);
  }

  public int compareToIgnoreCase(final StringBasedDomainPrimitive other) {
    return compareTo(other, (a, b) -> a.compareToIgnoreCase(b));
  }

  public boolean equalsIgnoreCase(final StringBasedDomainPrimitive other) {
    return value.equalsIgnoreCase(other.value);
  }
}
