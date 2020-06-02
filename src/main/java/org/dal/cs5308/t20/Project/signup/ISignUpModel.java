package org.dal.cs5308.t20.Project.signup;

import org.dal.cs5308.t20.Project.user.User;

public interface ISignUpModel {
    String bannerId = null;
    String firstName = null;
    String lastName = null;
    String emailId = null;
    String password = null;

    User createUser();

    String getPassword();

    void setPassword(String password);

}
