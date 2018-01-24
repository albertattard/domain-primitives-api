package com.javacreed.api.domain.primitives.optional;

import java.util.Objects;
import java.util.Optional;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ValidationState {

  private static final ValidationState VALID = new ValidationState(true, Optional.empty());

  /** TODO: add support for parameters */
  public static ValidationState invalid(final String invalidMessage) {
    return new ValidationState(false, Optional.ofNullable(invalidMessage));
  }

  public static ValidationState valid() {
    return ValidationState.VALID;
  }

  private final boolean valid;
  private final Optional<String> invalidMessage;

  private ValidationState(final boolean valid, final Optional<String> invalidMessage) throws NullPointerException {
    this.valid = valid;
    this.invalidMessage = Preconditions.checkNotNull(invalidMessage);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    final ValidationState other = (ValidationState) object;
    return valid == other.valid && invalidMessage.equals(other.invalidMessage);
  }

  public Optional<String> getInvalidMessage() {
    return invalidMessage;
  }

  public String getNullableInvalidMessage() {
    return invalidMessage.orElse(null);
  }

  @Override
  public int hashCode() {
    return Objects.hash(valid, invalidMessage);
  }

  public boolean isInvalid() {
    return valid == false;
  }

  public boolean isValid() {
    return valid;
  }

  @Override
  public String toString() {
    return valid ? "Valid" : invalidMessage.orElse("Invalid");
  }
}
