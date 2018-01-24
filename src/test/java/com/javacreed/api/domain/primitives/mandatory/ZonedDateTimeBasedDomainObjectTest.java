package com.javacreed.api.domain.primitives.mandatory;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.javacreed.api.domain.primitives.mandatory.ZonedDateTimeBasedDomainObject;

public class ZonedDateTimeBasedDomainObjectTest {

  private static class Testable extends ZonedDateTimeBasedDomainObject {
    protected Testable(final ZonedDateTime value) throws NullPointerException {
      super(value);
    }
  }

  @Test
  public void inPast() {
    final Set<ZoneId> zones = new LinkedHashSet<>();
    zones.add(ZoneId.of("UTC"));
    zones.add(ZoneId.of("Europe/London"));
    zones.add(ZoneId.of("Europe/Malta"));

    for (final ZoneId zoneId : zones) {
      final ZonedDateTime timeA = ZonedDateTime.now(zoneId).plusMinutes(1);
      Assert.assertFalse(new Testable(timeA).isInPast());

      final ZonedDateTime timeB = ZonedDateTime.now(zoneId).minusMinutes(1);
      Assert.assertTrue(new Testable(timeB).isInPast());
    }
  }

  @Test
  public void isAfter() {
    final Testable a = new Testable(ZonedDateTime.of(2018, 1, 5, 10, 0, 0, 0, ZoneId.of("Europe/Malta")));
    final Testable b = new Testable(ZonedDateTime.of(2018, 1, 5, 9, 30, 0, 0, ZoneId.of("Europe/London")));

    Assert.assertFalse(a.isAfter(b));
    Assert.assertTrue(b.isAfter(a));
  }
}
