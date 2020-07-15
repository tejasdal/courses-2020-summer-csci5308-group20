package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.SystemConfig;

@Controller
public class CourseController
{
	private Logger log = LoggerFactory.getLogger(CourseController.class);
	private static final String ID = "id";
	
	@GetMapping("/course/course")
	public String course(Model model, @RequestParam(name = ID) long courseID)
	{
		log.info("Processing a request to load a course with ID: {}", courseID);
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		Course course = new Course();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		List<Role> userRoles = course.getAllRolesForCurrentUserInCourse();
		if (null == userRoles)
		{
			// Default is user is a guest.
			model.addAttribute("instructor", false);
			model.addAttribute("ta", false);
			model.addAttribute("student", false);
			model.addAttribute("guest", true);
		}
		else
		{
			model.addAttribute("instructor", userRoles.contains(Role.INSTRUCTOR));
			model.addAttribute("ta", userRoles.contains(Role.TA));
			model.addAttribute("student", userRoles.contains(Role.STUDENT));
			model.addAttribute("guest", userRoles.isEmpty());
		}
		return "course/course";
	}
}
