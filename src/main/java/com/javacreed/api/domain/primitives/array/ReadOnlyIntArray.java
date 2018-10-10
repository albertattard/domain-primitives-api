package com.javacreed.api.domain.primitives.array;

import java.util.Arrays;

import com.google.common.base.Preconditions;

public class ReadOnlyIntArray {

  public static ReadOnlyIntArray of(final int[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new ReadOnlyIntArray(Arrays.copyOf(data, data.length));
  }

  private final int[] data;

  private ReadOnlyIntArray(final int[] data) {
    this.data = data;
  }

  public int length() {
    return data.length;
  }

}
