package org.dal.cs5308.t20.Project.user;

import org.dal.cs5308.t20.Project.CryptoUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
	private static final String bannerId = "B00ADMIN";
	private static final Long id = 1L;
	private static final String fName = "Admin";
	private static final String lName = "Admin";
	private static final String emailId = "sanjay.m@dal.ca";
	private static final String DEFAULT_RESET_PASSWORD = "Laptop@123";
	private static final User DEFAULT_USER = new User(id, bannerId, fName, lName, emailId);

	@Test
	public void constructorTest() {

		User user = new User(id, bannerId, fName, lName, emailId);
		assertEquals(id, user.getId());
		assertEquals(bannerId, user.getBannerId());
		assertEquals(fName, user.getFirstName());
		assertEquals(lName, user.getLastName());
		assertEquals(emailId, user.getEmailId());
	}

	@Test
	public void constructorIdTest() throws Exception {
		IUserService userService = new UserServiceMock();
		User user = new User(id, userService);
		assertEquals(bannerId, user.getBannerId());
		assertEquals(fName, user.getFirstName());
		assertEquals(lName, user.getLastName());
		assertEquals(emailId, user.getEmailId());
	}

	@Test
	public void constructorEmailTest() throws Exception {
		IUserService userService = new UserServiceMock();
		User user = new User(emailId, userService);
		assertEquals(id, user.getId());
		assertEquals(bannerId, user.getBannerId());
		assertEquals(fName, user.getFirstName());
		assertEquals(lName, user.getLastName());
	}

	@Test
	public void changePasswordTest() throws Exception {
		final String newPassword = "Hello@123";
		IUserService userService = new UserServiceMock();
		String changePassword = DEFAULT_USER.changePassword(userService, DEFAULT_RESET_PASSWORD, newPassword);
		assertEquals(CryptoUtil.encodePassword(newPassword), changePassword);
	}

	@Test
	public void resetPasswordTest() throws Exception {
		IUserService userService = new UserServiceMock();
		String resetPassword = DEFAULT_USER.resetPassword(userService);
		assertEquals(resetPassword, DEFAULT_RESET_PASSWORD);
	}
	
	@Test
	public void equalsTest() {
		User user1 = new User(1L, "banner1", "user1f", "user1l", "email1");
		User user2 = new User(2L, "banner2", "user2f", "user2l", "email1");
		assertEquals(user1, user2);
		user1 = new User(1L, "banner1", "user1f", "user1l", "email1");
		user2 = new User(2L, "banner1", "user2f", "user2l", "email2");
		assertEquals(user1, user2);
	}
}
