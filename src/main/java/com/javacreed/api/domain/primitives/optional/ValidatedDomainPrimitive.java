package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ValidatedDomainPrimitive extends StringBasedDomainPrimitive {

  private final ValidationState validationState;

  protected ValidatedDomainPrimitive(final Optional<String> value, final ValidationState validationState)
      throws NullPointerException {
    super(value);
    this.validationState = Preconditions.checkNotNull(validationState);
  }

  public Optional<String> getInvalidMessage() {
    return validationState.getInvalidMessage();
  }

  public String getNullableInvalidMessage() {
    return validationState.getNullableInvalidMessage();
  }

  public boolean isInvalid() {
    return validationState.isInvalid();
  }

  public boolean isValid() {
    return validationState.isValid();
  }
}
