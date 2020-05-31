package org.dal.cs5308.t20.Project.course.service;

import org.dal.cs5308.t20.Project.course.bo.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {

    void registerUploadedStudent(Long courseId, MultipartFile file, String fileName);

    List<Student> getRegisteredStudentForCourse(Long courseId);
}
