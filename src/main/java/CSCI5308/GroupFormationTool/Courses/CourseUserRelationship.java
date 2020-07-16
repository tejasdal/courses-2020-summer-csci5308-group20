package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.*;

public class CourseUserRelationship implements ICourseUserRelationship
{
	public boolean userHasRoleInCourse(IUser user, Role role, ICourse course)
	{
		if (null == user || user.isValidUser() == false)
		{
			return false;
		}
		if (null == role)
		{
			return false;
		}
		if (null == course)
		{
			return false;
		}
		ICourseUserRelationshipPersistence userCourseRelationshipDB = CoursePersistenceAbstractFactory.instance().makeCourseUserRelationshipPersistence();
		List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);
		if (null != roles && roles.contains(role))
		{
			return true;
		}
		return false;
	}

	public List<Role> loadAllRoluesForUserInCourse(IUser user, ICourse course)
	{
		ICourseUserRelationshipPersistence userCourseRelationshipDB = CoursePersistenceAbstractFactory.instance().makeCourseUserRelationshipPersistence();
		List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);
		return roles;
	}

	public boolean enrollUserInCourse(IUser user, Course course, Role role)
	{
		ICourseUserRelationshipPersistence userCourseRelationshipDB = CoursePersistenceAbstractFactory.instance().makeCourseUserRelationshipPersistence();
		return userCourseRelationshipDB.enrollUser(course, user, role);
	}
}
