package com.javacreed.api.domain.objects;

import java.util.Objects;
import java.util.Optional;

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
    this.invalidMessage = Objects.requireNonNull(invalidMessage);
  }

  public Optional<String> getInvalidMessage() {
    return invalidMessage;
  }

  public boolean isInvalid() {
    return valid == false;
  }

  public boolean isValid() {
    return valid;
  }
}
