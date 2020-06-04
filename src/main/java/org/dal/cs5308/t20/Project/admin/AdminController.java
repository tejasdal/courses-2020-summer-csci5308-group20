package org.dal.cs5308.t20.Project.admin;

import java.util.List;

import org.dal.cs5308.t20.Project.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController{
	
	@Autowired
	private IAdminService adminService;

	@RequestMapping("/admin")
	public String displayCourses(Model model) {
		model.addAttribute("courses", adminService.getAllCourse());
		return "admin";
	}
	
	@RequestMapping("/courseform")
	public String courseForm(Model model) {
		model.addAttribute("course",new Course());
		return "courseform" ;
	}
	
	
	@RequestMapping(value = "/addcourse",method = RequestMethod.POST)
	public String addCourse(@ModelAttribute(value="course") Course course, RedirectAttributes redirectAttribute) {
		String status=adminService.addCourse(course);
		redirectAttribute.addFlashAttribute("status",status);
		return "redirect:/courseform" ;
	}
	
	@RequestMapping(value = "/delcourse", method = RequestMethod.POST)
	public String delCourse(@RequestParam(name="reg_no",required=true) Integer id, RedirectAttributes redirectAttribute) {
		String status=adminService.delCourse(id);
		redirectAttribute.addFlashAttribute("statusdel",status);
		return "redirect:/admin";
		
	}
	
	@RequestMapping(value = "/instructF", method=RequestMethod.POST)
	public String addInstructPage(Model model,@RequestParam(name="id",required=true) Integer id) {
		model.addAttribute("id",id);
		return "instructSearch";
	}
	
	@RequestMapping(value = "/addinstructor", method=RequestMethod.POST)
	public String addInstructor(@RequestParam(name="id",required=true) Integer id,
			@RequestParam(name="emailId",required=true) String emailId, RedirectAttributes redirectAttribute) {
		String status=adminService.addInstructor(emailId, id);
		redirectAttribute.addFlashAttribute("statusInst",status);
		return "redirect:/admin";
	}
	
	
	
}
