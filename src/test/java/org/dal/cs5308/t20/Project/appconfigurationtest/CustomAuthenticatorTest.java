package org.dal.cs5308.t20.Project.appconfigurationtest;

import org.dal.cs5308.t20.Project.user.UserServiceMock;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomAuthenticatorTest {
    String email = "sanjay.m@dal.ca";
    String password = "AdvSdc@5308";

    @Test
    public void authenticationTest() throws Exception {
        UserServiceMock userService = new UserServiceMock();
        if (userService.verifyUser(email, password)) {
            ArrayList<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
            UsernamePasswordAuthenticationTokenMock token = new UsernamePasswordAuthenticationTokenMock(email, password, roles);
            assertEquals(new UsernamePasswordAuthenticationToken(email, password, roles), token.userToken);
        }
    }
}
