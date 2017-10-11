package com.javacreed.api.domain.objects;

import java.util.Comparator;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class StringBasedDomainObject extends ObjectBasedDomainObject<String>
    implements Comparable<StringBasedDomainObject> {

  public static final Comparator<StringBasedDomainObject> CASE_INSENSITIVE = (a, b) -> a.compareToIgnoreCase(b);

  protected StringBasedDomainObject(final Optional<String> value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final StringBasedDomainObject other) {
    return compareTo(other, (a, b) -> a.compareTo(b));
  }

  public int compareToIgnoreCase(final StringBasedDomainObject other) {
    return compareTo(other, (a, b) -> a.compareToIgnoreCase(b));
  }
}
