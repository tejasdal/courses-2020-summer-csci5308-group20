package org.dal.cs5308.t20.Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;

public class StreamUtilTest {

	@Test
	public void readFromInputStreamTest() throws IOException {
		final String string = "This is a test string";

		InputStream inputStream = new ByteArrayInputStream(string.getBytes(Charset.forName("UTF-8")));
		assertEquals(string, StreamUtil.readFromInputStream(inputStream));
	}
}
