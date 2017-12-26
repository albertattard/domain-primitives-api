package com.javacreed.api.domain.objects.mandatory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ZonedDateTimeBasedDomainObject extends ObjectBasedDomainObject<ZonedDateTime>
    implements Comparable<ZonedDateTimeBasedDomainObject> {

  private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

  private static final ZoneId ZONE_UTC = ZoneId.of("UTC");

  protected ZonedDateTimeBasedDomainObject(final ZonedDateTime value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final ZonedDateTimeBasedDomainObject other) {
    return compareTo(other, ZonedDateTime::compareTo);
  }

  public String format(final DateTimeFormatter formatter) throws NullPointerException {
    Preconditions.checkNotNull(formatter);
    return map(formatter::format);
  }

  public String format(final String pattern) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(pattern);
    return format(DateTimeFormatter.ofPattern(pattern));
  }

  public boolean isEqual(final ZonedDateTimeBasedDomainObject other) {
    return value.isEqual(other.value);
  }

  public boolean isInPast() {
    return value.isBefore(ZonedDateTime.now(value.getZone()));
  }

  public String toFormattedString() {
    return format(ZonedDateTimeBasedDomainObject.DEFAULT_FORMATTER);
  }

  public LocalDateTime toUtcLocalDateTime() {
    return value.withZoneSameInstant(ZonedDateTimeBasedDomainObject.ZONE_UTC).toLocalDateTime();
  }

  /**
   * Returns the timestamp at the UTC time zone. This is equivalent as creating the
   * {@link Timestamp#valueOf(LocalDateTime)} and passing the {@link #toUtcLocalDateTime()} as parameter
   *
   * @return the timestamo at the UTC time zone
   * @see #toUtcLocalDateTime()
   * @see Timestamp#valueOf(LocalDateTime)
   */
  public Timestamp toUtcTimestamp() {
    return Timestamp.valueOf(toUtcLocalDateTime());
  }
}
