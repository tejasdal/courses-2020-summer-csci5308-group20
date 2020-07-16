package CSCI5308.GroupFormationTool.Courses;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

import java.util.List;

public interface ICourse {

    void setDefaults();

    void setId(long id);

    long getId();

    void setTitle(String title);

    String getTitle();

    boolean delete(ICoursePersistence courseDB);

    boolean createCourse(ICoursePersistence courseDB);

    boolean enrollUserInCourse(Role role, IUser user);

    boolean isCurrentUserEnrolledAsRoleInCourse(Role role);

    boolean isCurrentUserEnrolledAsRoleInCourse(String role);

    List<Role> getAllRolesForCurrentUserInCourse();
}
