package com.javacreed.api.domain.objects.optional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

@Immutable
public class LocalDateTimeBasedDomainObject extends ObjectBasedDomainObject<LocalDateTime>
    implements Comparable<LocalDateTimeBasedDomainObject> {

  private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  protected LocalDateTimeBasedDomainObject(final Optional<LocalDateTime> value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final LocalDateTimeBasedDomainObject other) {
    return compareTo(other, LocalDateTime::compareTo);
  }

  public Optional<String> format(final DateTimeFormatter formatter) {
    return map(formatter::format);
  }

  public Optional<String> formatted() {
    return format(LocalDateTimeBasedDomainObject.DEFAULT_FORMATTER);
  }

}
