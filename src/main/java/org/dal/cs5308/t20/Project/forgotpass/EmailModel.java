package org.dal.cs5308.t20.Project.forgotpass;

public class EmailModel implements IEmailModel {
    public String email;

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
