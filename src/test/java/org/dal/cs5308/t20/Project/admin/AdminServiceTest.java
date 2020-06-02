package org.dal.cs5308.t20.Project.admin;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dal.cs5308.t20.Project.course.Course;
import org.dal.cs5308.t20.Project.course.CourseDAO;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


public class AdminServiceTest {
	
	@InjectMocks
	AdminService adminService;
	
	@Mock
	CourseDAO dao;
	
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void addCourseTest() {
    	Course course = new Course();
    	course.setId(1234);
    	course.setName("TestCourse");
    	
		try {
			when(dao.addCourse(course)).thenReturn(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	assertEquals("Successfully Added Course", adminService.addCourse(course));
    	
    }

    @Test
	public void addCourseTestIdNull()
	{
		Course course = new Course();
		course.setId(null);
		course.setName("TestCourse");

		try {
			when(dao.addCourse(course)).thenReturn(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Unable to Add Course! ID cannot be empty", adminService.addCourse(course));

	}

	@Test
	public void addCourseTestNameNull()
	{
		Course course = new Course();
		course.setId(12324);
		course.setName(null);

		try {
			when(dao.addCourse(course)).thenReturn(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Unable to Add Course! Name cannot be empty", adminService.addCourse(course));

	}

	@Test
	public void addCourseTestNameEmpty()
	{
		Course course = new Course();
		course.setId(12324);
		course.setName("");

		try {
			when(dao.addCourse(course)).thenReturn(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Unable to Add Course! Name cannot be empty", adminService.addCourse(course));

	}

	@Test
	public void delCourseTest(){


		try {
			when(dao.delCourse(1)).thenReturn(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("Successfully Deleted the Course", adminService.delCourse(1));
	}

	@Test
	public void delCourseTestIdNull(){

		try {
			when(dao.delCourse(null)).thenReturn(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("Unable to delete course", adminService.delCourse(null));
	}

	@Test
	public void getAllCourseTest(){
    	List<Course> list = new ArrayList<Course>();
    	Course c1=new Course();
    	c1.setId(123);
    	c1.setName("Test1");
		Course c2=new Course();
		c2.setId(12345);
		c2.setName("Test2");

    	list.add(c1);
    	list.add(c2);


		try {
			when(dao.getAllCourses()).thenReturn(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Course> courselist=adminService.getAllCourse();

		assertEquals(2,courselist.size());

	}
	
	@Test
	public void addInstructorTest() {
		
		try {
			when(dao.addInstructor("acc@dal.ca", 111)).thenReturn(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Instructor Added", adminService.addInstructor("acc@dal.ca", 111));		
		
	}

	@Test
	public void addInstructorTestEmailNull() {

		try {
			when(dao.addInstructor(null, 111)).thenReturn(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("EmailID cannot be empty", adminService.addInstructor(null, 111));

	}

	@Test
	public void addInstructorTestEmailEmpty() {

		try {
			when(dao.addInstructor("", 111)).thenReturn(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("EmailID cannot be empty", adminService.addInstructor("", 111));

	}

	@Test
	public void addInstructorEmailTestNotFound() {

		try {
			when(dao.addInstructor("test", 111)).thenReturn(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("User not found Try Again", adminService.addInstructor("test", 111));

	}

}




