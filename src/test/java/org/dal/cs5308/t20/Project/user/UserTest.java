package org.dal.cs5308.t20.Project.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {
	
	@Test
	public void constructorTest() {
		final String bannerId = "B00ADMIN";
		final Long id = 1L;
		final String fName = "Admin";
		final String lName = "Admin";
		final String emailId = "sanjay.m@dal.ca";
		
		User user = new User(id, bannerId, fName, lName, emailId);
		assertEquals(id, user.getId());
		assertEquals(bannerId, user.getBannerId());
		assertEquals(fName, user.getFirstName());
		assertEquals(lName, user.getLastName());
		assertEquals(emailId, user.getEmailId());
	}
}
