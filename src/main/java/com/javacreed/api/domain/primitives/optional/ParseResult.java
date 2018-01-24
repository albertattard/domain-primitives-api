package com.javacreed.api.domain.primitives.optional;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ParseResult<T> {

  /**
   * Returns the given <code>value</code>, if it is either <code>null</code> or at most as long as the given
   * <code>maxLength</code>. If the given <code>value</code> is not <code>null</code> and is longer than the given
   * <code>maxLength<code>, then an {@link IllegalArgumentException} is thrown.  An {@link IllegalArgumentException} is also thrown if the given <code>maxLength</code>
   * is less than 0.
   * <p/>
   * The value is usually written into the database and the column hosting this value does not accept anything longer
   * than a predefined length. While the program accepts invalid inputs, there is a limit to how invalid the inputs can
   * be. This constraint is imposed by the persistence layer and indicates that the input exceeds the tolerance level.
   * Please note values accepted by this method are by no means valid. These are simple safe to be written into the
   * database column.
   * <p/>
   * TODO: We may need to move this method elsewhere
   *
   * @param value
   *          the value to be verified (which can be <code>null</code>)
   * @param maxLength
   *          the maximum length that the given value can have (which needs to be a positive number)
   * @return the given value
   * @throws IllegalArgumentException
   *           given <code>value</code> is not null and is longer than the given <code>maxLength</code> or the given
   *           <code>maxLength</code> is negative
   * @see #checkLength(String, int, String)
   */

  public static String checkLength(final String value, final int maxLength) throws IllegalArgumentException {
    return ParseResult.checkLength(value, maxLength, "The value should not be longer than " + maxLength + " letters");
  }

  /**
   * Returns the given <code>value</code>, if it is either <code>null</code> or at most as long as the given
   * <code>maxLength</code>. If the given <code>value</code> is not <code>null</code> and is longer than the given
   * <code>maxLength<code>, then an {@link IllegalArgumentException} is thrown.  An {@link IllegalArgumentException} is also thrown if the given <code>maxLength</code>
   * is less than 0.
   * <p/>
   * The value is usually written into the database and the column hosting this value does not accept anything longer
   * than a predefined length. While the program accepts invalid inputs, there is a limit to how invalid the inputs can
   * be. This constraint is imposed by the persistence layer and indicates that the input exceeds the tolerance level.
   * Please note values accepted by this method are by no means valid. These are simple safe to be written into the
   * database column.
   * <p/>
   * TODO: We may need to move this method elsewhere
   *
   * @param value
   *          the value to be verified (which can be <code>null</code>)
   * @param maxLength
   *          the maximum length that the given value can have (which needs to be a positive number)
   * @param message
   *          the message to be used in the event the given <code>value</code> is not null and is longer than the given
   *          <code>maxLength</code>
   * @return the given value
   * @throws IllegalArgumentException
   *           given <code>value</code> is not null and is longer than the given <code>maxLength</code> or the given
   *           <code>maxLength</code> is negative
   */
  public static String checkLength(final String value, final int maxLength, final String message)
      throws IllegalArgumentException {
    Preconditions.checkArgument(maxLength >= 0, "The maximum length validation parameter cannot be negative");
    Preconditions.checkArgument(value == null || value.length() <= maxLength, message);
    return value;
  }

  public static <E> ParseResult<E> invalid(final RuntimeException e) {
    return ParseResult.invalid(e.getMessage());
  }

  public static <E> ParseResult<E> invalid(final String message) {
    return ParseResult.of(ValidationState.invalid(message), Optional.empty());
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

  public static <E> ParseResult<E> valid(final E object) throws NullPointerException {
    return ParseResult.valid(Optional.ofNullable(object));
  }

  public static <E> ParseResult<E> valid(final Optional<E> object) throws NullPointerException {
    return ParseResult.of(ValidationState.valid(), object);
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