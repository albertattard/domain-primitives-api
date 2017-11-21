package com.javacreed.api.domain.objects.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ParsedDomainObject<T> extends ValidatedDomainObject {

  protected final Optional<T> object;

  protected ParsedDomainObject(final Optional<String> value, final ValidationState validationState,
      final Optional<T> object) throws NullPointerException {
    super(value, validationState);
    this.object = Preconditions.checkNotNull(object);
  }

  public T getNullableObject() {
    return object.orElse(null);
  }

  public Optional<T> getObject() {
    return object;
  }

  public boolean hasObject() {
    return object.isPresent();
  }
}
