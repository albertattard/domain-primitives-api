package com.javacreed.api.domain.objects.optional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ZonedDateTimeBasedDomainObject extends ObjectBasedDomainObject<ZonedDateTime>
    implements Comparable<ZonedDateTimeBasedDomainObject> {

  private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

  private static final ZoneId ZONE_UTC = ZoneId.of("UTC");

  protected ZonedDateTimeBasedDomainObject(final Optional<ZonedDateTime> value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final ZonedDateTimeBasedDomainObject other) {
    return compareTo(other, ZonedDateTime::compareTo);
  }

  public Optional<String> format() {
    return format(ZonedDateTimeBasedDomainObject.DEFAULT_FORMATTER);
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

  public Optional<LocalDateTime> toUtcLocalDateTime() {
    return value.map(z -> z.withZoneSameInstant(ZonedDateTimeBasedDomainObject.ZONE_UTC))
                .map(ZonedDateTime::toLocalDateTime);
  }
}
