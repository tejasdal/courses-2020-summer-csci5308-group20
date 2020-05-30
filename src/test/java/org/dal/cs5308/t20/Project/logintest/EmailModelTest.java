package org.dal.cs5308.t20.Project.logintest;


import org.dal.cs5308.t20.Project.login.EmailModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailModelTest {

    @Test
    public void setEmailTest() {
        EmailModel emailModel = new EmailModel();
        emailModel.setEmail("test@test.dal.ca");
        assertEquals("test@test.dal.ca", emailModel.email);
    }

    @Test
    public void getEmailTest() {
        EmailModel emailModel = new EmailModel();
        emailModel.setEmail("test@test.dal.ca");
        assertEquals("test@test.dal.ca", emailModel.getEmail());
    }

}
