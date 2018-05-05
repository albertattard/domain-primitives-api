package com.javacreed.api.domain.primitives.optional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ZonedDateTimeBasedDomainPrimitive extends ObjectBasedDomainPrimitive<ZonedDateTime>
    implements Comparable<ZonedDateTimeBasedDomainPrimitive> {

  private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm (z)");

  private static final ZoneId ZONE_UTC = ZoneId.of("UTC");

  protected ZonedDateTimeBasedDomainPrimitive(final Optional<ZonedDateTime> value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final ZonedDateTimeBasedDomainPrimitive other) {
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

  public boolean isEqual(final ZonedDateTimeBasedDomainPrimitive other) {
    return other.isValuePresent() && map(v -> v.isEqual(other.orNull())).orElse(false);
  }

  public Optional<Boolean> isInFuture() {
    return value.map(v -> v.isAfter(ZonedDateTime.now(v.getZone())));
  }

  public Optional<Boolean> isInPast() {
    return value.map(v -> v.isBefore(ZonedDateTime.now(v.getZone())));
  }

  public Optional<String> toFormattedString() {
    return format(ZonedDateTimeBasedDomainPrimitive.DEFAULT_FORMATTER);
  }

  public Optional<Timestamp> toSqlTimestamp() {
    return map(v -> v.toInstant()).map(Timestamp::from);
  }

  public Timestamp toSqlTimestampOrNull() {
    return toSqlTimestamp().orElse(null);
  }

  public Optional<LocalDateTime> toUtcLocalDateTime() {
    return value.map(z -> z.withZoneSameInstant(ZonedDateTimeBasedDomainPrimitive.ZONE_UTC))
                .map(ZonedDateTime::toLocalDateTime);
  }

  public LocalDateTime toUtcLocalDateTimeOrNull() {
    return toUtcLocalDateTime().orElse(null);
  }

  /**
   * Returns the timestamp at the UTC time zone. This is equivalent as creating the
   * {@link Timestamp#valueOf(LocalDateTime)} and passing the {@link #toUtcLocalDateTime()} as parameter
   *
   * @return the timestamo at the UTC time zone
   * @see #toUtcLocalDateTime()
   * @see Timestamp#valueOf(LocalDateTime)
   */
  public Optional<Timestamp> toUtcSqlTimestamp() {
    return toUtcLocalDateTime().map(Timestamp::valueOf);
  }

  public Timestamp toUtcSqlTimestampOrNull() {
    return toUtcSqlTimestamp().orElse(null);
  }
}
