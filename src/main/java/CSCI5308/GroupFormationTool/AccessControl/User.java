package CSCI5308.GroupFormationTool.AccessControl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class User implements IUser {
	// This regex comes from here:
	// https://howtodoinjava.com/regex/java-regex-validate-email-address/
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	
	private long id;
	private String password;
	private String bannerID;
	private String firstName;
	private String lastName;
	private String email;
	
	public User()
	{
		setDefaults();
	}
	
	public User(long id, IUserPersistence persistence)
	{
		setDefaults();
		persistence.loadUserByID(id, this);
	}
	
	public User(String bannerID, IUserPersistence persistence)
	{
		setDefaults();
		persistence.loadUserByBannerID(bannerID, this);
	}
	
	@Override
	public void setDefaults()
	{
		id = -1;
		password = "";
		bannerID = "";
		firstName = "";
		lastName = "";
		email = "";
	}
	
	@Override
	public void setID(long id)
	{
		this.id = id;
	}
	
	@Override
	public long getID()
	{
		return id;
	}
	
	@Override
	public void setId(long id)
	{
		this.id = id;
	}
	@Override
	public long getId()
	{
		return id;
	}
	
	@Override
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	@Override
	public String getPassword()
	{
		return password;
	}
	
	@Override
	public void setBannerID(String bannerID)
	{
		this.bannerID = bannerID;
	}
	
	@Override
	public String getBannerID()
	{
		return bannerID;
	}

	@Override
	public String getBanner()
	{
		return bannerID;
	}
	
	@Override
	public void setFirstName(String name)
	{
		firstName = name;
	}
	
	@Override
	public String getFirstName()
	{
		return firstName;
	}
	
	@Override
	public void setLastName(String name)
	{
		lastName = name;
	}
	
	@Override
	public String getLastName()
	{
		return lastName;
	}
	
	@Override
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	@Override
	public String getEmail()
	{
		return email;
	}
	
	@Override
	public boolean isValidUser()
	{
		boolean valid = true;
		if (id == -1) {
			valid = false;
		}
		return valid; 
	}
	
	@Override
	public boolean createUser(
			IUserPersistence userDB,
			IPasswordEncryption passwordEncryption,
			IUserNotifications notification)
	{
		String rawPassword = password;
		this.password = passwordEncryption.encryptPassword(this.password);
		boolean success = userDB.createUser(this);
		boolean notificationNotNull = true;
		if (notification == null) {
			notificationNotNull = false;
		}
		if (success && notificationNotNull)
		{
			notification.sendUserLoginCredentials(this, rawPassword);
		}
		return success;
	}
	
	@Override
	public boolean updateUser(IUserPersistence userDB)
	{
		return userDB.updateUser(this);
	}
	
	private static boolean isStringNullOrEmpty(String s)
	{
		if (null == s)
		{
			return true;
		}
		return s.isEmpty();
	}
	
	public static boolean isBannerIDValid(String bannerID)
	{
		return !isStringNullOrEmpty(bannerID);
	}
		
	public static boolean isFirstNameValid(String name)
	{
		return !isStringNullOrEmpty(name);
	}
	
	public static boolean isLastNameValid(String name)
	{
		return !isStringNullOrEmpty(name);
	}
	
	public static boolean isEmailValid(String email)
	{
		if (isStringNullOrEmpty(email))
		{
			return false;
		}
		 
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}