package com.javacreed.api.domain.primitives.utils;

import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.regex.Pattern;

import com.google.common.base.Preconditions;

/**
 * A utilities class that provides stateless methods related to {@link UUID}
 *
 * @author Albert Attard
 * @see UUID
 */
public class UuidUtils {

  private static final Pattern REGEX = Pattern
                                              .compile(
                                                       "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

  public static String checkArgument(final String value) throws NullPointerException, IllegalArgumentException {
    return UuidUtils.checkArgument(value, "UUID value");
  }

  public static String checkArgument(final String value, final String name)
      throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(value, name + " is null");
    Preconditions.checkArgument(value.length() == 36,
                                name + " is of invalid length.  Expected 36 characters but found %s characters '%s'",
                                value.length(), value);
    Preconditions.checkArgument(UuidUtils.REGEX.matcher(value).matches(),
                                name + " contains invalid letters or is in invalid format");
    return value;
  }

  public static boolean isValid(final String value) {
    if (value == null || value.length() != 36) {
      return false;
    }

    return UuidUtils.REGEX.matcher(value).matches();
  }

  /**
   * Converts the given UUID to bytes. Note that this is incompatible with the {@link UUID#nameUUIDFromBytes()}. The
   * returned bytes array can be converted back to UUID using the {@link UuidUtils#toUuid(byte[])} method defined within
   * this class.
   *
   * @param uuid
   *          the UUID to convert (which cannot be <code>null</code>)
   * @return the converted (incompatible with the {@link UUID#nameUUIDFromBytes()}) bytes
   * @throws NullPointerException
   *           if the given <code>uuid</code> is <code>null</code>
   * @see UuidUtils#toUuid(byte[])
   */
  public static byte[] toBytes(final UUID uuid) throws NullPointerException {
    final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
    buffer.putLong(uuid.getMostSignificantBits());
    buffer.putLong(uuid.getLeastSignificantBits());
    return buffer.array();
  }

  /**
   * Converts the given bytes array to {@link UUID}. This method is compatible with the {@link UuidUtils#toBytes()}
   * method.
   *
   * @param bytes
   *          the bytes array (which cannot be <code>null</code> and must be of length 16)
   * @return
   * @throws NullPointerException
   *           if the given <code>bytes</code> are <code>null</code>
   * @throws IllegalArgumentException
   *           if the given <code>bytes</code> are not 16 elements long
   * @see UuidUtils#toBytes(UUID)
   */
  public static UUID toUuid(final byte[] bytes) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(bytes);
    Preconditions.checkArgument(bytes.length == 16, "The byte array needs to be 16 elements long");
    final ByteBuffer buffer = ByteBuffer.wrap(bytes);
    return new UUID(buffer.getLong(), buffer.getLong());
  }

  private UuidUtils() {}
}
