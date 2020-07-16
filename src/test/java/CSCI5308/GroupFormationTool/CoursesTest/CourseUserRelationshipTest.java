package CSCI5308.GroupFormationTool.CoursesTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.AccessControlTest.CurrentUserMock;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.Role;

@SpringBootTest
@SuppressWarnings("deprecation")
class CourseUserRelationshipTest 
{
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private ICourse course;
	private IUser user;

	public CourseUserRelationshipTest() 
	{
		CoursesTestAbstractFactory coursesTestAbstractFactory = new CoursesTestConcreteFactory();
		courseUserRelationshipDB = coursesTestAbstractFactory.makeCourseUserRelationshipPersistence();
		course = coursesTestAbstractFactory.makeCourse();
		CurrentUserMock currentUserMock = new CurrentUserMock();
		user = currentUserMock.getCurrentAuthenticatedUser();
	}

	@Test
	public void userHasRoleInCourse() 
	{
		course.setId(0);
		List<Role> roles = courseUserRelationshipDB.loadUserRolesForCourse(course, user);
		assertThat(roles).isNotNull();
		assertThat(roles).isNotEmpty();
		Assert.isTrue(roles.size() > 0);
	}

	@Test
	public void loadAllRoluesForUserInCourse() 
	{
		course.setId(0);
		List<Role> roles = courseUserRelationshipDB.loadUserRolesForCourse(course, user);
		Assert.isTrue(roles.size() > 0);
	}

	@Test
	public void enrollUserInCourse() 
	{
		boolean result = courseUserRelationshipDB.enrollUser(course, user, Role.STUDENT);
		Assert.isTrue(result);
	}

}
