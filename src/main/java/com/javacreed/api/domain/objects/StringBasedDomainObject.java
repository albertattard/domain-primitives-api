package com.javacreed.api.domain.objects;

import java.util.Comparator;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class StringBasedDomainObject extends DomainObject<String> implements Comparable<StringBasedDomainObject> {

  public static final Comparator<StringBasedDomainObject> CASE_INSENSITIVE = (a, b) -> a.compareToIgnoreCase(b);

  protected StringBasedDomainObject(final Optional<String> value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final StringBasedDomainObject other) {
    return compareTo(other, (a, b) -> a.compareTo(b));
  }

  protected int compareTo(final StringBasedDomainObject other, final Comparator<String> comparator) {
    final String a = getNullable();
    final String b = other.getNullable();

    if (a == b) {
      return 0;
    }

    if (a == null) {
      return -1;
    }

    if (b == null) {
      return 1;
    }

    return comparator.compare(a, b);

  }

  public int compareToIgnoreCase(final StringBasedDomainObject other) {
    return compareTo(other, (a, b) -> a.compareToIgnoreCase(b));
  }
}
