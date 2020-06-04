package org.dal.cs5308.t20.Project.course.service;

import org.dal.cs5308.t20.Project.course.Course;
import org.dal.cs5308.t20.Project.course.exception.CourseException;
import org.dal.cs5308.t20.Project.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICourseService {

    void registerUploadedStudent(Long courseId, MultipartFile file, String fileName) throws CourseException;

    List<User> getRegisteredStudentForCourse(Long courseId) throws CourseException;

    List<User> getCourseTAs(Long courseId) throws CourseException;

    void assignCourseTA(Long courseId, Long userId) throws CourseException;

    boolean isValidCourse(Long courseId) throws CourseException;

//    List<User> searchUser(String bannerId, String emailId);

}
