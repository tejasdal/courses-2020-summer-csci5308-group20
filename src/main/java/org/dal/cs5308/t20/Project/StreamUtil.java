package org.dal.cs5308.t20.Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil {
	public static String readFromInputStream(InputStream stream) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		StringBuilder output = new StringBuilder();
		String line = null;
		String separator = "";
		try {
			while ((line = br.readLine()) != null) {
				output.append(separator).append(line);
				separator = System.lineSeparator();
			}
		} finally {
			br.close();
		}
		return output.toString();
	}
}
