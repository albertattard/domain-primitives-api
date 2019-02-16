package com.javacreed.api.domain.primitives.mandatory;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class LocalDateBasedDomainPrimitive extends ObjectBasedDomainPrimitive<LocalDate>
    implements Comparable<LocalDateBasedDomainPrimitive> {

  private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  protected LocalDateBasedDomainPrimitive(final LocalDate value) throws NullPointerException {
    super(value);
  }

  public Date asSqlDate() {
    return map(Date::valueOf);
  }

  public String asString() {
    return format();
  }

  @Override
  public int compareTo(final LocalDateBasedDomainPrimitive other) {
    return compareTo(other, LocalDate::compareTo);
  }

  public String format() {
    return format(LocalDateBasedDomainPrimitive.DEFAULT_FORMATTER);
  }

  public String format(final DateTimeFormatter formatter) throws NullPointerException {
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
  public String format(final String pattern) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(pattern);
    return format(DateTimeFormatter.ofPattern(pattern));
  }

  public int getYear() {
    return value.getYear();
  }

  public boolean isAfter(final LocalDateBasedDomainPrimitive other) {
    return value.isAfter(other.value);
  }

  public boolean isBefore(final LocalDateBasedDomainPrimitive other) {
    return value.isBefore(other.value);
  }

  public boolean isSameOrBefore(final LocalDateBasedDomainPrimitive other) {
    return value.isEqual(other.value) || value.isBefore(other.value);
  }
}
