package com.javacreed.api.domain.primitives.utils;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

public class UuidUtilsTest {

  /**
   * The UUID utils is not compatible with the {@link UUID#nameUUIDFromBytes(byte[])}} method. Verifies the
   * incompatibility between these two
   *
   * @see UUID#nameUUIDFromBytes(byte[])
   */
  @Test
  public void incompatibility() {
    final UUID expected = UUID.randomUUID();
    final UUID incompatible = UUID.nameUUIDFromBytes(UuidUtils.asBytes(expected));
    Assert.assertNotEquals(expected, incompatible);
  }

  /**
   * Verifies that whatever is converted to bytes can be converted back to the same UUID
   */
  @Test
  public void should_create_the_same_UUID() {
    final UUID expected = UUID.randomUUID();
    final UUID actual = UuidUtils.asUuid(UuidUtils.asBytes(expected));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void should_return_the_given_UUID() {
    final String uuid = "92f53821-01c5-4020-85c7-86cc2f0211ff";
    Assert.assertEquals(uuid, UuidUtils.checkArgument(uuid));
  }

  @Test
  public void should_return_true_when_given_a_value_UUID_otherwise_false() {
    Assert.assertTrue(UuidUtils.isValid(UUID.randomUUID().toString()));

    Assert.assertFalse(UuidUtils.isValid(null));
    Assert.assertFalse(UuidUtils.isValid("123456789-123456789-123456789-12345"));
    Assert.assertFalse(UuidUtils.isValid("123456789-123456789-123456789-1234567"));
    Assert.assertFalse(UuidUtils.isValid("0123456-89ab-cdef-0123-456789abcdef0"));
    Assert.assertFalse(UuidUtils.isValid("01234567-89a-cdef-0123-456789abcdef0"));
    Assert.assertFalse(UuidUtils.isValid("01234567-89ab-cde-0123-456789abcdef0"));
    Assert.assertFalse(UuidUtils.isValid("01234567-89ab-cdef-012-456789abcdef0"));
  }

  @Test
  public void should_throw_a_NullPointerException_when_given_null() {
    Assert.assertThrows(NullPointerException.class, () -> UuidUtils.asUuid(null));
    Assert.assertThrows(NullPointerException.class, () -> UuidUtils.checkArgument(null));
  }

  @Test
  public void should_throw_an_IllegalArgumentException_when_given_an_invalid_input() {
    Assert.assertThrows(IllegalArgumentException.class, () -> UuidUtils.asUuid(new byte[10]));

    Assert.assertThrows(IllegalArgumentException.class, () -> UuidUtils.checkArgument(""));
    Assert.assertThrows(IllegalArgumentException.class, () -> UuidUtils.checkArgument("123456789_123456789_123456789_123456"));
  }
}
