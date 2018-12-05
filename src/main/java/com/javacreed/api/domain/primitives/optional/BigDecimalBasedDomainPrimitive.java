package com.javacreed.api.domain.primitives.optional;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class BigDecimalBasedDomainPrimitive extends ObjectBasedDomainPrimitive<BigDecimal>
    implements Comparable<BigDecimalBasedDomainPrimitive> {

  private static final NumberFormat DEFAULT_FORMAT = NumberFormat.getNumberInstance();

  protected BigDecimalBasedDomainPrimitive(final Optional<BigDecimal> value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final BigDecimalBasedDomainPrimitive other) {
    return compareTo(other, BigDecimal::compareTo);
  }

  public Optional<String> format() {
    return format(BigDecimalBasedDomainPrimitive.DEFAULT_FORMAT);
  }

  public Optional<String> format(final NumberFormat format) throws NullPointerException {
    Preconditions.checkNotNull(format);
    return map(format::format);
  }

  public Optional<Boolean> hasSameValue(final BigDecimal value) throws NullPointerException {
    return map(v -> v.compareTo(value) == 0);
  }
}
