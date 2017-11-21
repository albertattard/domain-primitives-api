package com.javacreed.api.domain.objects.mandatory;

import java.util.UUID;

import javax.annotation.concurrent.Immutable;

import com.javacreed.api.domain.objects.utils.UuidUtils;

@Immutable
public class UuidBasedDomainObject extends ComparableBasedDomainObject<UUID> {

  protected UuidBasedDomainObject(final UUID value) throws NullPointerException {
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
  public byte[] getBytes() {
    return map(UuidUtils::toBytes);
  }

  public String getFormatted() {
    return map(UUID::toString);
  }
}
