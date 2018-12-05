package com.javacreed.api.domain.primitives.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.function.Function;

import com.google.common.base.Preconditions;

public class BigDecimalUtils {

  public static BigDecimal parseValue(final String value, final Function<Exception, String> errorHandler)
      throws NullPointerException, IllegalArgumentException {
    return BigDecimalUtils.parseValue(value, "#,###.00", errorHandler);
  }

  public static BigDecimal parseValue(final String value, final String pattern, final Function<Exception, String> errorTransformation)
      throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(value);
    Preconditions.checkNotNull(pattern);
    Preconditions.checkNotNull(errorTransformation);

    try {
      final DecimalFormat format = new DecimalFormat(pattern);
      format.setParseBigDecimal(true);
      return (BigDecimal) format.parse(value);
    } catch (final Exception e) {
      throw new IllegalArgumentException(errorTransformation.apply(e));
    }
  }

  private BigDecimalUtils() {};
}
