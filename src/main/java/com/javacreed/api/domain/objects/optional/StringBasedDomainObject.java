package com.javacreed.api.domain.objects.optional;

import java.util.Comparator;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class StringBasedDomainObject extends ComparableBasedDomainObject<String> {

  public static final Comparator<StringBasedDomainObject> CASE_INSENSITIVE = (a, b) -> a.compareToIgnoreCase(b);

  protected StringBasedDomainObject(final Optional<String> value) throws NullPointerException {
    super(value);
  }

  public int compareToIgnoreCase(final StringBasedDomainObject other) {
    return compareTo(other, (a, b) -> a.compareToIgnoreCase(b));
  }

  public boolean equalsIgnoreCase(final StringBasedDomainObject other) {
    if (isValuePresent() && other.isValuePresent()) {
      return value.get().equalsIgnoreCase(other.value.get());
    }

    return false;
  }
}
