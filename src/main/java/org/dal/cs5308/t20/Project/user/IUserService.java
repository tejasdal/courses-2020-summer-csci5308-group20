package org.dal.cs5308.t20.Project.user;

import java.util.Set;

public interface IUserService {

	public User addUser(String firstName, String lastName, String emailId, String bannerId, String password)
			throws Exception;

	public User getUserById(Long userId) throws Exception;

	public boolean isUserExistByEmailId(String emailId) throws Exception;
	
	public boolean isUserExistByBannerId(String bannerId) throws Exception;

	public User getUserByEmail(String emailId) throws Exception;

	public String changePassword(String emailId, String oldPassword, String newPassword) throws Exception;

	public String resetPassword(String emailId) throws Exception;
	
	public User addAdminUser() throws Exception;
	
	public boolean verifyUser(String emailId, String password) throws Exception;

	public String generateRandomPassword();
	
	public Set<User> searchUsers(String emailIdPattern, String bannerIdPattern) throws Exception;
	
	public User getUserByBannerId(String bannerId) throws Exception;
}
