package com.javacreed.api.domain.primitives.array;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.Arrays;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

/**
 * Arrays in Java are mutable and anyone can change their contents. This wrapper class addresses this issue by providing
 * read-only operations to the array, maintaining the primitive types.
 *
 * @author Albert Attard
 */
@Immutable
public class ReadOnlyByteArray implements Iterable<Byte> {

  private static final ReadOnlyByteArray EMPTY = new ReadOnlyByteArray(new byte[0]);

  /**
   * Returns the empty array. This is a shared instance and the same empty array is always returned.
   *
   * @return the empty array
   */
  public static ReadOnlyByteArray empty() {
    return ReadOnlyByteArray.EMPTY;
  }

  /**
   * Copies the given array and returns a new instance of this class. Any changes to the given array will not affect
   * this class.
   *
   * @param data
   *          the array (which cannot be {@code null})
   * @return an instance of this class for the given array
   * @throws NullPointerException
   *           if the given array is {@code null}
   */
  public static ReadOnlyByteArray of(final byte[] data) {
    Preconditions.checkNotNull(data);

    if (data.length == 0) {
      return ReadOnlyByteArray.empty();
    }

    return new ReadOnlyByteArray(Arrays.copyOf(data, data.length));
  }

  /**
   * Reads the given file and returns an instance of this class with the bytes read from the given file. The contents
   * read from the file need to fit the array as otherwise an Exception is thrown. Note that the file is not closed and
   * the caller is responsible from closing it.
   *
   * @param file
   *          the file to be read (which cannot be {@code null} and must fit within an array)
   * @return an instance of this class
   * @throws IOException
   * @throws NullPointerException
   *           if the given {@code file} is {@code null}
   * @throws IllegalArgumentException
   *           if the given {@code file} cannot be read or is too large
   */
  public static ReadOnlyByteArray read(final File file) throws IOException {
    Preconditions.checkNotNull(file);
    Preconditions.checkArgument(file.canRead());

    /* http://hg.openjdk.java.net/jdk7/jdk7/jdk/rev/ec45423a4700#l3.1 */
    final long size = file.length();
    Preconditions.checkArgument(size < Integer.MAX_VALUE - 8);

    try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
      return ReadOnlyByteArray.read(in, (int) size);
    }
  }

  /**
   * Reads the given channel and returns an instance of this class with the bytes read from the given channel. The
   * contents read from the channel need to fit the array as otherwise an Exception is thrown. Note that the channel is
   * not closed and the caller is responsible from closing it.
   *
   * @param channel
   *          the channel to be read (which cannot be {@code null})
   * @return an instance of this class
   * @throws NullPointerException
   *           if the given {@code channel} is {@code null}
   * @throws IOException
   *           if it fails to read the bytes from the given channel
   */
  public static ReadOnlyByteArray read(final FileChannel channel) throws IOException {
    Preconditions.checkNotNull(channel);

    final ByteArrayOutputStream output = new ByteArrayOutputStream();
    final ByteBuffer buffer = ByteBuffer.allocate(4096);
    for (int read; (read = channel.read(buffer)) != -1;) {
      buffer.flip();
      output.write(buffer.array(), 0, read);
    }

    if (output.size() == 0) {
      return ReadOnlyByteArray.empty();
    }

    return new ReadOnlyByteArray(output.toByteArray());
  }

  /**
   * Reads the given input stream and returns an instance of this class with the bytes read from the given input stream.
   * The contents read from the input stream need to fit the array as otherwise an Exception is thrown. Note that the
   * input stream is not closed and the caller is responsible from closing it.
   *
   * @param inputStream
   *          the input stream to be read (which cannot be {@code null})
   * @return an instance of this class
   * @throws NullPointerException
   *           if the given {@code inputStream} is {@code null}
   * @throws IOException
   *           if it fails to read the bytes from the given input stream
   */
  public static ReadOnlyByteArray read(final InputStream inputStream) throws IOException {
    Preconditions.checkNotNull(inputStream);
    return ReadOnlyByteArray.read(inputStream, 4096);
  }

  /**
   * Reads the given input stream and returns an instance of this class with the bytes read from the given input stream.
   * The contents read from the input stream need to fit the array as otherwise an Exception is thrown. Note that the
   * input stream is not closed and the caller is responsible from closing it.
   * <p>
   * This method also takes the buffer initial size. This is good to have as otherwise the buffer needs to be expanded
   * everytime it fills up.
   *
   * @param inputStream
   *          the input stream to be read (which cannot be {@code null})
   * @param size
   *          the initial buffer size (which cannot be negative)
   * @return an instance of this class
   * @throws NullPointerException
   *           if the given {@code inputStream} is {@code null}
   * @throws IOException
   *           if it fails to read the bytes from the given input stream
   */
  public static ReadOnlyByteArray read(final InputStream inputStream, final int size) throws IOException {
    Preconditions.checkNotNull(inputStream);
    Preconditions.checkArgument(size > 0);

    final ByteArrayOutputStream output = new ByteArrayOutputStream(size);
    final byte[] buffer = new byte[4096];
    for (int read; (read = inputStream.read(buffer)) != -1;) {
      output.write(buffer, 0, read);
    }

    if (output.size() == 0) {
      return ReadOnlyByteArray.empty();
    }

    return new ReadOnlyByteArray(output.toByteArray());
  }

  private final byte[] data;
  /* Compute the hash code when requested */
  private transient int lazyHashCode;

  private transient boolean lazyHashCodeComputed;

  private ReadOnlyByteArray(final byte[] data) {
    this.data = data;
  }

  /**
   * Returns a copy array every time this method is called. This is a defensive copy in order to protect the array
   * enclosed by this class. Any changes made to the returned array do not have any effects on the array enclosed by
   * this class.
   *
   * @return a copy of the array enclosed by this class
   */
  public byte[] copyArray() {
    return Arrays.copyOf(data, data.length);
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

  @Override
  public ByteArrayIterator iterator() {
    return ByteArrayIterator.create(data);
  }

  /**
   * Returns the length of this array
   *
   * @return the length of this array
   */
  public int length() {
    return data.length;
  }

  /**
   * Returns {@code true} if the contents of the given arrays is the same as the content of this array, {@code false}
   * otherwise.
   *
   * @param other
   *          the array to compare with (which cannot be {@code null})
   * @return {@code true} if the contents of the given arrays is the same as the content of this array, {@code false}
   *         otherwise
   * @throws NullPointerException
   *           if the given array is {@code null}
   */
  public boolean sameAs(final byte[] other) {
    Preconditions.checkNotNull(other);
    return Arrays.equals(data, other);
  }

  /**
   * Returns the value at the given {@code index}
   *
   * @param index
   *          the index (which must be between 0 and the {@code array.length() - 1}, both inclusive)
   * @return the value at the given {@code index}
   * @throws ArrayIndexOutOfBoundsException
   *           if the given index is less than 0 or greater than or equal to {@code array.length()};
   */
  public byte valueAt(final int index) {
    return data[index];
  }

  /**
   * Writes the content of the array to the given file. The file is created if it does not exist, together with any
   * missing parent directories. An IOException is thrown in the event that the parent directories cannot be created or
   * an error occurs while writing the files. Note that this operation is not atomic and any created directories are not
   * deleted should this operation fail.
   *
   * @param file
   *          the file where the byte array is written (which cannot be {@code null})
   * @throws NullPointerException
   *           if the given {@code file} is {@code null}
   * @throws IOException
   *           if an error occurs while writing to the given {@code file}
   */
  public void writeTo(final File file) throws IOException {
    Preconditions.checkNotNull(file);

    final File parent = file.getParentFile();
    if (parent != null && false == parent.isDirectory()) {
      parent.mkdirs();
      if (false == parent.isDirectory()) {
        throw new IOException("Failed to create the parent directory " + parent);
      }
    }

    try (final BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file))) {
      output.write(data);
    }
  }

  /**
   * Writes the content of the array to the given file. The file is created if it does not exist, together with any
   * missing parent directories. An IOException is thrown in the event that the parent directories cannot be created or
   * an error occurs while writing the files. Note that this operation is not atomic and any created directories are not
   * deleted should this operation fail.
   *
   * @param path
   *          the file where the byte array is written (which cannot be {@code null})
   * @throws NullPointerException
   *           if the given {@code file} is {@code null}
   * @throws IOException
   *           if an error occurs while writing to the given {@code file}
   * @see #writeTo(File)
   */
  public void writeTo(final Path path) throws IOException {
    Preconditions.checkNotNull(path);

    /*
     * This was converted to the older File version based on a recommendation made by Sonar. It seems that in Java 8,
     * the Files.exists() method has noticeably poor performance and can slow an application significantly when used to
     * check files that don't actually exist. The same goes for Files.notExists(), Files.isDirectory() and
     * Files.isRegularFile().
     */
    // final Path parent = path.getParent();
    // if (parent != null && false == Files.isDirectory(parent)) {
    // Files.createDirectories(parent);
    // if (false == Files.isDirectory(parent)) {
    // throw new IOException("Failed to create the parent directory " + parent);
    // }
    // }
    //
    // Files.write(path, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    writeTo(path.toFile());
  }
}
