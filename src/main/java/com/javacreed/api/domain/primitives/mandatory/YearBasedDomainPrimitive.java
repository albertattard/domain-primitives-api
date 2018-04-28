package com.javacreed.api.domain.primitives.mandatory;

import java.time.Year;

import javax.annotation.concurrent.Immutable;

@Immutable
public class YearBasedDomainPrimitive extends ObjectBasedDomainPrimitive<Year>
    implements Comparable<YearBasedDomainPrimitive> {

  protected YearBasedDomainPrimitive(final Year value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final YearBasedDomainPrimitive other) {
    return compareTo(other, Year::compareTo);
  }
}
