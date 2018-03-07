package com.javacreed.api.domain.primitives.mandatory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.function.Function;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class BigDecimalBasedDomainPrimitive extends ObjectBasedDomainPrimitive<BigDecimal>
    implements Comparable<BigDecimalBasedDomainPrimitive> {

  private static final NumberFormat DEFAULT_FORMAT = NumberFormat.getNumberInstance();

  public static BigDecimal parseValue(final String value,
      final Function<Exception, String> errorHandler)
      throws IllegalArgumentException {
    return BigDecimalBasedDomainPrimitive.parseValue(value, "#,###.00", errorHandler);
  }

  public static BigDecimal parseValue(final String value, final String pattern,
      final Function<Exception, String> errorTransformation) throws IllegalArgumentException {
    try {
      final DecimalFormat format = new DecimalFormat(pattern);
      format.setParseBigDecimal(true);
      return (BigDecimal) format.parse(value);
    } catch (final Exception e) {
      throw new IllegalArgumentException(errorTransformation.apply(e));
    }
  }

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
