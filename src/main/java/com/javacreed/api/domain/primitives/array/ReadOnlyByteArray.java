package com.javacreed.api.domain.primitives.array;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import com.google.common.base.Preconditions;

public class ReadOnlyByteArray {

  public static ReadOnlyByteArray of(final byte[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new ReadOnlyByteArray(Arrays.copyOf(data, data.length));
  }

  private final byte[] data;

  private ReadOnlyByteArray(final byte[] data) {
    this.data = data;
  }

  public int length() {
    return data.length;
  }

  public ByteArrayInputStream toInputStream() {
    return new ByteArrayInputStream(data);
  }

}
