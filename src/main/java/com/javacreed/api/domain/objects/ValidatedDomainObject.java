package com.javacreed.api.domain.objects;

import java.util.Objects;
import java.util.Optional;

public class ValidatedDomainObject extends StringBasedDomainObject {

  private final ValidationState validationState;

  protected ValidatedDomainObject(final String value, final ValidationState validationState)
      throws NullPointerException {
    super(value);
    this.validationState = Objects.requireNonNull(validationState);
  }

  public Optional<String> getInvalidMessage() {
    return validationState.getInvalidMessage();
  }

  public boolean isInvalid() {
    return validationState.isInvalid();
  }

  public boolean isValid() {
    return validationState.isValid();
  }
}
