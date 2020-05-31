package org.dal.cs5308.t20.Project.course.repo.impl;

import org.dal.cs5308.t20.Project.Factory;
import org.dal.cs5308.t20.Project.course.bo.Student;
import org.dal.cs5308.t20.Project.course.repo.CourseRepo;
import org.dal.cs5308.t20.Project.dd.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseRepoImpl implements CourseRepo {

    Logger log = LoggerFactory.getLogger(CourseRepoImpl.class);
    @Override
    public void registerStudentIntoCourse(Long courseId, String bannerId) throws SQLException {
        final PreparedStatement statement = Factory.getDbUtilInstance().getConnection()
                .prepareStatement(REGISTER_STUDENT_TO_COURSE);
        statement.setLong(1, courseId);
        statement.setString(2, bannerId);
        ResultSet resultSet = statement.executeQuery();
        log.debug("Registered a student: {} into courseID: {}",bannerId, courseId);
    }

    @Override
    public List<Student> getRegisteredStudentForCourse(Long courseId) throws SQLException {
        log.debug("Fetching registered students list from database for courseID: {}", courseId);
        final PreparedStatement statement = Factory.getDbUtilInstance().getConnection()
                .prepareStatement(GET_REGISTERED_STUDENTS_FOR_COURSE);
        statement.setLong(1, courseId);
        ResultSet resultSet = statement.executeQuery();
        return parseResultSetForStudent(resultSet);
    }

    @Override
    public boolean isStudentAlreadyRegistered(String bannerId, Long courseId) throws Exception {
        log.debug("Checking whether student: {} is already registered in courseID:", bannerId, courseId);
        final PreparedStatement statement = Factory.getDbUtilInstance().getConnection()
                .prepareStatement(IS_STUDENT_ALREADY_REGISTERED);
        statement.setString(1, bannerId);
        statement.setLong(2, courseId);
        ResultSet resultSet = statement.executeQuery();
        if(parseResultSetToId(resultSet).size() >= 1)
            return true;
        return false;
    }

    private List<String> parseResultSetToId(ResultSet resultSet) throws Exception {
        List<String> ids = new ArrayList<>();
        while(resultSet.next()) {
            ids.add(""+resultSet.getLong(1));
        }
        return ids;
    }

    private List<Student> parseResultSetForStudent(ResultSet resultSet) throws SQLException {
        List<Student> students = new ArrayList<>();
        while(resultSet.next()){
            Student student = new Student();
            student.setFirstName(resultSet.getString(User.FIRST_NAME));
            student.setLastName(resultSet.getString(User.LAST_NAME));
            student.setEmail(resultSet.getString(User.EMAIL_ID));
            student.setBannerId(resultSet.getString(User.BANNER_ID));
            students.add(student);
        }
        return students;
    }

    private static final String REGISTER_STUDENT_TO_COURSE = "call sp_register_student_to_course(?, ?)";

    private static final String GET_REGISTERED_STUDENTS_FOR_COURSE = "SELECT u.first_name, u.last_name, u.email_id, u.banner_id FROM user as u, coursetouser as cu WHERE cu.course_id = ? AND u.id = cu.user_id";

    public static final String IS_STUDENT_ALREADY_REGISTERED = "SELECT cu.user_id FROM coursetouser as cu, user as u " +
            "WHERE u.banner_id = ? AND cu.course_id = ? AND cu.user_id = u.id";
}
