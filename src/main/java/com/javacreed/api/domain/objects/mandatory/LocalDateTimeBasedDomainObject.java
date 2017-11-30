package com.javacreed.api.domain.objects.mandatory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class LocalDateTimeBasedDomainObject extends ObjectBasedDomainObject<LocalDateTime>
    implements Comparable<LocalDateTimeBasedDomainObject> {

  private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  protected LocalDateTimeBasedDomainObject(final LocalDateTime value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final LocalDateTimeBasedDomainObject other) {
    return compareTo(other, LocalDateTime::compareTo);
  }

  public String format() {
    return format(LocalDateTimeBasedDomainObject.DEFAULT_FORMATTER);
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
}
