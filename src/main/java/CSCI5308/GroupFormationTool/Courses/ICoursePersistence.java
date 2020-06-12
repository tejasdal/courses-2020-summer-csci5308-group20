package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

public interface ICoursePersistence
{
	public List<Course> loadAllCourses();
	public void loadCourseByID(long id, Course course);
	public boolean createCourse(Course course);
	public boolean deleteCourse(long id);
}
