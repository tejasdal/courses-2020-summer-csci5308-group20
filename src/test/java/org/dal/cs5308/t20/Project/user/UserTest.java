package org.dal.cs5308.t20.Project.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.dal.cs5308.t20.Project.CryptoUtil;
import org.junit.jupiter.api.Test;

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
	public void addUserTest() throws Exception {
		IUserService userService = new UserServiceMock();
		User newUser = DEFAULT_USER.addUser(userService, DEFAULT_RESET_PASSWORD);
		assertEquals(DEFAULT_USER.getBannerId(), newUser.getBannerId());
		assertEquals(DEFAULT_USER.getEmailId(), newUser.getEmailId());
		assertEquals(DEFAULT_USER.getFirstName(), newUser.getFirstName());
		assertEquals(DEFAULT_USER.getLastName(), newUser.getLastName());
		assertEquals(DEFAULT_USER.getId(), newUser.getId());
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
}
