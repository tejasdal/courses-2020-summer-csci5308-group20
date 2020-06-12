package CSCI5308.GroupFormationTool.CoursesTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.Role;

class CourseUserRelationshipDBMock implements ICourseUserRelationshipPersistence 
{
	public List<User> findAllUsersWithoutCourseRole(Role role, long courseID) 
	{
		List<User> userList = new ArrayList<>();
		User u = new User();
		u.setId(0);
		userList.add(u);
		u = new User();
		u.setId(1);
		userList.add(u);
		return userList;
	}

	public List<User> findAllUsersWithCourseRole(Role role, long courseID) 
	{
		List<User> userList = new ArrayList<>();
		User u = new User();
		u.setId(0);
		userList.add(u);
		u = new User();
		u.setId(1);
		userList.add(u);
		return userList;
	}

	public boolean enrollUser(Course course, User user, Role role) 
	{
		user.setId(0);
		course.setId(0);
		course.setTitle("Software Engineering");
		return true;

	}

	public List<Role> loadUserRolesForCourse(Course course, User user) 
	{
		List<Role> userRoles = new ArrayList<>();
		userRoles.add(Role.STUDENT);
		userRoles.add(Role.TA);
		return userRoles;
	}

}
