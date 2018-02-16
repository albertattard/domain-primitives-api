package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.concurrent.Immutable;

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

  public Optional<String> toFormattedString() {
    return map(UUID::toString);
  }

  /**
   * This method is a shortcut to <code>getBytes().orElse(null)</code>
   * <p>
   * Converts the UUID to bytes. Note that this is incompatible with the {@link UUID#nameUUIDFromBytes()}. The returned
   * bytes array can be converted back to UUID using the {@link UuidUtils#toUuid(byte[])} method defined within this
   * class.
   *
   * @return the converted (incompatible with the {@link UUID#nameUUIDFromBytes()}) bytes if available, otherwise
   *         <code>null</code>
   * @see #toBytes()
   */
  public byte[] toNullableBytes() {
    return toBytes().orElse(null);
  }

  public String toNullableFormattedString() {
    return toFormattedString().orElse(null);
  }
}
