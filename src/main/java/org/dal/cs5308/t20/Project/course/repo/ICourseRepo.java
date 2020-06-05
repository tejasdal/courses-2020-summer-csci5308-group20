package org.dal.cs5308.t20.Project.course.repo;

import org.dal.cs5308.t20.Project.course.exception.CourseException;
import org.dal.cs5308.t20.Project.user.User;

import java.sql.SQLException;
import java.util.List;

public interface ICourseRepo {

    void registerStudentIntoCourse(Long courseId, String bannerId) throws SQLException;

    List<User> getRegisteredStudentForCourse(Long courseId) throws SQLException;

    boolean isStudentAlreadyRegistered(String bannerId, Long courseId) throws Exception;

    List<User> getCourseTAs(Long courseId) throws SQLException;

    void assignCourseTA(Long courseId, Long userId) throws SQLException;

    boolean isValidCourse(Long courseId) throws CourseException;

    List<String> getCourseRolesByUserNameAndCourseId(String name, Long courseId) throws CourseException;
}
