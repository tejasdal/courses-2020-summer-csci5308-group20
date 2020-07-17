package CSCI5308.GroupFormationTool.AccessControlTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;

@SpringBootTest
@SuppressWarnings("deprecation")
public class UserTest
{
	private static IUser u;
	private static IUserPersistence userDBMock;

	@BeforeEach
	public void setUp(){
		UserTestAbstractFactory userTestAbstractFactory = new UserTestConcreteFactory();
		u = userTestAbstractFactory.makeUser();
		userDBMock = userTestAbstractFactory.makeUserPersistence();
	}

	@Test
	public void ConstructorTests()
	{
		assertTrue(u.getBannerID().isEmpty());
		assertTrue(u.getFirstName().isEmpty());
		assertTrue(u.getLastName().isEmpty());
		assertTrue(u.getEmail().isEmpty());
		
		u = new User(1, userDBMock);
		assertTrue(u.getBannerID().equals("B00000000"));
		
		u = new User("B00000000", userDBMock);
		assertTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void setIDTest()
	{
		u.setID(10);
		assertTrue(10 == u.getID());
	}
	
	@Test
	public void getIDTest()
	{
		u.setID(10);
		assertTrue(10 == u.getID());
	}
	
	@Test
	public void setBannerIDTest()
	{
		u.setBannerID("B00000000");
		assertTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void getBannerIDTest()
	{
		u.setBannerID("B00000000");
		assertTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void setFirstNameTest()
	{
		u.setFirstName("Rob");
		assertTrue(u.getFirstName().equals("Rob"));
	}
	
	@Test
	public void getFirstNameTest()
	{
		u.setFirstName("Rob");
		assertTrue(u.getFirstName().equals("Rob"));
	}

	@Test
	public void setLastNameTest()
	{
		u.setLastName("Hawkey");
		assertTrue(u.getLastName().equals("Hawkey"));
	}

	@Test
	public void getLastNameTest()
	{
		u.setLastName("Hawkey");
		assertTrue(u.getLastName().equals("Hawkey"));
	}
	
	@Test
	public void setEmailTest()
	{
		u.setEmail("rhawkey@dal.ca");
		assertTrue(u.getEmail().equals("rhawkey@dal.ca"));
	}
	
	@Test
	public void getEmailTest()
	{
		u.setEmail("rhawkey@dal.ca");
		assertTrue(u.getEmail().equals("rhawkey@dal.ca"));
	}
	
	@Test
	public void createUser()
	{		
		User user = new User();
		userDBMock.createUser(user);
		assertTrue(user.getId() == 0);
		assertTrue(user.getBannerID().equals("B00000000"));
	}

	@Test
	public void isBannerIDValidTest()
	{
		assertTrue(User.isBannerIDValid("B000000000"));
		assertFalse(User.isBannerIDValid(null));
		assertFalse(User.isBannerIDValid(""));
	}
		
	@Test
	public void isFirstNameValidTest()
	{
		assertTrue(User.isFirstNameValid("rob"));
		assertFalse(User.isFirstNameValid(null));
		assertFalse(User.isFirstNameValid(""));
	}
	
	@Test
	public void isLastNameValidTest()
	{
		assertTrue(User.isLastNameValid("hawkey"));
		assertFalse(User.isLastNameValid(null));
		assertFalse(User.isLastNameValid(""));
	}
	
	@Test
	public void isEmailValidTest()
	{
		assertTrue(User.isEmailValid("rhawkey@dal.ca"));
		assertFalse(User.isEmailValid(null));
		assertFalse(User.isEmailValid(""));
		assertFalse(User.isEmailValid("@dal.ca"));
		assertFalse(User.isEmailValid("rhawkey@"));
	}	
}
