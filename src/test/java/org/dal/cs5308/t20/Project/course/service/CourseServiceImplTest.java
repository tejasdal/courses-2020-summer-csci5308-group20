package org.dal.cs5308.t20.Project.course.service;

import org.dal.cs5308.t20.Project.course.bo.Student;
import org.dal.cs5308.t20.Project.course.exception.CourseException;
import org.dal.cs5308.t20.Project.course.repo.impl.CourseRepo;
import org.dal.cs5308.t20.Project.course.service.impl.CourseService;
import org.dal.cs5308.t20.Project.user.User;
import org.dal.cs5308.t20.Project.user.UserNotFoundException;
import org.dal.cs5308.t20.Project.user.UserService;
import org.dal.cs5308.t20.Project.user.repo.impl.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {

    @InjectMocks
    CourseService courseService;

    @Mock
    CourseRepo courseRepo;

    @Mock
    UserRepo userRepo;

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
    void registerUploadedStudentTest() throws SQLException, CourseException {
        String file = "B00846296,Tejas,Patel,tejas.patel@dal.ca\n" +
                "B00846297,Roshan,Patel,roshan.patel@dal.ca\n" +
                "B00846201,Karan,Kharecha,karank@dal.ca\n";
        String fileName = "test.csv";
        List<User> students = new ArrayList<>();
        doAnswer(ans -> {
            User student = new User(0L,(String)ans.getArguments()[1], null, null, null);
            students.add(student);
            return null;
        })
                .when(courseRepo).registerStudentIntoCourse(anyLong(),anyString());

        doReturn(true).when(courseRepo).isValidCourse(anyLong());
        doReturn(false).when(userService).isUserExistByEmailId(anyString());
        MultipartFile multipartFile = new MockMultipartFile(fileName, file.getBytes());
        this.courseService.registerUploadedStudent(1L, multipartFile, fileName);
        assertEquals(3, students.size(), "Failed to register new students.");

    }

    @Test
    void getRegisteredStudentForCourseTest() {
        try {
            List<Student> students = Arrays.asList(new Student("B00846296","Tejas", "Patel", "tejas.patel@dal.ca"),
                    new Student("B00846297","Rob", "H", "robh@dal.ca"),
                    new Student("B00846298","Krutarth", "Patel", "krutarth.patel@dal.ca"));
            doReturn(students).when(courseRepo)
                    .getRegisteredStudentForCourse(anyLong());
            assertEquals(students.size(), this.courseService.getRegisteredStudentForCourse((long)1).size(), "Failed to fetch registered student list.");
        } catch (SQLException | CourseException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void isValidCourseTest() throws CourseException {
        doReturn(true).when(courseRepo).isValidCourse(1L);
        assertTrue(this.courseService.isValidCourse(1L), "Failed to validate the course.");
    }

    @Test
    void assignCourseTATest() throws SQLException, CourseException, UserNotFoundException {

        List<User> users = new ArrayList<>();
        doAnswer(ans -> {
            User user = new User((Long)ans.getArguments()[1], null, null, null, null);
            users.add(user);
            return null;
        }).when(courseRepo).assignCourseTA(anyLong(),anyLong());

        doReturn(true).when(courseRepo).isValidCourse(anyLong());
        doReturn(null).when(userService).getUserById(anyLong());
        //3 Users with IDs (3, 4, 5) are assign as TA to course with ID: 1.
        this.courseService.assignCourseTA(1L, 3L);
        this.courseService.assignCourseTA(1L, 4L);
        this.courseService.assignCourseTA(1L, 5L);
        assertEquals(3, users.size(), "Failed to assign TA.");
    }

    @Test
    void getCourseTAsTest(){
        try {
            List<User> users = Arrays.asList(new User(1L,"B00846296","Tejas", "Patel", "tejas.patel@dal.ca"),
                    new User(2L,"B00846297","Rob", "H", "robh@dal.ca"),
                    new User(3L,"B00846298","Krutarth", "Patel", "krutarth.patel@dal.ca"));
            doReturn(true).when(courseRepo).isValidCourse(anyLong());
            doReturn(users).when(courseRepo)
                    .getCourseTAs(anyLong());
            assertEquals(users.size(), this.courseService.getCourseTAs((long)1).size(), "Failed to fetch registered student list.");
        } catch (SQLException | CourseException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void searchUserTest() throws SQLException {

        //Mock UserRepo searchUser method and handle two inputs.
        doAnswer(ans -> {
            List<User> users = new ArrayList<>();
            if ("B00846296".equals(ans.getArguments()[0]) &&
                    "".equals(ans.getArguments()[1])){
                User user = new User(1L, null, null, null, null);
                users.add(user);
            }
            if ("".equals(ans.getArguments()[0]) &&
                    "tejas.patel@dal.ca".equals(ans.getArguments()[1])){
                User user = new User(2L, null, null, null, null);
                users.add(user);
            }
            return users;
        }).when(userRepo).searchUser(anyString(),anyString());

        assertEquals(1, this.courseService.searchUser("B00846296","").size());
        assertEquals(1, this.courseService.searchUser("","tejas.patel@dal.ca").size());
    }
}