	package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

	public interface ICourseUserRelationshipPersistence
{
	public List<IUser> findAllUsersWithoutCourseRole(Role role, long courseID);
	public List<IUser> findAllUsersWithCourseRole(Role role, long courseID);
	public boolean enrollUser(ICourse course, IUser user, Role role);
	public List<Role> loadUserRolesForCourse(ICourse course, IUser user);
}
