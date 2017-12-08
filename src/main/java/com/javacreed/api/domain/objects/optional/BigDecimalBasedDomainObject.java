package com.javacreed.api.domain.objects.optional;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class BigDecimalBasedDomainObject extends ObjectBasedDomainObject<BigDecimal>
    implements Comparable<BigDecimalBasedDomainObject> {

  private static final NumberFormat DEFAULT_FORMAT = NumberFormat.getNumberInstance();

  protected BigDecimalBasedDomainObject(final Optional<BigDecimal> value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final BigDecimalBasedDomainObject other) {
    return compareTo(other, BigDecimal::compareTo);
  }

  public Optional<String> format() {
    return format(BigDecimalBasedDomainObject.DEFAULT_FORMAT);
  }

  public Optional<String> format(final NumberFormat format) throws NullPointerException {
    Preconditions.checkNotNull(format);
    return map(format::format);
  }
}
