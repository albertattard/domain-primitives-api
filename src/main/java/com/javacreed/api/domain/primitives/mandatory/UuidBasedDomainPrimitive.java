package com.javacreed.api.domain.primitives.mandatory;

import java.util.Arrays;
import java.util.UUID;

import javax.annotation.concurrent.Immutable;

import com.javacreed.api.domain.primitives.utils.UuidUtils;

@Immutable
public class UuidBasedDomainPrimitive extends ComparableBasedDomainPrimitive<UUID> {

  protected UuidBasedDomainPrimitive(final UUID value) throws NullPointerException {
    super(value);
  }

  public boolean sameValue(final byte[] bytes) {
    return bytes != null && bytes.length == 16 && Arrays.equals(toBytes(), bytes);
  }

  /**
   * Converts the UUID to bytes. Note that this is incompatible with the {@link UUID#nameUUIDFromBytes()}. The returned
   * bytes array can be converted back to UUID using the {@link UuidUtils#toUuid(byte[])} method defined within this
   * class.
   *
   * @return the converted (incompatible with the {@link UUID#nameUUIDFromBytes()}) bytes
   * @see UuidUtils#toBytes(UUID)
   */
  public byte[] toBytes() {
    return map(UuidUtils::toBytes);
  }

}
