package com.javacreed.api.domain.primitives.array;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import com.google.common.base.Preconditions;

public class ReadOnlyByteArray {

  public static ReadOnlyByteArray of(final byte[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new ReadOnlyByteArray(Arrays.copyOf(data, data.length));
  }

  public static ReadOnlyByteArray read(final FileChannel channel) throws IOException {
    final ByteArrayOutputStream output = new ByteArrayOutputStream();
    final ByteBuffer buffer = ByteBuffer.allocate(4096);
    for (int read; (read = channel.read(buffer)) != -1;) {
      buffer.flip();
      output.write(buffer.array(), 0, read);
    }

    return new ReadOnlyByteArray(output.toByteArray());
  }

  private final byte[] data;

  /* Compute the hash code when requested */
  private transient int lazyHashCode;

  private transient boolean lazyHashCodeComputed = false;

  private ReadOnlyByteArray(final byte[] data) {
    this.data = data;
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    final ReadOnlyByteArray other = (ReadOnlyByteArray) object;
    return Arrays.equals(data, other.data);
  }

  @Override
  public int hashCode() {
    if (false == lazyHashCodeComputed) {
      lazyHashCode = Arrays.hashCode(data);
      lazyHashCodeComputed = true;
    }

    return lazyHashCode;
  }

  public int length() {
    return data.length;
  }

  public boolean sameAs(final byte[] other) throws NullPointerException {
    Preconditions.checkNotNull(other);
    return Arrays.equals(data, other);
  }

  public void writeTo(final File file) throws NullPointerException, IOException {
    Preconditions.checkNotNull(file);
    writeTo(file.toPath());
  }

  public void writeTo(final Path path) throws NullPointerException, IOException {
    Preconditions.checkNotNull(path);

    final Path parent = path.getParent();
    if (false == Files.isDirectory(parent)) {
      Files.createDirectories(parent);
      if (false == Files.isDirectory(parent)) {
        throw new IOException("Failed to create the parent directory");
      }
    }

    Files.write(path, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
  }
}
