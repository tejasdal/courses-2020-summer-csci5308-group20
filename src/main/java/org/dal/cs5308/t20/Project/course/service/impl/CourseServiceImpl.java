package org.dal.cs5308.t20.Project.course.service.impl;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.dal.cs5308.t20.Project.EmailUtil;
import org.dal.cs5308.t20.Project.Factory;
import org.dal.cs5308.t20.Project.course.bo.Student;
import org.dal.cs5308.t20.Project.course.repo.CourseRepo;
import org.dal.cs5308.t20.Project.course.service.CourseService;
import org.dal.cs5308.t20.Project.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    private IUserService userService = Factory.getUserService();

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public void registerUploadedStudent(Long courseId, MultipartFile file, String fileName) {
        try {
            List<Student> studentsToRegister = this.parseCsvFileToRead(file);
            if (studentsToRegister != null && !studentsToRegister.isEmpty()) {
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
            } else {
                log.info("File: {} is empty!", fileName);
            }
        } catch (IOException e) {
            log.error("Failed to parse file to register student into course: ", e);
        }
    }

    @Override
    public List<Student> getRegisteredStudentForCourse(Long courseId) {
        try {
            return this.courseRepo.getRegisteredStudentForCourse(courseId);
        } catch (Exception e) {
            log.error("Failed to fetch students registered in the course with ID: {}", courseId);
        }
        return null;
    }

    private List<Student> parseCsvFileToRead(MultipartFile file) throws IOException {
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
        }catch (Exception e){
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

