package org.dal.cs5308.t20.Project;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class AppProperties {
	public static final Properties properties = new Properties();

	static Properties fetchProperties() throws IOException {
		try {
			ClassPathResource file = new ClassPathResource("application.properties");
			InputStream in = file.getInputStream();
			properties.load(in);
			return properties;
		} finally {
			System.out.println("Properties loaded.");
		}
	}
}

