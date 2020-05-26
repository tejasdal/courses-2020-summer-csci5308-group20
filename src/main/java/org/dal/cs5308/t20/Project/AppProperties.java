package org.dal.cs5308.t20.Project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.util.ResourceUtils;

public class AppProperties {
	public static final Properties properties = new Properties();

	static Properties fetchProperties() throws IOException {
		try {
			File file = ResourceUtils.getFile("classpath:application.properties");
			InputStream in = new FileInputStream(file);
			properties.load(in);
			return properties;
		} finally {
			System.out.println("Properties loaded.");
		}
	}
}
