package org.dal.cs5308.t20.Project.user;

import org.dal.cs5308.t20.Project.AppProperties;
import org.dal.cs5308.t20.Project.CryptoUtil;

/* default user
 *	- id = 1
 *	- firstName = Admin
 *	- lastName = Admin
 *	- bannerId = B00ADMIN
 *	- emailId = sanjay.m@dal.ca 
 *	- reset password = Laptop@123
 */

public class UserServiceMock implements IUserService {
	private static final User DEFAULT_USER = new User(1L, "B00ADMIN", "Admin", "Admin", "sanjay.m@dal.ca");
	private static final String DEFAULT_RESET_PASSWORD = "Laptop@123";

	@Override
	public User addUser(String firstName, String lastName, String emailId, String bannerId, String password)
			throws Exception {
		return DEFAULT_USER;
	}

	@Override
	public User getUserById(Long userId) throws Exception {
		if (userId == DEFAULT_USER.getId()) {
			return DEFAULT_USER;
		}
		return null;
	}

	@Override
	public boolean isUserExistByEmailId(String emailId) throws Exception {
		if (DEFAULT_USER.getEmailId().equals(emailId)) {
			return true;
		}
		return false;
	}

	@Override
	public User getUserByEmail(String emailId) throws Exception {
		if (DEFAULT_USER.getEmailId().equals(emailId)) {
			return DEFAULT_USER;
		}
		return null;
	}

	@Override
	public String changePassword(String emailId, String oldPassword, String newPassword) throws Exception {
		return CryptoUtil.encodePassword(newPassword);
	}

	@Override
	public String resetPassword(String emailId) {
		if (DEFAULT_USER.getEmailId().equals(emailId)) {
			return DEFAULT_RESET_PASSWORD;
		}
		return null;
	}

	@Override
	public User addAdminUser() throws Exception {
		return DEFAULT_USER;
	}

	@Override
	public boolean verifyUser(String emailId, String password) throws Exception {
		if (DEFAULT_USER.getEmailId().equals(emailId)) {
			final String existingPassword = CryptoUtil.encodePassword(AppProperties.properties.getProperty("admin.password"));
			if (password.equals(existingPassword)) {
				return true;
			}
		}
		return false;
	}

}
