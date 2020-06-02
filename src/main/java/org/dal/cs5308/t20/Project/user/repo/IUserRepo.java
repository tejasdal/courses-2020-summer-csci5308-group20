package org.dal.cs5308.t20.Project.user.repo;

import org.dal.cs5308.t20.Project.user.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepo {

    List<User> searchUser(String bannerId, String emailId) throws SQLException;
}
