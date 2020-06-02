package org.dal.cs5308.t20.Project.user.repo.impl;

import org.dal.cs5308.t20.Project.Factory;
import org.dal.cs5308.t20.Project.user.User;
import org.dal.cs5308.t20.Project.user.repo.IUserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepo implements IUserRepo {

    Logger log = LoggerFactory.getLogger(UserRepo.class);

    @Override
    public List<User> searchUser(String bannerId, String emailId) throws SQLException {
        log.debug("Searching user with bannerId: {} and emailId: {}", bannerId, emailId);
        final PreparedStatement statement = Factory.getDbUtilInstance().getConnection()
                .prepareStatement(REGISTER_STUDENT_TO_COURSE);
        statement.setString(1,"%" + bannerId +"%" );
        statement.setString(2, "%" + emailId +"%");
        ResultSet resultSet = statement.executeQuery();
        return parseResultSetToUsers(resultSet);
    }

    private List<User> parseResultSetToUsers(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            Long userId = resultSet.getLong(org.dal.cs5308.t20.Project.dd.User.ID);
            String firstName = resultSet.getString(org.dal.cs5308.t20.Project.dd.User.FIRST_NAME);
            String lastName = resultSet.getString(org.dal.cs5308.t20.Project.dd.User.LAST_NAME);
            String emailId = resultSet.getString(org.dal.cs5308.t20.Project.dd.User.EMAIL_ID);
            String bannerId = resultSet.getString(org.dal.cs5308.t20.Project.dd.User.BANNER_ID);
            users.add(new User(userId, bannerId, firstName, lastName, emailId));
        }
        return users;
    }


    private static final String REGISTER_STUDENT_TO_COURSE = "SELECT id, first_name, last_name, email_id, banner_id FROM user " +
            "WHERE banner_Id LIKE ? AND email_Id LIKE ? AND banner_id <> \"B00ADMIN\"";
}
