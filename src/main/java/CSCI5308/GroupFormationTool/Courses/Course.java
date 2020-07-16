package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.IUser;

public class Course implements ICourse {
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
	
	@Override
	public void setDefaults()
	{
		id = -1;
		title = "";
		userRoleDecider = new CourseUserRelationship();
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
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	@Override
	public String getTitle()
	{
		return title;
	}
	
	@Override
	public boolean delete(ICoursePersistence courseDB)
	{
		return courseDB.deleteCourse(id);
	}
	
	@Override
	public boolean createCourse(ICoursePersistence courseDB)
	{
		return courseDB.createCourse(this);
	}
	
	@Override
	public boolean enrollUserInCourse(Role role, IUser user)
	{
		return userRoleDecider.enrollUserInCourse(user, this, role);
	}
	
	@Override
	public boolean isCurrentUserEnrolledAsRoleInCourse(Role role)
	{
		return userRoleDecider.userHasRoleInCourse(CurrentUser.instance().getCurrentAuthenticatedUser(), role, this);
	}

	@Override
	public boolean isCurrentUserEnrolledAsRoleInCourse(String role)
	{
		Role r = Role.valueOf(role.toUpperCase());
		return isCurrentUserEnrolledAsRoleInCourse(r);
	}
	
	@Override
	public List<Role> getAllRolesForCurrentUserInCourse()
	{
		return userRoleDecider.loadAllRoluesForUserInCourse(CurrentUser.instance().getCurrentAuthenticatedUser(), this);
	}
}
