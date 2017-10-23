package com.javacreed.api.domain.objects.utils;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

public class UuidUtilsTest {

  /**
   * The UUID utils is not compatible with the {@link UUID#nameUUIDFromBytes()} method. Verifies the incompatibility
   * between these two
   *
   * @see UUID#nameUUIDFromBytes(byte[])
   */
  @Test
  public void incompatibility() {
    final UUID expected = UUID.randomUUID();
    final UUID incompatible = UUID.nameUUIDFromBytes(UuidUtils.toBytes(expected));
    Assert.assertNotEquals(expected, incompatible);
  }

  /**
   * Verifies that whatever is converted to bytes can be converted back to the same UUID
   */
  @Test
  public void readWrite() {
    final UUID expected = UUID.randomUUID();
    final UUID actual = UuidUtils.toUuid(UuidUtils.toBytes(expected));
    Assert.assertEquals(expected, actual);
  }
}
