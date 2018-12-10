package com.javacreed.api.domain.primitives.optional;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;
import com.javacreed.api.domain.primitives.utils.UuidUtils;

@Immutable
public class UuidBasedDomainPrimitive extends ComparableBasedDomainPrimitive<UUID> {

  protected UuidBasedDomainPrimitive(final Optional<UUID> value) throws NullPointerException {
    super(value);
  }

  /**
   * Converts the UUID to bytes. Note that this is incompatible with the {@link UUID#nameUUIDFromBytes()}. The returned
   * bytes array can be converted back to UUID using the {@link UuidUtils#toUuid(byte[])} method defined within this
   * class.
   *
   * @return the converted (incompatible with the {@link UUID#nameUUIDFromBytes()}) bytes
   * @see UuidUtils#toBytes(UUID)
   */
  public Optional<byte[]> toBytes() {
    return map(UuidUtils::toBytes);
  }

  /**
   * This method is a shortcut to <code>getBytes().orElse(null)</code>
   * <p>
   * Converts the UUID to bytes. Note that this is incompatible with the {@link UUID#nameUUIDFromBytes()}. The returned
   * bytes array can be converted back to UUID using the {@link UuidUtils#toUuid(byte[])} method defined within this
   * class.
   *
   * @return the converted (incompatible with the {@link UUID#nameUUIDFromBytes()}) bytes if available, otherwise
   *         {@code null}
   * @see #toBytes()
   */
  public byte[] toBytesOrNull() {
    return toBytes().orElse(null);
  }

  public Optional<String> toFormattedString() {
    return map(UUID::toString);
  }

  public String toFormattedStringOrNull() {
    return toFormattedString().orElse(null);
  }

  /**
   * Compares the given byte array to the byte equivalent value of this UUID ({@link #toBytes()}) and returns
   * {@code true}, if the value is set and the given byte array has the same content as this UUID byte value,
   * {@code false} otherwise.
   * 
   * @param other
   *          the array to compare to (which cannot be {@code null})
   * @return {@code true} if the given array has the same byte value of this UUID, {@code false} otherwise
   * @throws NullPointerException
   *           if the given parameter is {@code null}
   */
  public boolean sameValue(byte[] other) {
    Preconditions.checkNotNull(other);
    return toBytes().map(b -> Arrays.equals(b, other)).orElse(false);
  }
}
