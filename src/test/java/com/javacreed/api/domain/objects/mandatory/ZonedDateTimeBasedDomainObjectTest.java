package com.javacreed.api.domain.objects.mandatory;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

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
}
