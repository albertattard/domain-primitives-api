package com.javacreed.api.domain.primitives.mandatory;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ZonedDateTimeBasedDomainPrimitive extends ObjectBasedDomainPrimitive<ZonedDateTime>
    implements Comparable<ZonedDateTimeBasedDomainPrimitive> {

  private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm (z)");

  protected ZonedDateTimeBasedDomainPrimitive(final ZonedDateTime value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final ZonedDateTimeBasedDomainPrimitive other) {
    return compareTo(other, ZonedDateTime::compareTo);
  }

  public String format(final DateTimeFormatter formatter) throws NullPointerException {
    Preconditions.checkNotNull(formatter);
    return map(formatter::format);
  }

  public String format(final DateTimeFormatter formatter, final ZoneId zoneId) throws NullPointerException {
    Preconditions.checkNotNull(formatter);
    Preconditions.checkNotNull(zoneId);
    return formatter.format(get().withZoneSameInstant(zoneId));
  }

  public String format(final String pattern) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(pattern);
    return format(DateTimeFormatter.ofPattern(pattern));
  }

  public String format(final String pattern, final ZoneId zoneId) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(pattern);
    Preconditions.checkNotNull(zoneId);
    return format(DateTimeFormatter.ofPattern(pattern), zoneId);
  }

  public Month getMonth() {
    return value.getMonth();
  }

  public Year getYear() {
    return Year.of(value.getYear());
  }

  public ZoneId getZone() {
    return value.getZone();
  }

  /**
   * Checks if the instant of this date-time is after that of the specified date-time.
   * <p/>
   * This method differs from the comparison in {@link #compareTo} in that it only compares the instant of the
   * date-time. This is equivalent to using {@code dateTime1.toInstant().isAfter(dateTime2.toInstant());}.
   * <p/>
   * This default implementation performs the comparison based on the epoch-second and nano-of-second.
   *
   * @param other
   *          the other date-time to compare to (which cannot be <code>null</code>)
   * @return true if this is after the specified date-time
   * @throws NullPointerException
   *           if the given time is <code>null</code>
   */
  public boolean isAfter(final ZonedDateTimeBasedDomainPrimitive other) {
    return value.isAfter(other.value);
  }

  public boolean isEqual(final ZonedDateTimeBasedDomainPrimitive other) {
    return value.isEqual(other.value);
  }

  public boolean isInFuture() {
    return value.isAfter(ZonedDateTime.now(value.getZone()));
  }

  public boolean isInPast() {
    return value.isBefore(ZonedDateTime.now(value.getZone()));
  }

  public boolean sameValueUpToSecond(final Supplier<ZonedDateTime> supplier) {
    Preconditions.checkNotNull(supplier);
    return sameValueUpToSecond(supplier.get());
  }

  public boolean sameValueUpToSecond(final ZonedDateTime other) {
    return similarValueUpToSecond(other, 0);
  }

  public boolean similarValueUpToSecond(final Supplier<ZonedDateTime> supplier, final int deltaInSeconds) {
    Preconditions.checkNotNull(supplier);
    return similarValueUpToSecond(supplier.get(), deltaInSeconds);
  }

  public boolean similarValueUpToSecond(final ZonedDateTime other, final int deltaInSeconds) {
    Preconditions.checkNotNull(other);
    Preconditions.checkArgument(deltaInSeconds >= 0);

    final long a = TimeUnit.MILLISECONDS.toSeconds(toEpochMilli());
    final long b = TimeUnit.MILLISECONDS.toSeconds(other.withZoneSameInstant(getZone()).toInstant().toEpochMilli());

    return Math.abs(a - b) <= deltaInSeconds;
  }

  public long toEpochMilli() {
    return value.toInstant().toEpochMilli();
  }

  public String toFormattedString() {
    return format(ZonedDateTimeBasedDomainPrimitive.DEFAULT_FORMATTER);
  }

  /**
   * Returns the timestamp from this time instant
   *
   * @return the timestamp from this time instant
   * @see #toUtcSqlTimestamp()
   */
  public Timestamp toSqlTimestamp() {
    return Timestamp.from(value.toInstant());
  }

  public LocalDate toUtcLocalDate() {
    return value.withZoneSameInstant(ZoneOffset.UTC).toLocalDate();
  }

  public LocalDateTime toUtcLocalDateTime() {
    return value.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
  }

  /**
   * Returns the timestamp at the UTC time zone. This is equivalent as creating the
   * {@link Timestamp#valueOf(LocalDateTime)} and passing the {@link #toUtcLocalDateTime()} as parameter
   *
   * @return the timestamo at the UTC time zone
   * @see #toUtcLocalDateTime()
   * @see Timestamp#valueOf(LocalDateTime)
   */
  public Timestamp toUtcSqlTimestamp() {
    return Timestamp.valueOf(toUtcLocalDateTime());
  }

  public Date toUtilDate() {
    return Date.from(value.toInstant());
  }
}
