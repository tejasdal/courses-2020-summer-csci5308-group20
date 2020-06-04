package org.dal.cs5308.t20.Project.course.service.impl;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.dal.cs5308.t20.Project.EmailUtil;
import org.dal.cs5308.t20.Project.Factory;
import org.dal.cs5308.t20.Project.course.Course;
import org.dal.cs5308.t20.Project.course.ICourseDAO;
import org.dal.cs5308.t20.Project.course.bo.Student;
import org.dal.cs5308.t20.Project.course.exception.CourseException;
import org.dal.cs5308.t20.Project.course.repo.ICourseRepo;
import org.dal.cs5308.t20.Project.course.service.ICourseService;
import org.dal.cs5308.t20.Project.user.IUserService;
import org.dal.cs5308.t20.Project.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements ICourseService {
    Logger log = LoggerFactory.getLogger(CourseService.class);

    private IUserService userService = Factory.getUserService();

    @Autowired
    private ICourseRepo courseRepo;

//    @Autowired
//    private IUserRepo userRepo;

    @Autowired
    private ICourseDAO courseDAO;

    @Override
    public void registerUploadedStudent(Long courseId, MultipartFile file, String fileName) throws CourseException {

        this.isValidCourse(courseId);
        List<Student> studentsToRegister = this.parseCsvFileToRead(file);
        if (studentsToRegister == null || studentsToRegister.isEmpty()) {
            log.info("File: {} is empty.", fileName);
            throw new CourseException("File: " + fileName + " is empty.");
        }
        for (Student student : studentsToRegister) {
            try {
                if (student != null && !student.getBannerId().isEmpty()) {
                    // 1.1 check whether it exists or not
                    if (!this.userService.isUserExistByEmailId(student.getEmail())) {
                        // 1.2 register new student if does not exists and email them
                        this.registerNewStudent(student);
                    }
                    // 2 register student to course and return
                    this.registerStudentsIntoCourse(student, courseId);
                }
            } catch (Exception e) {
                log.error("Failed to register a student: {}", student.getBannerId(), e);
            }
        }
    }

    @Override
    public List<User> getRegisteredStudentForCourse(Long courseId) throws CourseException {
        try {
            this.isValidCourse(courseId);
            return this.courseRepo.getRegisteredStudentForCourse(courseId);
        } catch (SQLException e) {
            log.error("Failed to fetch students registered in the course with ID: {}", courseId, e);
            throw new CourseException("Failed to load registered students for course with ID: " + courseId);
        }
    }

    @Override
    public List<User> getCourseTAs(Long courseId) throws CourseException {
        log.debug("Get all TAs of the course with ID: {}", courseId);
        try {
            this.isValidCourse(courseId);
            return this.courseRepo.getCourseTAs(courseId);
        } catch (SQLException e) {
            log.error("Failed to get all TAs for a courseId: {}", courseId, e);
            throw new CourseException("Failed to fetch all TA for the course with ID: " + courseId);
        }
    }

    @Override
    public void assignCourseTA(Long courseId, Long userId) throws CourseException {
        log.debug("Assigning TA with userId: {} to a course with Id:{}", userId, courseId);

        try {
            this.isValidCourse(courseId);
            this.userService.getUserById(userId);
            this.courseRepo.assignCourseTA(courseId, userId);
        } catch (SQLException e) {
            log.error("User with ID: {} is already TA of the course with ID: {}", userId, courseId);
            throw new CourseException("User with ID: " + userId + " is already TA of the course with ID: " + courseId);
        } catch (Exception e) {
            log.error("User with not found in the database.");
            throw new CourseException("User with ID: " + userId + " not found.", e);
        }
    }

    @Override
    public boolean isValidCourse(Long courseId) throws CourseException {
        if (!this.courseRepo.isValidCourse(courseId)) {
            log.error("Invalid course with courseID: {}", courseId);
            throw new CourseException("Course with ID: " + courseId + " is not a valid course.");
        }
        return true;
    }

//    @Override
//    public List<User> searchUser(String bannerId, String emailId) {
//        log.debug("Start searching users with bannerId: {} and emailId: {}", bannerId, emailId);
//        try{
//            return this.userRepo.searchUser(bannerId, emailId);
//        }catch (SQLException e){
//            log.error("Error while searching users with bannerId: {} and emailId: {}: ",bannerId, emailId, e);
//        }
//        return new ArrayList<>();
//    }


    private List<Student> parseCsvFileToRead(MultipartFile file) throws CourseException {
        try {
            log.debug("Parsing csv file.");
            Reader fileReader = new InputStreamReader(new ByteArrayInputStream(file.getBytes()));
            //Create instance of CsvToBean object to parse file.
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Student.class);
            CsvToBean csvToBean = new CsvToBeanBuilder(fileReader)
                    .withMappingStrategy(mappingStrategy)
                    .withType(Student.class)
                    .build();
            return csvToBean.parse();
        } catch (IOException e) {
            log.error("Failed to parse the CSV file:", e);
            throw new CourseException("Failed to parse the CSV file.");
        }
    }

    private void registerNewStudent(Student student) throws Exception {
        String randomPassword = this.userService.generateRandomPassword();
        this.userService.addUser(student.getFirstName(), student.getLastName(),
                student.getEmail(), student.getBannerId(), randomPassword);
        log.debug("A new student: {} is added into the database.", student.getEmail());
        // 1.3 Method call to send email to student.
        this.sendEmailToStudent(student, randomPassword);
    }

    private void sendEmailToStudent(Student student, String password) {
        String content = "Your email " + student.getEmail() + " is registered to our portal. \n Please use the following credential to login: " +
                " Username: " + student.getBannerId() + "\n Password: " + password;
        try {
            EmailUtil.sendEmail(student.getEmail(), "Welcome to ADV Group 20!", content);
        } catch (Exception e) {
            log.error("Failed to send email to new student with bannner ID: {}", student.getBannerId());
        }
        log.debug("Email sent to new student with banner ID: {}", student.getBannerId());
    }

    private void registerStudentsIntoCourse(Student student, Long courseId) throws Exception {
        log.debug("Registering student: {} to courseId: {}.", student.getEmail(), courseId);
        // check for user already registered or not.
        if (!this.courseRepo.isStudentAlreadyRegistered(student.getBannerId(), courseId)) {
            this.courseRepo.registerStudentIntoCourse(courseId, student.getBannerId());
        }
    }
}

