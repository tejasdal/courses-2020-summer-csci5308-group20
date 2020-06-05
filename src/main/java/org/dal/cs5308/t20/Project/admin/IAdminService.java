package org.dal.cs5308.t20.Project.admin;

import java.util.List;

import org.dal.cs5308.t20.Project.course.Course;

public interface IAdminService {
	
	public String addCourse(Course course);
	
	public String delCourse(Long course_id);
	
	public List<Course> getAllCourse();
	
	public String addInstructor(String emailId, Long course_id);
	

}
