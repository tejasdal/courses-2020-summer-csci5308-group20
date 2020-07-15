package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDB implements ICoursePersistence
{
	private Logger log = LoggerFactory.getLogger(CourseDB.class);

	public List<Course> loadAllCourses()
	{
		log.trace("Loading all courses from database.");
		List<Course> courses = new ArrayList<Course>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadAllCourses()");
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long id = results.getLong(1);
					String title = results.getString(2);
					Course c = new Course();
					c.setId(id);
					c.setTitle(title);
					courses.add(c);
				}
			}
		}
		catch (SQLException e)
		{
			log.error("Error while loading all courses from database, error: {}", e.getMessage());
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return courses;
	}

	public void loadCourseByID(long id, Course course)
	{
		log.trace("Loading a course by ID: {} from database.", course.getId());
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindCourseByID(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					String title = results.getString(2);
					course.setId(id);
					course.setTitle(title);
				}
			}
		}
		catch (SQLException e)
		{
			log.error("Error while loading a course with ID: {} from database, erorr: {}", course.getId(), e.getMessage());
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
	}
	
	public boolean createCourse(Course course)
	{
		log.trace("Creating a new course with title: {} to database.", course.getTitle());
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateCourse(?, ?)");
			proc.setParameter(1, course.getTitle());
			proc.registerOutputParameterLong(2);
			proc.execute();
		}
		catch (SQLException e)
		{
			log.error("Error while creating a course with title: {} to database, error: {}", course.getTitle(), e.getMessage());
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return true;
	}
	
	public boolean deleteCourse(long id)
	{
		log.trace("Deleting a course with ID: {} from database.", id);
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spDeleteCourse(?)");
			proc.setParameter(1, id);
			proc.execute();
		}
		catch (SQLException e)
		{
			log.error("Error while deleting a course with ID: {} from database, error: {}", id, e.getMessage());
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return true;
	}
}
