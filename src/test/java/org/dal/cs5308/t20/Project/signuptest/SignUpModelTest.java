package org.dal.cs5308.t20.Project.signuptest;

import org.dal.cs5308.t20.Project.signup.SignUpModel;
import org.dal.cs5308.t20.Project.user.IUserService;
import org.dal.cs5308.t20.Project.user.User;
import org.dal.cs5308.t20.Project.user.UserServiceMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpModelTest {

    @Test
    public void createUserTest() throws Exception {
        IUserService userService = new UserServiceMock();
        User user = new User(0l, "B00ADMIN", "Admin", "Admin", "sanjay.m@dal.ca");
        User assertUser = user.addUser(userService, "Test");
        assertEquals(user.getBannerId(), assertUser.getBannerId());
        assertEquals(user.getFirstName(), assertUser.getFirstName());
        assertEquals(user.getLastName(), assertUser.getLastName());
        assertEquals(user.getEmailId(), assertUser.getEmailId());
    }

    @Test
    public void bannerIdTest() {
        SignUpModel signUpModel = new SignUpModel();
        signUpModel.setBannerId("B0000");
        assertEquals("B0000", signUpModel.getBannerId());
    }

    @Test
    public void firstNameTest() {
        SignUpModel signUpModel = new SignUpModel();
        signUpModel.setFirstName("test");
        assertEquals("test", signUpModel.getFirstName());
    }

    @Test
    public void lastNameTest() {
        SignUpModel signUpModel = new SignUpModel();
        signUpModel.setLastName("test");
        assertEquals("test", signUpModel.getLastName());
    }

    @Test
    public void emailIdTest() {
        SignUpModel signUpModel = new SignUpModel();
        signUpModel.setEmailId("test");
        assertEquals("test", signUpModel.getEmailId());
    }

    @Test
    public void passwordTest() {
        SignUpModel signUpModel = new SignUpModel();
        signUpModel.setPassword("test");
        assertEquals("test", signUpModel.getPassword());
    }

}
