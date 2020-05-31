package org.dal.cs5308.t20.Project.course.controller;

import org.dal.cs5308.t20.Project.course.bo.Student;
import org.dal.cs5308.t20.Project.course.service.CourseService;
import org.dal.cs5308.t20.Project.dd.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/course/{id}/students/register/upload-csv")
    public String getIndex(Model model, @PathVariable("id") Long courseId){
        model.addAttribute("fileName", new String());
        model.addAttribute("courseId", courseId);
        return "studentRegistrationUploadCsv";
    }

    @PostMapping(value = "/course/{id}/students/register/upload-csv")
    public String registerUploadedStudent(@ModelAttribute String fileName,@PathVariable("id") Long courseId,
                                          @RequestParam("file") MultipartFile file,
                                          RedirectAttributes redirectAttributes) {

        this.courseService.registerUploadedStudent(courseId, file, fileName);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded"+ /* + file.getOriginalFilename() + */ fileName +"!");
        return "redirect:/course/"+courseId+"/students";
    }

    @GetMapping("/course/{id}/students")
    public String getRegisteredStudentForCourse(Model model, @PathVariable("id") Long courseId){
        List<Student> students = this.courseService.getRegisteredStudentForCourse(courseId);
        model.addAttribute("students", students);
        return "studentRegistered";
    }
}
