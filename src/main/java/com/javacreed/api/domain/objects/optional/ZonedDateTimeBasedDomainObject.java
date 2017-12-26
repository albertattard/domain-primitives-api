package com.javacreed.api.domain.objects.optional;

import java.sql.Timestamp;
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

  public Optional<String> format(final DateTimeFormatter formatter) throws NullPointerException {
    Preconditions.checkNotNull(formatter);
    return map(formatter::format);
  }

  public Optional<String> format(final String pattern) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(pattern);
    return format(DateTimeFormatter.ofPattern(pattern));
  }

  public boolean isEqual(final ZonedDateTimeBasedDomainObject other) {
    return other.isValuePresent() && map(v -> v.isEqual(other.getNullable())).orElse(false);
  }

  public Optional<Boolean> isInPast() {
    return value.map(v -> v.isBefore(ZonedDateTime.now(v.getZone())));
  }

  public Optional<String> toFormattedString() {
    return format(ZonedDateTimeBasedDomainObject.DEFAULT_FORMATTER);
  }

  public Optional<LocalDateTime> toUtcLocalDateTime() {
    return value.map(z -> z.withZoneSameInstant(ZonedDateTimeBasedDomainObject.ZONE_UTC))
                .map(ZonedDateTime::toLocalDateTime);
  }

  /**
   * Returns the timestamp at the UTC time zone. This is equivalent as creating the
   * {@link Timestamp#valueOf(LocalDateTime)} and passing the {@link #toUtcLocalDateTime()} as parameter
   *
   * @return the timestamo at the UTC time zone
   * @see #toUtcLocalDateTime()
   * @see Timestamp#valueOf(LocalDateTime)
   */
  public Optional<Timestamp> toUtcTimestamp() {
    return toUtcLocalDateTime().map(Timestamp::valueOf);
  }

}
