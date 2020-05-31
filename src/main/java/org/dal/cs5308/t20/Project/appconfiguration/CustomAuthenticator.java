package org.dal.cs5308.t20.Project.appconfiguration;

import org.dal.cs5308.t20.Project.user.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticator implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {
            String username = authentication.getPrincipal().toString();
            String password = authentication.getCredentials().toString();
            UserService userService = new UserService();
            if (userService.verifyUser(username, password)) {
                //fetch roles here and add to the list
                ArrayList<GrantedAuthority> roles = new ArrayList<>();
                roles.add(new SimpleGrantedAuthority("USER"));
                //roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                return new UsernamePasswordAuthenticationToken(username, password, roles);
            } else {
                throw new BadCredentialsException("1000");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
