package com.javacreed.api.domain.primitives.array;

import java.io.File;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class ReadOnlyByteArrayTest {

  @Test
  public void contentIntegrity() {
    final byte[] actual = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    final ReadOnlyByteArray readOnly = ReadOnlyByteArray.of(actual);
    Assert.assertEquals(actual.length, readOnly.length());
    Assert.assertTrue(readOnly.sameAs(actual));
    for (int i = 0; i < actual.length; i++) {
      Assert.assertEquals(actual[i], readOnly.valueAt(i));
    }

    /* Verify that modifying the starting array will not effect the read-only version */
    actual[0] = 10;
    Assert.assertFalse(readOnly.sameAs(actual));
    Assert.assertEquals(0, readOnly.valueAt(0));
  }

  @Test
  public void handlingOfNulls() {
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.of(null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.read((File) null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.read((FileChannel) null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.read((InputStream) null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.read((InputStream) null, 0));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.empty().sameAs(null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.empty().writeTo((File) null));
    Assert.assertThrows(NullPointerException.class, () -> ReadOnlyByteArray.empty().writeTo((Path) null));
  }

  @Test
  public void readBytesFromPathUsingFileChannel() throws Exception {
    /* The sample file that is checked */
    final Path path = Paths.get(ReadOnlyByteArrayTest.class.getResource("/samples/files/java.jpg").toURI());

    /* Read the byte array using channels */
    ReadOnlyByteArray array;
    try (FileChannel channel = FileChannel.open(path)) {
      array = ReadOnlyByteArray.read(channel);
    }

    /* Read the file using a standard method */
    final byte[] bytes = Files.readAllBytes(path);

    /* Verify content */
    Assert.assertEquals(bytes.length, array.length());
    Assert.assertTrue(array.sameAs(bytes));
  }
}
