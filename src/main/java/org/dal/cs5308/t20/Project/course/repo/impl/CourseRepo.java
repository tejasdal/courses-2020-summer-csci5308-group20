package org.dal.cs5308.t20.Project.course.repo.impl;

import org.dal.cs5308.t20.Project.Factory;
import org.dal.cs5308.t20.Project.course.exception.CourseException;
import org.dal.cs5308.t20.Project.course.repo.ICourseRepo;
import org.dal.cs5308.t20.Project.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseRepo implements ICourseRepo {

    Logger log = LoggerFactory.getLogger(CourseRepo.class);

    @Override
    public void registerStudentIntoCourse(Long courseId, String bannerId) throws SQLException {
        final PreparedStatement statement = Factory.getDbUtilInstance().getConnection()
                .prepareStatement(REGISTER_STUDENT_TO_COURSE);
        statement.setLong(1, courseId);
        statement.setString(2, bannerId);
        ResultSet resultSet = statement.executeQuery();
        log.debug("Registered a student: {} into courseID: {}", bannerId, courseId);
    }

    @Override
    public List<User> getRegisteredStudentForCourse(Long courseId) throws SQLException {
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
        if (parseResultSetToId(resultSet).size() >= 1)
            return true;
        return false;
    }

    @Override
    public List<User> getCourseTAs(Long courseId) throws SQLException {
        final PreparedStatement statement = Factory.getDbUtilInstance().getConnection()
                .prepareStatement(GET_TA_FOR_COURSE);
        statement.setLong(1, courseId);
        ResultSet resultSet = statement.executeQuery();
        return parseResultSetForStudent(resultSet);
    }

    @Override
    public void assignCourseTA(Long courseId, Long userId) throws SQLException {
        final PreparedStatement statement = Factory.getDbUtilInstance().getConnection()
                .prepareStatement(ASSIGN_TA_FOR_COURSE);
        statement.setLong(1, courseId);
        statement.setLong(2, userId);
        ResultSet resultSet = statement.executeQuery();
        log.debug("Registered a user: {} as TA into courseID: {}", userId, courseId);
    }

    @Override
    public boolean isValidCourse(Long courseId) throws CourseException {
        log.debug("Checking whether course with ID: {} does exist or not.:", courseId);
        try {
            final PreparedStatement statement;
            statement = Factory.getDbUtilInstance().getConnection()
                    .prepareStatement(IS_VALID_COURSE);
            statement.setLong(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            if (parseResultSetToId(resultSet).size() == 1)
                return true;
            return false;
        } catch (Exception e) {
            throw new CourseException("Course with ID: "+ courseId+ " does not exists.");
        }
    }

    private List<String> parseResultSetToId(ResultSet resultSet) throws Exception {
        List<String> ids = new ArrayList<>();
        while (resultSet.next()) {
            ids.add("" + resultSet.getLong(1));
        }
        return ids;
    }

    private List<User> parseResultSetForStudent(ResultSet resultSet) throws SQLException {
        List<User> students = new ArrayList<>();
        while (resultSet.next()) {
            User student = new User(0L,
            resultSet.getString(org.dal.cs5308.t20.Project.dd.User.FIRST_NAME),
            resultSet.getString(org.dal.cs5308.t20.Project.dd.User.LAST_NAME),
            resultSet.getString(org.dal.cs5308.t20.Project.dd.User.EMAIL_ID),
            resultSet.getString(org.dal.cs5308.t20.Project.dd.User.BANNER_ID));
            students.add(student);
        }
        return students;
    }

    private static final String REGISTER_STUDENT_TO_COURSE = "call sp_register_student_to_course(?, ?)";

    private static final String GET_REGISTERED_STUDENTS_FOR_COURSE = "SELECT u.first_name, u.last_name, u.email_id, u.banner_id FROM user as u, coursetouser as cu " +
            "WHERE cu.course_id = ? AND u.id = cu.user_id";

    public static final String IS_STUDENT_ALREADY_REGISTERED = "SELECT cu.user_id FROM coursetouser as cu, user as u " +
            "WHERE u.banner_id = ? AND cu.course_id = ? AND cu.user_id = u.id";

    public static final String GET_TA_FOR_COURSE = "SELECT u.first_name, u.last_name, u.email_id, u.banner_id FROM user as u, coursetouser uc " +
            "WHERE uc.course_id = ? AND u.id = uc.user_id";

    private static final String ASSIGN_TA_FOR_COURSE = "call sp_assign_ta(?, ?)";

    private static final String IS_VALID_COURSE = "SELECT id FROM course WHERE id = ?";
}
