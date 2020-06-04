package org.dal.cs5308.t20.Project.course;

import org.dal.cs5308.t20.Project.Factory;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CourseDAO implements ICourseDAO {

    private static final String insert_course = "insert into Course(ID,NAME) values(?,?)";
    private static final String del_course = "delete from Course where ID=?";
    private static final String del_course_mapping = "delete from CourseToUser where COURSE_ID=?";
    private static final String get_all_course = "select * from Course";
    private static final String search_user = "select ID from User where EMAIL_ID=?";
    private static final String insert_instructor = "insert into CourseToUser(COURSE_ID,USER_ID,ROLE_ID) values(?,?,?)";


    @Override
    public int addCourse(Course course) throws SQLException {

        PreparedStatement pstatement = Factory.getDbUtilInstance().getConnection()
                .prepareStatement(insert_course);
        pstatement.setInt(1, course.getId());
        pstatement.setString(2, course.getName());
        int status = pstatement.executeUpdate();
        pstatement.close();
        return status;
    }

    @Override
    public int delCourse(Integer course_id) throws SQLException {

        PreparedStatement pstatement = Factory.getDbUtilInstance().getConnection()
                .prepareStatement(del_course_mapping);
        pstatement.setInt(1, course_id);
        pstatement.executeUpdate();
        pstatement = Factory.getDbUtilInstance().getConnection()
                .prepareStatement(del_course);
        pstatement.setInt(1, course_id);
        int status = pstatement.executeUpdate();
        pstatement.close();
        return status;
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        List<Course> course_list = new ArrayList<Course>();
        final PreparedStatement pstatement = Factory.getDbUtilInstance().getConnection()
                .prepareStatement(get_all_course);
        ResultSet rs = pstatement.executeQuery();
        while (rs.next()) {
            Course course = new Course();
            course.setName(rs.getString("name"));
            course.setId(rs.getInt("id"));
            course_list.add(course);
        }
        pstatement.close();
        rs.close();
        return course_list;
    }

    @Override
    public int addInstructor(String email_id, Integer course_id) throws SQLException {

        PreparedStatement pstatement = Factory.getDbUtilInstance().getConnection()
                .prepareStatement(search_user);
        pstatement.setString(1, email_id);
        ResultSet rs = pstatement.executeQuery();
        Long user_id = null;
        while (rs.next()) {
            user_id = rs.getLong("ID");
        }
        rs.close();
        if (user_id == null)
            return 0;

        pstatement = Factory.getDbUtilInstance().getConnection()
                .prepareStatement(insert_instructor);
        pstatement.setInt(1, course_id);
        pstatement.setLong(2, user_id);
        pstatement.setInt(3, 1);
        int status = pstatement.executeUpdate();
        pstatement.close();
        return status;
    }


}
