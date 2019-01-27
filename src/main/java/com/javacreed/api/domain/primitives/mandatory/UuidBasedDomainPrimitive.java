package com.javacreed.api.domain.primitives.mandatory;

import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import javax.annotation.concurrent.Immutable;

import com.javacreed.api.domain.primitives.utils.UuidUtils;

@Immutable
public class UuidBasedDomainPrimitive extends ComparableBasedDomainPrimitive<UUID> {

  protected UuidBasedDomainPrimitive(final UUID value) throws NullPointerException {
    super(value);
  }

  public String asBase64String() {
    return Base64.getEncoder().encodeToString(asBytes());
  }

  /**
   * Converts the UUID to bytes. Note that this is incompatible with the {@link UUID#nameUUIDFromBytes()}. The returned
   * bytes array can be converted back to UUID using the {@link UuidUtils#asUuid(byte[])} method defined within this
   * class.
   *
   * @return the converted (incompatible with the {@link UUID#nameUUIDFromBytes()}) bytes
   * @see UuidUtils#asBytes(UUID)
   */
  public byte[] asBytes() {
    return map(UuidUtils::asBytes);
  }

  public String asString() {
    return map(UUID::toString);
  }

  public boolean sameValue(final byte[] other) {
    return other != null && other.length == 16 && Arrays.equals(asBytes(), other);
  }

  public boolean sameValue(final String other) {
    return UuidUtils.isValid(other) && toString().equalsIgnoreCase(other);
  }

}
