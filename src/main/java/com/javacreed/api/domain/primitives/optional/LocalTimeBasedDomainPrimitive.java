package com.javacreed.api.domain.primitives.optional;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class LocalTimeBasedDomainPrimitive extends ObjectBasedDomainPrimitive<LocalTime>
    implements Comparable<LocalTimeBasedDomainPrimitive> {

  private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

  protected LocalTimeBasedDomainPrimitive(final Optional<LocalTime> value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final LocalTimeBasedDomainPrimitive other) {
    return compareTo(other, LocalTime::compareTo);
  }

  public Optional<String> format() {
    return format(LocalTimeBasedDomainPrimitive.DEFAULT_FORMATTER);
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

  public Optional<Time> toSqlTime() {
    return map(Time::valueOf);
  }

  public Time toSqlTimeOrNull() {
    return toSqlTime().orElse(null);
  }

}
