package org.dal.cs5308.t20.Project.user;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.dal.cs5308.t20.Project.AppProperties;
import org.dal.cs5308.t20.Project.EmailUtil;
import org.dal.cs5308.t20.Project.CryptoUtil;
import org.dal.cs5308.t20.Project.Factory;

public class UserService implements IUserService {

	private static final String INSERT_USER_QUERY = "insert "
			+ "into User(ID, BANNER_ID, FIRST_NAME, LAST_NAME, EMAIL_ID, PASSWORD) " + "values(?, ?, ?, ?, ?, ?)";
	private static final String GET_USER_BY_ID = "select *from User where ID = ?";
	private static final String GET_USER_BY_EMAIL_ID = "select *from User where EMAIL_ID = ?";
	private static final String VERIFY_USER_CREDENTIALS = "select *from User where EMAIL_ID = ? and PASSWORD = ?";
	private static final String UPDATE_PASSWORD_FOR_EMAIL_ID = "update User set PASSWORD = ? where EMAIL_ID = ?";

	@Override
	public User addUser(String firstName, String lastName, String emailId, String bannerId, String password)
			throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		final Long id = System.currentTimeMillis();
		final String encodedPassword = CryptoUtil.encodePassword(password);
		final PreparedStatement pstatement = Factory.getDbUtilInstance().getConnection()
				.prepareStatement(INSERT_USER_QUERY);
		pstatement.setLong(1, id);
		pstatement.setString(2, bannerId);
		pstatement.setString(3, firstName);
		pstatement.setString(4, lastName);
		pstatement.setString(5, emailId);
		pstatement.setString(6, encodedPassword);
		pstatement.execute();
		return new User(id, bannerId, firstName, lastName, emailId);
	}

	@Override
	public User getUserById(Long userId) throws SQLException, UserNotFoundException {
		final PreparedStatement pstatement = Factory.getDbUtilInstance().getConnection()
				.prepareStatement(GET_USER_BY_ID);
		pstatement.setLong(1, userId);
		ResultSet rs = null;
		User user = null;
		try {
			rs = pstatement.executeQuery();
			while (rs.next()) {
				String firstName = rs.getString(org.dal.cs5308.t20.Project.dd.User.FIRST_NAME);
				String lastName = rs.getString(org.dal.cs5308.t20.Project.dd.User.LAST_NAME);
				String emailId = rs.getString(org.dal.cs5308.t20.Project.dd.User.EMAIL_ID);
				String bannerId = rs.getString(org.dal.cs5308.t20.Project.dd.User.BANNER_ID);
				user = new User(userId, bannerId, firstName, lastName, emailId);
				return user;
			}
		} finally {
			rs.close();
		}
		throw new UserNotFoundException("User with ID '" + userId + "' not found");
	}

	@Override
	public User getUserByEmail(String emailId) throws SQLException, UserNotFoundException {
		final PreparedStatement pstatement = Factory.getDbUtilInstance().getConnection()
				.prepareStatement(GET_USER_BY_EMAIL_ID);
		pstatement.setString(1, emailId);
		ResultSet rs = null;
		User user = null;
		try {
			rs = pstatement.executeQuery();
			while (rs.next()) {
				String firstName = rs.getString(org.dal.cs5308.t20.Project.dd.User.FIRST_NAME);
				String lastName = rs.getString(org.dal.cs5308.t20.Project.dd.User.LAST_NAME);
				Long userId = rs.getLong(org.dal.cs5308.t20.Project.dd.User.ID);
				String bannerId = rs.getString(org.dal.cs5308.t20.Project.dd.User.BANNER_ID);
				user = new User(userId, bannerId, firstName, lastName, emailId);
				return user;
			}
		} finally {
			rs.close();
		}
		throw new UserNotFoundException("User with Email ID '" + emailId + "' not found");
	}

	@Override
	public boolean isUserExistByEmailId(String emailId) throws SQLException {
		final PreparedStatement pstatement = Factory.getDbUtilInstance().getConnection()
				.prepareStatement(GET_USER_BY_EMAIL_ID);
		pstatement.setString(1, emailId);
		ResultSet rs = null;
		try {
			rs = pstatement.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} finally {
			rs.close();
		}
	}

	@Override
	public String changePassword(String emailId, String oldPassword, String newPassword)
			throws SQLException, UserNotFoundException, PasswordChangeFailedException, InvalidKeyException,
			UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if (AppProperties.properties.getProperty("admin.emailId").equals(emailId)) {
			throw new PasswordChangeFailedException("Password cannot be changed for admin account.");
		}
		if (!verifyUser(emailId, oldPassword)) {
			throw new PasswordChangeFailedException("Old password does not match the password in the database");
		}
		String encodedNewPassword = CryptoUtil.encodePassword(newPassword);
		PreparedStatement pstatement = Factory.getDbUtilInstance().getConnection()
				.prepareStatement(UPDATE_PASSWORD_FOR_EMAIL_ID);
		pstatement.setString(1, encodedNewPassword);
		pstatement.setString(2, emailId);
		pstatement.execute();
		return encodedNewPassword;
	}

	@Override
	public String resetPassword(String emailId)
			throws UserNotFoundException, SQLException, InvalidKeyException, UnsupportedEncodingException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, PasswordChangeFailedException {
		if (AppProperties.properties.getProperty("admin.emailId").equals(emailId)) {
			throw new PasswordChangeFailedException("Password cannot be reset for admin account");
		}
		if (!isUserExistByEmailId(emailId)) {
			throw new UserNotFoundException("User with email ID '" + emailId + "' not found");
		}
		String newPassword = generateRandomPassword();
		String encodedNewPassword = CryptoUtil.encodePassword(newPassword);
		PreparedStatement pstatement = Factory.getDbUtilInstance().getConnection()
				.prepareStatement(UPDATE_PASSWORD_FOR_EMAIL_ID);
		pstatement.setString(1, encodedNewPassword);
		pstatement.setString(2, emailId);
		pstatement.execute();

		// Send Email
		String message = "Your new password is : " + newPassword + ". Generated by Team 20 - CSCI 5308 Summer 2020.";
		String subject = "Password reset successfullly";
		EmailUtil.sendEmail(emailId, subject, message);

		return newPassword;
	}

	@Override
	public User addAdminUser() throws SQLException, UserNotFoundException, InvalidKeyException,
			UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		final String adminEmailId = AppProperties.properties.getProperty("admin.emailId");
		if (!isUserExistByEmailId(adminEmailId)) {
			final String adminPassword = AppProperties.properties.getProperty("admin.password");
			final User adminUser = new User(1L, "B00ADMIN", "Admin", "Admin", adminEmailId);
			addUser(adminUser.getFirstName(), adminUser.getLastName(), adminUser.getEmailId(), adminUser.getBannerId(),
					adminPassword);
			System.out.println("Admin user created for - " + adminEmailId);
		}
		return getUserByEmail(adminEmailId);

	}

	@Override
	public boolean verifyUser(String emailId, String password) throws SQLException, UserNotFoundException,
			InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		PreparedStatement pstatement = Factory.getDbUtilInstance().getConnection()
				.prepareStatement(VERIFY_USER_CREDENTIALS);
		pstatement.setString(1, emailId);
		pstatement.setString(2, CryptoUtil.encodePassword(password));
		ResultSet rs = null;
		try {
			rs = pstatement.executeQuery();
			if (!rs.next()) {
				return false;
			}
			return true;
		} finally {
			rs.close();
		}
	}

	@Override
	public String generateRandomPassword() {
		final char[] specialCharacters = { '!', '@', '#', '$', '%', '^', '&', '*' };
		final Random random = new Random();
		final StringBuilder password = new StringBuilder();
		final StringBuilder partA = new StringBuilder();
		final StringBuilder partB = new StringBuilder();
		final char specialChar = specialCharacters[random.nextInt(specialCharacters.length)];
		int integer = random.nextInt(100);
		if (integer < 10) {
			integer += 10;
		}
		for (int i = 0; i < 3; i++) {
			char c = (char) (65 + random.nextInt(25));
			partA.append(c);
		}
		for (int i = 0; i < 3; i++) {
			char c = (char) (65 + random.nextInt(25));
			partB.append(c);
		}
		password.append(partA.toString().toLowerCase()).append(specialChar).append(integer).append(partB.toString());
		return password.toString();
	}

}