package com.javacreed.api.domain.objects;

import java.util.Objects;
import java.util.Optional;

public class ParsedDomainObject<T> extends ValidatedDomainObject {

  protected final Optional<T> object;

  protected ParsedDomainObject(final Optional<String> value, final ValidationState validationState,
      final Optional<T> object) throws NullPointerException {
    super(value, validationState);
    this.object = Objects.requireNonNull(object);
  }

  public Optional<T> getObject() {
    return object;
  }

  public boolean hasObject() {
    return object.isPresent();
  }

  public T toNullableObject() {
    return object.orElse(null);
  }
}