package com.javacreed.api.domain.primitives.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.function.Function;

import com.google.common.base.Preconditions;

/**
 * A simple utility class that provides stateless methods related to {@link BigDecimal}
 *
 * @author Albert Attard
 */
public class BigDecimalUtils {

  /**
   * Parses the given string value to a {@link BigDecimal} using the default format: {@code #,###.00 } (two decimal
   * places and comma for grouping)
   *
   * @param value
   *          the value to parse (which cannot be {@code null})
   * @param errorTransformation
   *          creates a relative message should the parsing fail (which cannot be {@code null})
   * @return the parse value
   * @throws NullPointerException
   *           if any of the given parameter is {@code null}
   * @throws IllegalArgumentException
   *           if the given value cannot be parsed into a {@link BigDecimal}
   * @see #parseValue(String, String, Function)
   */
  public static BigDecimal parseValue(final String value, final Function<Exception, String> errorTransformation) {
    return BigDecimalUtils.parseValue(value, "#,###.00", errorTransformation);
  }

  /**
   * Parses the given string value to a {@link BigDecimal} using the given format.
   *
   * @param value
   *          the value to parse (which cannot be {@code null})
   * @param pattern
   *          the pattern used (which cannot be {@code null} and must be a valid format)
   * @param errorTransformation
   *          creates a relative message should the parsing fail (which cannot be {@code null})
   * @return the parse value
   * @throws NullPointerException
   *           if any of the given parameter is {@code null}
   * @throws IllegalArgumentException
   *           if the given value cannot be parsed into a {@link BigDecimal}
   */
  public static BigDecimal parseValue(final String value, final String pattern, final Function<Exception, String> errorTransformation) {
    Preconditions.checkNotNull(value);
    Preconditions.checkNotNull(pattern);
    Preconditions.checkNotNull(errorTransformation);

    try {
      final DecimalFormat format = new DecimalFormat(pattern);
      format.setParseBigDecimal(true);
      return (BigDecimal) format.parse(value);
    } catch (final Exception e) {
      /*
       * TODO: we should not simply translate the message, but we should transform the whole exception (from Exception
       * to IllegalArgumentException)
       */
      throw new IllegalArgumentException(errorTransformation.apply(e), e);
    }
  }

  private BigDecimalUtils() {};
}
