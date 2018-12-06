package com.javacreed.api.domain.primitives.utils;

import java.util.Comparator;

import com.google.common.base.Preconditions;
import com.google.common.collect.Comparators;

/**
 * A stateless class that provides {@link Comparator} related functions
 *
 * @author Albert Attard
 */
public class ComparatorUtils {

  /**
   * Compare two objects of the same type using the given comparator. These objects are only compared if these are not
   * null, making this method null-safe. With that said, the comparator cannot be null.
   * <p>
   * Nulls are considered smaller than non-nulls. If the first parameter is {@code null} while the second parameter is
   * not {@code null}, this method returns -1. On the other hand, if the second parameter is {@code null} while the
   * first parameter is not {@code null}, then this method returns 1. If the parameters are not the same and non of them
   * is {@code null}, then the method returns the {@link Comparators} result.
   *
   * @param a
   *          the object to be compared (which can be {@code null})
   * @param b
   *          the object to be compared (which can be {@code null})
   * @param comparator
   *          the comparator to be used (which cannot be {@code null})
   * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater
   *         than the second.
   * @throws NullPointerException
   *           if the given {@code comparator} is {@code null}
   */
  public static <T> int compare(final T a, final T b, final Comparator<T> comparator) {
    Preconditions.checkNotNull(comparator);

    if (a == b) {
      return 0;
    }

    if (a == null) {
      return -1;
    }

    if (b == null) {
      return 1;
    }

    return comparator.compare(a, b);
  }

  private ComparatorUtils() {}
}
