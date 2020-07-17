package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

public interface ICoursePersistence
{
	public List<ICourse> loadAllCourses();
	public void loadCourseByID(long id, Course course);
	public boolean createCourse(ICourse course);
	public boolean deleteCourse(long id);
}
