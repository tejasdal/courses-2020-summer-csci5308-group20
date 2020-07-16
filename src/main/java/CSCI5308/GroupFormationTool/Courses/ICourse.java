package CSCI5308.GroupFormationTool.Courses;

import CSCI5308.GroupFormationTool.AccessControl.User;

import java.util.List;

public interface ICourse {
    void setDefaults();

    // I don't want to name this method this way, but unfortunately Spring and Thymeleaf are
    // full of magical underneath the hood connection mechanisms that force me to name it this way.
    void setId(long id);

    // I don't want to name this method this way, but unfortunately Spring and Thymeleaf are
    // full of magical underneath the hood connection mechanisms that force me to name it this way.
    long getId();

    void setTitle(String title);

    String getTitle();

    boolean delete(ICoursePersistence courseDB);

    boolean createCourse(ICoursePersistence courseDB);

    boolean enrollUserInCourse(Role role, User user);

    boolean isCurrentUserEnrolledAsRoleInCourse(Role role);

    boolean isCurrentUserEnrolledAsRoleInCourse(String role);

    List<Role> getAllRolesForCurrentUserInCourse();
}
