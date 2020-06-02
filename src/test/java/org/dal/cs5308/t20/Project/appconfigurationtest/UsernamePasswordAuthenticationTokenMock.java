package org.dal.cs5308.t20.Project.appconfigurationtest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

public class UsernamePasswordAuthenticationTokenMock {
    UsernamePasswordAuthenticationToken userToken;
    public UsernamePasswordAuthenticationTokenMock(String email, String password, ArrayList<GrantedAuthority> roles) {
        userToken = new UsernamePasswordAuthenticationToken(email, password, roles);
    }
}
