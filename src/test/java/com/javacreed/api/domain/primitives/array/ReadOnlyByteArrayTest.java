package com.javacreed.api.domain.primitives.array;

import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class ReadOnlyByteArrayTest {

  @Test
  public void test() throws Exception {
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
