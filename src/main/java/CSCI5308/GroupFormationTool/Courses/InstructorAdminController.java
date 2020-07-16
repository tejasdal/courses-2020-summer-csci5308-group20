package CSCI5308.GroupFormationTool.Courses;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class InstructorAdminController {
	private Logger log = LoggerFactory.getLogger(InstructorAdminController.class);
    private static final String ID = "id";
    private static final String FILE = "file";
    private static final String SUCCESSFUL = "successful";
    private static final String FAILURES = "failures";
    private static final String DISPLAY_RESULTS = "displayresults";

    @GetMapping("/course/instructoradmin")
    public String instructorAdmin(Model model, @RequestParam(name = ID) long courseID) {
		log.info("Processing a request to load a course admin page for instructor and ta.");
        ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
        Course course = new Course();
        courseDB.loadCourseByID(courseID, course);
        model.addAttribute("course", course);
        model.addAttribute("displayresults", false);
        if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) ||
                course.isCurrentUserEnrolledAsRoleInCourse(Role.TA)) {
            User currentUser = CurrentUser.instance().getCurrentAuthenticatedUser();
            if (null != currentUser) {
                model.addAttribute("isInstructor", course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR));
                model.addAttribute("instructorId", currentUser.getID());
            }
            return "course/instructoradmin";
        } else {
            return "index";
        }
    }

    @GetMapping("/course/instructoradminresults")
    public String instructorAdminResults(
            Model model,
            @RequestParam(name = ID) long courseID,
            @RequestParam(name = SUCCESSFUL, required = false) List<String> successful,
            @RequestParam(name = FAILURES, required = false) List<String> failures,
            @RequestParam(name = DISPLAY_RESULTS) boolean displayResults) {
		log.info("Processing a request to redirect to a course admin page to display results of tas enroll request.");
        ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
        Course course = new Course();
        courseDB.loadCourseByID(courseID, course);
        model.addAttribute("course", course);
        model.addAttribute("displayresults", false);
        model.addAttribute(SUCCESSFUL, successful);
        model.addAttribute(FAILURES, failures);
        model.addAttribute(DISPLAY_RESULTS, displayResults);
        if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) ||
                course.isCurrentUserEnrolledAsRoleInCourse(Role.TA)) {
            return "course/instructoradmin";
        } else {
            return "index";
        }
    }


    @GetMapping("/course/enrollta")
    public String enrollTA(Model model, @RequestParam(name = ID) long courseID) {
		log.info("Processing a request to load a page to enroll tas for a course with ID: {}.", courseID);
        ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
        Course course = new Course();
        courseDB.loadCourseByID(courseID, course);
        model.addAttribute("course", course);
        if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) ||
                course.isCurrentUserEnrolledAsRoleInCourse(Role.TA)) {
            return "course/enrollta";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/course/uploadcsv", consumes = {"multipart/form-data"})
    public ModelAndView upload(@RequestParam(name = FILE) MultipartFile file, @RequestParam(name = ID) long courseID) {
		log.info("Processing a request to enroll tas for a course with ID: {}.", courseID);
        ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
        Course course = new Course();
        courseDB.loadCourseByID(courseID, course);
        IStudentCSVParser parser = new StudentCSVParser(file);
        StudentCSVImport importer = new StudentCSVImport(parser, course);
        ModelAndView mav = new ModelAndView("redirect:/course/instructoradminresults?id=" + Long.toString(courseID));
        mav.addObject("successful", importer.getSuccessResults());
        mav.addObject("failures", importer.getFailureResults());
        mav.addObject("displayresults", true);

        return mav;
    }
}
