package com.javacreed.api.domain.primitives.optional;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@Immutable
@SuppressFBWarnings(value = "EQ_DOESNT_OVERRIDE_EQUALS", justification = "The parsed value is not important to the equals method")
public class ParsedDomainPrimitive<T> extends ValidatedDomainPrimitive {

  protected final Optional<T> object;

  protected ParsedDomainPrimitive(final Optional<String> value, final ParseResult<T> parseResult)
      throws NullPointerException {
    super(value, parseResult.getValidationState());
    this.object = Preconditions.checkNotNull(parseResult.getObject());
  }

  /**
   * @Deprecated The {@link ParseResult} simplifies the parsing of objects and should be preferred over this method.
   *             This method has nothing wrong and archives the same result. It is included here simply to remind me
   *             that there may be a simpler option
   * @see ParseResult
   */
  @Deprecated
  protected ParsedDomainPrimitive(final Optional<String> value, final ValidationState validationState,
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
