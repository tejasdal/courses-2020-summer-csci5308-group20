package org.dal.cs5308.t20.Project.course.repo;

import org.dal.cs5308.t20.Project.course.bo.Student;

import java.sql.SQLException;
import java.util.List;

public interface CourseRepo {

    void registerStudentIntoCourse(Long courseId, String bannerId) throws SQLException;

    List<Student> getRegisteredStudentForCourse(Long courseId) throws SQLException;

    boolean isStudentAlreadyRegistered(String bannerId, Long courseId) throws Exception;
}
