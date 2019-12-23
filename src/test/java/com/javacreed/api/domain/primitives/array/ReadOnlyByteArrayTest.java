package com.javacreed.api.domain.primitives.array;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ReadOnlyByteArrayTest {
  @Test
  public void should_be_immune_from_source_modification() {
    final byte[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyByteArray array = ReadOnlyByteArray.of(source);

    /* Verify that modifying the source will not effect the read-only version */
    source[0] = 10;
    Assert.assertFalse(array.sameAs(source));
    Assert.assertEquals(0, array.valueAt(0));
  }

  @Test
  public void should_create_array_with_given_content() {
    final byte[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyByteArray array = ReadOnlyByteArray.of(source);
    Assert.assertEquals(source.length, array.length());
    Assert.assertTrue(array.sameAs(source));
    for (int i = 0; i < source.length; i++) {
      Assert.assertEquals(source[i], array.valueAt(i));
    }
  }

  @Test
  public void should_create_new_iterator() {
    final byte[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyByteArray array = ReadOnlyByteArray.of(source);

    final Iterator<Byte> iterator = array.iterator();
    final Iterator<Byte> secondIterator = array.iterator();
    Assert.assertNotSame(iterator, secondIterator);

    for (int i = 0; i < source.length; i++) {
      Assert.assertTrue(iterator.hasNext());
      Assert.assertEquals(source[i], iterator.next().byteValue());

      Assert.assertTrue(secondIterator.hasNext());
      Assert.assertEquals(source[i], secondIterator.next().byteValue());
    }
    Assert.assertFalse(iterator.hasNext());
    Assert.assertFalse(secondIterator.hasNext());
  }

  @Test
  public void should_read_the_bytes_from_a_given_channel() throws Exception {
    final Path path = Paths.get(ReadOnlyByteArrayTest.class.getResource("/samples/files/java.jpg").toURI());

    final ReadOnlyByteArray array;
    try (FileChannel channel = FileChannel.open(path)) {
      array = ReadOnlyByteArray.read(channel);
    }

    final byte[] bytes = Files.readAllBytes(path);
    Assert.assertEquals(bytes.length, array.length());
    Assert.assertTrue(array.sameAs(bytes));
  }

  @Test
  public void should_read_the_bytes_from_a_given_file() throws Exception {
    final File file = new File("src/test/resources/samples/files/java.jpg");
    final ReadOnlyByteArray array = ReadOnlyByteArray.read(file);

    final byte[] bytes = Files.readAllBytes(file.toPath());
    Assert.assertEquals(bytes.length, array.length());
    Assert.assertTrue(array.sameAs(bytes));
  }

  @Test
  public void should_return_a_new_copy() {
    final byte[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyByteArray array = ReadOnlyByteArray.of(source);
    final byte[] copy = array.copyArray();
    Assert.assertArrayEquals(source, copy);

    /* Should return a new instance */
    final byte[] secondCopy = array.copyArray();
    Assert.assertNotSame(copy, secondCopy);
    Assert.assertArrayEquals(source, secondCopy);
  }

  @Test
  public void should_return_empty_instance() {
    final ReadOnlyByteArray array = ReadOnlyByteArray.of(new byte[0]);
    Assert.assertSame(ReadOnlyByteArray.empty(), array);
    Assert.assertEquals(0, array.length());
  }

  @Test
  public void should_return_empty_instance_when_reading_an_empty_channel() throws Exception {
    final Path path = Paths.get(ReadOnlyByteArrayTest.class.getResource("/samples/files/empty").toURI());

    final ReadOnlyByteArray array;
    try (FileChannel channel = FileChannel.open(path)) {
      array = ReadOnlyByteArray.read(channel);
    }

    Assert.assertSame(ReadOnlyByteArray.empty(), array);
    Assert.assertEquals(0, array.length());
  }

  @Test
  public void should_return_empty_instance_when_reading_an_empty_input_stream() throws Exception {
    final ReadOnlyByteArray array;
    try (InputStream inputStream = ReadOnlyByteArrayTest.class.getResourceAsStream("/samples/files/empty")) {
      array = ReadOnlyByteArray.read(inputStream);
    }

    Assert.assertSame(ReadOnlyByteArray.empty(), array);
    Assert.assertEquals(0, array.length());
  }

  @Test
  public void should_return_the_same_hashcode_for_the_same_source() {
    final byte[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyByteArray array = ReadOnlyByteArray.of(source);
    Assert.assertEquals(Arrays.hashCode(source), array.hashCode());
    Assert.assertEquals(array.hashCode(), ReadOnlyByteArray.of(source).hashCode());
  }

  @Test
  public void should_return_true_only_when_equals_another_instance_of_the_same_content() {
    final byte[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyByteArray array = ReadOnlyByteArray.of(source);

    Assert.assertFalse(array.equals(null));
    Assert.assertFalse(array.equals(new Object()));
    Assert.assertFalse(array.equals(ReadOnlyByteArray.of(new byte[0])));

    Assert.assertTrue(array.equals(array));
    Assert.assertTrue(array.equals(ReadOnlyByteArray.of(source)));
  }

  @Test
  public void should_throw_IllegalArgumentException_when_given_a_buffer_size_smaller_than_one() {
    final InputStream in = Mockito.mock(InputStream.class);
    Assert.assertThrows(IllegalArgumentException.class, () -> ReadOnlyByteArray.read(in, 0));
    Mockito.verifyNoMoreInteractions(in);
  }

  @Test
  public void should_throw_IllegalArgumentException_when_given_a_file_that_cannot_be_read() {
    final File file = Mockito.mock(File.class);
    Mockito.when(file.canRead()).thenReturn(false);

    Assert.assertThrows(IllegalArgumentException.class, () -> ReadOnlyByteArray.read(file));

    Mockito.verify(file).canRead();
    Mockito.verifyNoMoreInteractions(file);
  }

  @Test
  public void should_throw_IllegalArgumentException_when_given_a_very_large_file() {
    final File file = Mockito.mock(File.class);
    Mockito.when(file.canRead()).thenReturn(true);
    Mockito.when(file.length()).thenReturn(Integer.MAX_VALUE - 8L);

    Assert.assertThrows(IllegalArgumentException.class, () -> ReadOnlyByteArray.read(file));

    Mockito.verify(file).canRead();
    Mockito.verify(file).length();
    Mockito.verifyNoMoreInteractions(file);
  }

  @Test
  public void should_throw_IOException_when_it_fails_to_create_parent_directories() {
    final File file = Mockito.mock(File.class);
    final File parentFile = Mockito.mock(File.class);
    Mockito.when(file.getParentFile()).thenReturn(parentFile);
    Mockito.when(parentFile.isDirectory()).thenReturn(false);
    Mockito.when(parentFile.mkdirs()).thenReturn(false);

    final byte[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyByteArray array = ReadOnlyByteArray.of(source);

    Assert.assertThrows(IOException.class, () -> array.writeTo(file));

    Mockito.verify(file).getParentFile();
    Mockito.verifyNoMoreInteractions(file);

    Mockito.verify(parentFile).isDirectory();
    Mockito.verify(parentFile).mkdirs();
    Mockito.verifyNoMoreInteractions(parentFile);
  }

  @Test
  public void should_throw_IOException_when_parent_directories_are_not_created() {
    final File file = Mockito.mock(File.class);
    final File parentFile = Mockito.mock(File.class);
    Mockito.when(file.getParentFile()).thenReturn(parentFile);
    Mockito.when(parentFile.isDirectory()).thenReturn(false);
    Mockito.when(parentFile.mkdirs()).thenReturn(true);

    final byte[] source = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyByteArray array = ReadOnlyByteArray.of(source);

    Assert.assertThrows(IOException.class, () -> array.writeTo(file));

    Mockito.verify(file).getParentFile();
    Mockito.verifyNoMoreInteractions(file);

    Mockito.verify(parentFile, Mockito.times(2)).isDirectory();
    Mockito.verify(parentFile).mkdirs();
    Mockito.verifyNoMoreInteractions(parentFile);
  }

  @Test
  public void should_throw_NullPointerException_when_given_nulls() {
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.of(null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.read((File) null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.read((FileChannel) null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.read((InputStream) null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.read(null, 0));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.empty().sameAs(null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.empty().writeTo((File) null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.empty().writeTo((Path) null));
  }

  @Test
  public void should_write_the_bytes_to_file_and_create_missing_directories() throws Exception {
    final File file = new File("src/test/resources/samples/files/java.jpg");
    final ReadOnlyByteArray array = ReadOnlyByteArray.read(file);

    final File output = new File("target/" + UUID.randomUUID() + "/java.jpg");
    Assert.assertFalse(output.exists());
    Assert.assertFalse(output.getParentFile().exists());
    array.writeTo(output);

    Assert.assertArrayEquals(Files.readAllBytes(file.toPath()), Files.readAllBytes(output.toPath()));
  }

  @Test
  public void should_write_the_bytes_to_path_and_create_missing_directories() throws Exception {
    final File file = new File("src/test/resources/samples/files/java.jpg");
    final ReadOnlyByteArray array = ReadOnlyByteArray.read(file);

    final Path output = Paths.get("target/" + UUID.randomUUID() + "/java.jpg");
    array.writeTo(output);

    Assert.assertArrayEquals(Files.readAllBytes(file.toPath()), Files.readAllBytes(output));
  }
}
