package com.javacreed.api.domain.primitives.optional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class LocalDateBasedDomainPrimitive extends ObjectBasedDomainPrimitive<LocalDate>
    implements Comparable<LocalDateBasedDomainPrimitive> {

  private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  protected LocalDateBasedDomainPrimitive(final Optional<LocalDate> value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final LocalDateBasedDomainPrimitive other) {
    return compareTo(other, LocalDate::compareTo);
  }

  public Optional<String> format() {
    return format(LocalDateBasedDomainPrimitive.DEFAULT_FORMATTER);
  }

  public Optional<String> format(final DateTimeFormatter formatter) throws NullPointerException {
    Preconditions.checkNotNull(formatter);
    return map(formatter::format);
  }

  /**
   *
   * @param pattern
   * @return
   * @throws NullPointerException
   * @throws IllegalArgumentException
   *           if the pattern is invalid
   */
  public Optional<String> format(final String pattern) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(pattern);
    return format(DateTimeFormatter.ofPattern(pattern));
  }

  public Optional<Date> toSqlDate() {
    return map(Date::valueOf);
  }

  public Date toSqlDateOrNull() {
    return toSqlDate().orElse(null);
  }
}
