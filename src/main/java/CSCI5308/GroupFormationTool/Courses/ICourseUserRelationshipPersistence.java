	package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;

public interface ICourseUserRelationshipPersistence
{
	public List<User> findAllUsersWithoutCourseRole(Role role, long courseID);
	public List<User> findAllUsersWithCourseRole(Role role, long courseID);
	public boolean enrollUser(Course course, User user, Role role);
	public List<Role> loadUserRolesForCourse(Course course, User user);
}
