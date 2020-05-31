package org.dal.cs5308.t20.Project.signup;

import org.dal.cs5308.t20.Project.Factory;
import org.dal.cs5308.t20.Project.user.User;

public class SignUpModel implements ISignUpModel {
    private String bannerId = null;
    private String firstName = null;
    private String lastName = null;
    private String emailId = null;
    private String password = null;

    @Override
    public User createUser() {
        if (!(bannerId == null || firstName == null || lastName == null || emailId == null || password == null)) {
            User user = new User(0L, bannerId, firstName, lastName, emailId);
            try {
                return user.addUser(Factory.getUserService(), password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
