package CSCI5308.GroupFormationTool.CoursesTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControlTest.CurrentUserMock;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.Role;

@SpringBootTest
@SuppressWarnings("deprecation")
class CourseUserRelationshipTest 
{
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;

	public CourseUserRelationshipTest() 
	{
		courseUserRelationshipDB = new CourseUserRelationshipDBMock();
	}

	@Test
	public void userHasRoleInCourse() 
	{
		Course course = new Course();
		course.setId(0);
		CurrentUserMock currentUser = new CurrentUserMock();
		User user = currentUser.getCurrentAuthenticatedUser();
		List<Role> roles = courseUserRelationshipDB.loadUserRolesForCourse(course, user);
		assertThat(roles).isNotNull();
		assertThat(roles).isNotEmpty();
		Assert.isTrue(roles.size() > 0);
	}

	@Test
	public void loadAllRoluesForUserInCourse() 
	{
		Course course = new Course();
		course.setId(0);
		CurrentUserMock currentUser = new CurrentUserMock();
		User user = currentUser.getCurrentAuthenticatedUser();
		List<Role> roles = courseUserRelationshipDB.loadUserRolesForCourse(course, user);
		Assert.isTrue(roles.size() > 0);
	}

	@Test
	public void enrollUserInCourse() 
	{
		Course course = new Course();
		CurrentUserMock currentUser = new CurrentUserMock();
		User user = currentUser.getCurrentAuthenticatedUser();
		boolean result = courseUserRelationshipDB.enrollUser(course, user, Role.STUDENT);
		Assert.isTrue(result);
	}

}
