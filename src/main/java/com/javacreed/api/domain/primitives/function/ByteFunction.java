package com.javacreed.api.domain.primitives.function;

import java.util.function.Function;

/**
 * Represents a function that accepts a byte-valued argument and produces a result. This is the {@code byte}-consuming
 * primitive specialization for {@link Function}.
 *
 * @param <R>
 *          the type of the result of the function
 *
 * @see Function
 */
@FunctionalInterface
public interface ByteFunction<R> {

  /**
   * Applies this function to the given argument.
   *
   * @param value
   *          the function argument
   * @return the function result
   */
  R apply(byte value);
}