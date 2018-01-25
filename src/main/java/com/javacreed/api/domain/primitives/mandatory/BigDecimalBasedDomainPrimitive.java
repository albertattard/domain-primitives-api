package com.javacreed.api.domain.primitives.mandatory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class BigDecimalBasedDomainObject extends ObjectBasedDomainObject<BigDecimal>
    implements Comparable<BigDecimalBasedDomainObject> {

  private static final NumberFormat DEFAULT_FORMAT = NumberFormat.getNumberInstance();

  protected BigDecimalBasedDomainObject(final BigDecimal value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final BigDecimalBasedDomainObject other) {
    return compareTo(other, BigDecimal::compareTo);
  }

  public String format() {
    return format(BigDecimalBasedDomainObject.DEFAULT_FORMAT);
  }

  public String format(final NumberFormat format) throws NullPointerException {
    Preconditions.checkNotNull(format);
    return map(format::format);
  }

  public String format(final String pattern) throws NullPointerException, IllegalArgumentException {
    return format(new DecimalFormat(pattern));
  }
}
