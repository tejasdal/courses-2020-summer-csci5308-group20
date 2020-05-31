package org.dal.cs5308.t20.Project.course.service;

import org.dal.cs5308.t20.Project.AppProperties;
import org.dal.cs5308.t20.Project.EmailUtil;
import org.dal.cs5308.t20.Project.course.bo.Student;
import org.dal.cs5308.t20.Project.course.repo.CourseRepo;
import org.dal.cs5308.t20.Project.course.repo.impl.CourseRepoImpl;
import org.dal.cs5308.t20.Project.course.service.impl.CourseServiceImpl;
import org.dal.cs5308.t20.Project.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {

    @InjectMocks
    CourseServiceImpl courseService;

    @Mock
    CourseRepoImpl courseRepo;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        try {
            /** Mock isUserExistByEmailId repo method such that it always returns true. */
            doReturn(true).when(userService).isUserExistByEmailId("test.exists@dal.ca");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 1. Mock MultiPartFile object and file name, which are passed as argument to registerUploadedStudent method.
     * 2. Mock registerStudentIntoCourse Repo method such that instead of saving the data in db, it stores data in list.
     * 3. Call registerUploadedStudent Service method which inside calls the registerStudentIntoCourse method.
     * 4. Check size of result of the registerStudentIntoCourse method with size of data inside MultiPartFile(i.e. 3).
     * @throws SQLException
     */
    @Test
    void registerUploadedStudentTest() throws SQLException {
        String file = "B00846296,Tejas,Patel,tejas.patel@dal.ca\n" +
                "B00846297,Roshan,Patel,roshan.patel@dal.ca\n" +
                "B00846201,Karan,Kharecha,karank@dal.ca\n";
        String fileName = "test.csv";
        List<Student> students = new ArrayList<>();
        doAnswer(ans -> {
            Student student = new Student();
            student.setBannerId((String)ans.getArguments()[1]);
            students.add(student);
            return null;
        })
                .when(courseRepo).registerStudentIntoCourse(anyLong(),anyString());

        doReturn(false).when(userService).isUserExistByEmailId(anyString());
        MultipartFile multipartFile = new MockMultipartFile(fileName, file.getBytes());
        this.courseService.registerUploadedStudent(1L, multipartFile, fileName);
        assertEquals(3, students.size(), "Failed to register new students.");

    }

    @Test
    void getRegisteredStudentForCourse() {
        try {
            List<Student> students = Arrays.asList(new Student("B00846296","Tejas", "Patel", "tejas.patel@dal.ca"),
                    new Student("B00846297","Rob", "H", "robh@dal.ca"),
                    new Student("B00846298","Krutarth", "Patel", "krutarth.patel@dal.ca"));
            doReturn(students).when(courseRepo)
                    .getRegisteredStudentForCourse(anyLong());
            assertEquals(students.size(), this.courseService.getRegisteredStudentForCourse((long)1).size(), "Failed to fetch registered student list.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}