package com.javacreed.api.domain.primitives.utils;

import java.util.Comparator;

import com.google.common.base.Preconditions;

public class ComparatorUtils {

  public static <T> int compare(final T a, final T b, final Comparator<T> comparator) throws NullPointerException {
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
