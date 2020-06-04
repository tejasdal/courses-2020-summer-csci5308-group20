package org.dal.cs5308.t20.Project.signup;

public class SignUpModel {
    private String bannerId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;

    public SignUpModel() {
        bannerId = null;
        firstName = null;
        lastName = null;
        emailId = null;
        password = null;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
