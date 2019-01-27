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

  /* The REGEX pattern representing a valid V4 UUID */
  private static final Pattern REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

  /**
   * Converts the given UUID to bytes. Note that this is incompatible with the {@link UUID#nameUUIDFromBytes()}. The
   * returned bytes array can be converted back to UUID using the {@link UuidUtils#asUuid(byte[])} method defined within
   * this class.
   *
   * @param uuid
   *          the UUID to convert (which cannot be <code>null</code>)
   * @return the converted (incompatible with the {@link UUID#nameUUIDFromBytes()}) bytes
   * @throws NullPointerException
   *           if the given <code>uuid</code> is <code>null</code>
   * @see UuidUtils#asUuid(byte[])
   */
  public static byte[] asBytes(final UUID uuid) {
    final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
    buffer.putLong(uuid.getMostSignificantBits());
    buffer.putLong(uuid.getLeastSignificantBits());
    return buffer.array();
  }

  /**
   * Converts the given bytes array to {@link UUID}. This method is compatible with the {@link UuidUtils#asBytes()}
   * method.
   *
   * @param bytes
   *          the bytes array (which cannot be <code>null</code> and must be of length 16)
   * @return the UUID
   * @throws NullPointerException
   *           if the given <code>bytes</code> are <code>null</code>
   * @throws IllegalArgumentException
   *           if the given <code>bytes</code> are not 16 elements long
   * @see UuidUtils#asBytes(UUID)
   */
  public static UUID asUuid(final byte[] bytes) {
    Preconditions.checkNotNull(bytes);
    Preconditions.checkArgument(bytes.length == 16, "The byte array needs to be 16 elements long");
    final ByteBuffer buffer = ByteBuffer.wrap(bytes);
    return new UUID(buffer.getLong(), buffer.getLong());
  }

  /**
   * Checks the given UUID string and returns the same value only if it is valid. A {@link NullPointerException} or
   * {@link IllegalArgumentException} is thrown if the given UUID is <code>null</code> or not a valid UUID string.
   *
   * @param value
   *          the UUID to be checked (which needs to be a valid UUID string and cannot be <code>null</code>)
   * @return the given UUID value only if is valid
   * @throws NullPointerException
   *           if the given <code>value</code> is <code>null</code>
   * @throws IllegalArgumentException
   *           if the given <code>value</code> is not a valid UUID string
   */
  public static String checkArgument(final String value) {
    return UuidUtils.checkArgument(value, "UUID value");
  }

  /**
   * Checks the given UUID string and returns the same value only if it is valid. A {@link NullPointerException} or
   * {@link IllegalArgumentException} is thrown if the given UUID is <code>null</code> or not a valid UUID string.
   *
   * @param value
   *          the UUID to be checked (which needs to be a valid UUID string and cannot be <code>null</code>)
   * @param name
   *          the name to be used in the error message (which cannot be <code>null</code>)
   * @return the given UUID value only if is valid
   * @throws NullPointerException
   *           if the given <code>value</code> is <code>null</code>
   * @throws IllegalArgumentException
   *           if the given <code>value</code> is not a valid UUID string
   */
  public static String checkArgument(final String value, final String name) {
    Preconditions.checkNotNull(value, name + " cannot be null");
    Preconditions.checkArgument(value.length() == 36, name + " is of invalid length.  Expected 36 characters but found %s characters '%s'", value.length(),
                                value);
    Preconditions.checkArgument(UuidUtils.REGEX.matcher(value).matches(), name + " contains invalid letters or is in invalid format");
    return value;
  }

  /**
   * Returns <code>true</code> if the given <code>value</code> is a valid UUID, <code>false</code> otherwise.
   *
   * @param value
   *          the value to be checked (which can be <code>null</code>)
   * @return <code>true</code> if the given <code>value</code> is a valid UUID, <code>false</code> otherwise
   */
  public static boolean isValid(final String value) {
    if (value == null || value.length() != 36) {
      return false;
    }

    return UuidUtils.REGEX.matcher(value).matches();
  }

  private UuidUtils() {}
}
