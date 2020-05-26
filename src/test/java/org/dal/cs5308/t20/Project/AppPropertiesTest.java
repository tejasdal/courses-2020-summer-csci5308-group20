package org.dal.cs5308.t20.Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class AppPropertiesTest {

	@Test
	public void fetchPropertiesTest() throws IOException {
		String adminEmailId = (String) AppProperties.fetchProperties().get("admin.emailId");
		assertEquals("sanjay.m@dal.ca", adminEmailId);
	}
}
