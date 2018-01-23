package com.javacreed.api.domain.objects.optional;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ParseResult<T> {

  private static <E> ParseResult<E> invalid(final RuntimeException e) {
    return ParseResult.of(ValidationState.invalid(e.getMessage()), Optional.empty());
  }

  private static <E> ParseResult<E> of(final ValidationState validationState, final Optional<E> object)
      throws NullPointerException {
    Preconditions.checkNotNull(validationState);
    Preconditions.checkNotNull(object);
    return new ParseResult<>(validationState, object);
  }

  public static <T> ParseResult<T> parse(final Supplier<T> supplier) throws NullPointerException {
    Preconditions.checkNotNull(supplier);
    try {
      return ParseResult.valid(supplier.get());
    } catch (final RuntimeException e) {
      return ParseResult.invalid(e);
    }
  }

  private static <E> ParseResult<E> valid(final E value) throws NullPointerException {
    return ParseResult.of(ValidationState.valid(), Optional.of(value));
  }

  private final ValidationState validationState;

  private final Optional<T> object;

  private ParseResult(final ValidationState validationState, final Optional<T> object) {
    this.object = object;
    this.validationState = validationState;
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    @SuppressWarnings("rawtypes")
    final ParseResult other = (ParseResult) object;
    return validationState.equals(other.validationState) && this.object.equals(other.object);
  }

  public Optional<T> getObject() {
    return object;
  }

  public ValidationState getValidationState() {
    return validationState;
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationState, object);
  }
}