package org.dal.cs5308.t20.Project.user;

import org.dal.cs5308.t20.Project.EncryptUtil;

/* default user
 *	- id = 1
 *	- firstName = Admin
 *	- lastName = Admin
 *	- bannerId = B00ADMIN
 *	- emailId = sanjay.m@dal.ca 
 */

public class UserServiceMock implements IUserService {
	private static final User DEFAULT_USER = new User(1L, "B00ADMIN", "Admin", "Admin", "sanjay.m@dal.ca");
	
	@Override
	public User addUser(String firstName, String lastName, String emailId, String bannerId, String password)
			throws Exception {
		return DEFAULT_USER;
	}

	@Override
	public User getUserById(Long userId) throws Exception {
		if (userId == 1L) {
			return DEFAULT_USER;
		}
		return null;
	}

	@Override
	public boolean isUserExistByEmailId(String emailId) throws Exception {
		if ("sanjay.m@dal.ca".equals(emailId)) {
			return true;
		}
		return false;
	}

	@Override
	public User getUserByEmail(String emailId) throws Exception {
		if ("sanjay.m@dal.ca".equals(emailId)) {
			return DEFAULT_USER;
		}
		return null;
	}

	@Override
	public String changePassword(String emailId, String oldPassword, String newPassword) throws Exception {
		return EncryptUtil.encrypt(newPassword);
	}

	@Override
	public User resetPassword(String emailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addAdminUser() throws Exception {
		return DEFAULT_USER;
	}

}
