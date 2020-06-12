package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;

public class Course
{
	private long id;
	private String title;
	private ICourseUserRelationship userRoleDecider;
	
	public Course()
	{
		setDefaults();
	}
	
	public Course(long id, ICoursePersistence courseDB)
	{
		setDefaults();
		courseDB.loadCourseByID(id, this);
	}
	
	public void setDefaults()
	{
		id = -1;
		title = "";
		userRoleDecider = new CourseUserRelationship();
	}
	
	// I don't want to name this method this way, but unfortunately Spring and Thymeleaf are
	// full of magical underneath the hood connection mechanisms that force me to name it this way.
	public void setId(long id)
	{
		this.id = id;
	}
	
	// I don't want to name this method this way, but unfortunately Spring and Thymeleaf are
	// full of magical underneath the hood connection mechanisms that force me to name it this way.
	public long getId()
	{
		return id;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public boolean delete(ICoursePersistence courseDB)
	{
		return courseDB.deleteCourse(id);
	}
	
	public boolean createCourse(ICoursePersistence courseDB)
	{
		return courseDB.createCourse(this);
	}
	
	public boolean enrollUserInCourse(Role role, User user)
	{
		return userRoleDecider.enrollUserInCourse(user, this, role);
	}
	
	public boolean isCurrentUserEnrolledAsRoleInCourse(Role role)
	{
		return userRoleDecider.userHasRoleInCourse(CurrentUser.instance().getCurrentAuthenticatedUser(), role, this);
	}

	public boolean isCurrentUserEnrolledAsRoleInCourse(String role)
	{
		Role r = Role.valueOf(role.toUpperCase());
		return isCurrentUserEnrolledAsRoleInCourse(r);
	}
	
	public List<Role> getAllRolesForCurrentUserInCourse()
	{
		return userRoleDecider.loadAllRoluesForUserInCourse(CurrentUser.instance().getCurrentAuthenticatedUser(), this);
	}
}
