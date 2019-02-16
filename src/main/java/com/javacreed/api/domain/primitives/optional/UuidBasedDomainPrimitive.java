package com.javacreed.api.domain.primitives.optional;

import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.concurrent.Immutable;

import com.javacreed.api.domain.primitives.utils.UuidUtils;

@Immutable
public class UuidBasedDomainPrimitive extends ComparableBasedDomainPrimitive<UUID> {

  protected UuidBasedDomainPrimitive(final Optional<UUID> value) throws NullPointerException {
    super(value);
  }

  public Optional<String> asBase64String() {
    return asBytes().map(b -> Base64.getEncoder().encodeToString(b));
  }

  public String asBase64StringOrNull() {
    return asBase64String().orElse(null);
  }

  /**
   * Converts the UUID to bytes. Note that this is incompatible with the {@link UUID#nameUUIDFromBytes()}. The returned
   * bytes array can be converted back to UUID using the {@link UuidUtils#asUuid(byte[])} method defined within this
   * class.
   *
   * @return the converted (incompatible with the {@link UUID#nameUUIDFromBytes()}) bytes
   * @see UuidUtils#asBytes(UUID)
   */
  public Optional<byte[]> asBytes() {
    return map(UuidUtils::asBytes);
  }

  /**
   * This method is a shortcut to <code>getBytes().orElse(null)</code>
   * <p>
   * Converts the UUID to bytes. Note that this is incompatible with the {@link UUID#nameUUIDFromBytes()}. The returned
   * bytes array can be converted back to UUID using the {@link UuidUtils#asUuid(byte[])} method defined within this
   * class.
   *
   * @return the converted (incompatible with the {@link UUID#nameUUIDFromBytes()}) bytes if available, otherwise
   *         {@code null}
   * @see #asBytes()
   */
  public byte[] asBytesOrNull() {
    return asBytes().orElse(null);
  }

  public Optional<String> asString() {
    return map(UUID::toString);
  }

  public String asStringOrBlank() {
    return asString().orElse("");
  }

  public String asStringOrNull() {
    return asString().orElse(null);
  }

  /**
   * Compares the given byte array to the byte equivalent value of this UUID ({@link #asBytes()}) and returns
   * {@code true}, if the value is set and the given byte array has the same content as this UUID byte value,
   * {@code false} otherwise.
   *
   * @param other
   *          the array to compare to (which cannot be {@code null})
   * @return {@code true} if the given array has the same byte value of this UUID, {@code false} otherwise
   */
  public boolean sameValue(final byte[] other) {
    return other != null && other.length == 16 && asBytes().map(b -> Arrays.equals(b, other)).orElse(false);
  }

  public boolean sameValue(final String other) {
    return UuidUtils.isValid(other) && asString().map(s -> s.equalsIgnoreCase(other)).orElse(false);
  }
}
