package org.dal.cs5308.t20.Project.admin;

import java.sql.SQLException;
import java.util.List;

import org.dal.cs5308.t20.Project.course.Course;
import org.dal.cs5308.t20.Project.course.ICourseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminService implements IAdminService {
	
	@Autowired
	ICourseDAO dao;

	@Override
	public String addCourse(Course course) {
		int status=0;

		if(course.getId()==null)
		{
			return "Unable to Add Course! ID cannot be empty";
		}
		else if(course.getName()==null || course.getName().equals(""))
		{
			return "Unable to Add Course! Name cannot be empty";
		}
		try {
			status = dao.addCourse(course);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(status>0) {
			return "Successfully Added Course";
		}
		else {
			return"Failed!!";
		}
	}

	@Override
	public String delCourse(Integer course_id) {
		int status=0;
		
		if(course_id==null) {
			return "Unable to delete course";
		}
		
		try {
			status = dao.delCourse(course_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(status>0) {
			return "Successfully Deleted the Course";
		}
		else {
			return"Failed";
		}
	}

	@Override
	public List<Course> getAllCourse() {
		
		List<Course> course_list = null;
		try {
			course_list = dao.getAllCourses();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course_list;
	}

	@Override
	public String addInstructor(String emailId, Integer course_id) {
		
		int status=0;
		if(emailId==null || emailId.equals(""))
			return "EmailID cannot be empty";

		
		try {
			status = dao.addInstructor(emailId, course_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(status>0) {
			return "Instructor Added";
		}
		else {
			return"User not found Try Again";
		}
	}
	
	




}
