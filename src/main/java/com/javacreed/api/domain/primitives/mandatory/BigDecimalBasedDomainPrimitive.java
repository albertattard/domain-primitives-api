package com.javacreed.api.domain.primitives.mandatory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class BigDecimalBasedDomainPrimitive extends ObjectBasedDomainPrimitive<BigDecimal>
    implements Comparable<BigDecimalBasedDomainPrimitive> {
  private static final NumberFormat DEFAULT_FORMAT = NumberFormat.getNumberInstance();

  protected BigDecimalBasedDomainPrimitive(final BigDecimal value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final BigDecimalBasedDomainPrimitive other) {
    return compareTo(other, BigDecimal::compareTo);
  }

  public String format() {
    return format(BigDecimalBasedDomainPrimitive.DEFAULT_FORMAT);
  }

  public String format(final NumberFormat format) throws NullPointerException {
    Preconditions.checkNotNull(format);
    return map(format::format);
  }

  public String format(final String pattern) throws NullPointerException, IllegalArgumentException {
    return format(new DecimalFormat(pattern));
  }

  public boolean hasLargerValue(final BigDecimal value) {
    return this.value.compareTo(value) > 0;
  }

  public boolean hasSameValue(final BigDecimal value) throws NullPointerException {
    return this.value.compareTo(value) == 0;
  }

  public boolean hasSmallerValue(final BigDecimal value) {
    return this.value.compareTo(value) < 0;
  }
}
