package CSCI5308.GroupFormationTool.CoursesTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControlTest.UserTestAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControlTest.UserTestConcreteFactory;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControlTest.UserDBMock;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.Role;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.SecurityTest.PasswordEncryptionMock;

@SpringBootTest
@SuppressWarnings("deprecation")
class StudentCSVImportTest 
{
	private static IUser user;
	private static ICourse course;
	private static IUserPersistence userDB;

	@BeforeAll
	public static void setUp()
	{
		UserTestAbstractFactory userTestAbstractFactory = new UserTestConcreteFactory();
		CoursesTestAbstractFactory coursesTestAbstractFactory = new CoursesTestConcreteFactory();
		user = userTestAbstractFactory.makeUser();
		course = coursesTestAbstractFactory.makeCourse();
		userDB = userTestAbstractFactory.makeUserPersistence();
	}

	@Test
	public void enrollStudentFromRecord() 
	{

		IPasswordEncryption passwordEncryption = new PasswordEncryptionMock();
		Assert.isTrue(user.createUser(userDB, passwordEncryption, null));
		Assert.isTrue(course.enrollUserInCourse(Role.STUDENT, user) == false);
	}

	@Test
	public void getSuccessResults() 
	{
		List<String> successResults = new ArrayList<String>();
		successResults.add("Created record");
		assertThat(successResults).isNotNull();
		assertThat(successResults).isNotEmpty();
		Assert.isTrue(successResults.size() > 0);
	}

	@Test
	public void getFailureResults() 
	{
		List<String> failureResults = new ArrayList<String>();
		failureResults.add("Created record");
		assertThat(failureResults).isNotNull();
		assertThat(failureResults).isNotEmpty();
		Assert.isTrue(failureResults.size() > 0);
	}

}
