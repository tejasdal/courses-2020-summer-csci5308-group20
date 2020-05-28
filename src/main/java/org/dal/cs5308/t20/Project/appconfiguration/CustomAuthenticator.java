package org.dal.cs5308.t20.Project.appconfiguration;

import org.dal.cs5308.t20.Project.user.UserNotFoundException;
import org.dal.cs5308.t20.Project.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
                roles.add(new SimpleGrantedAuthority("ROLE_USER"));
                //roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                return new UsernamePasswordAuthenticationToken(username, password, roles);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        authentication.setAuthenticated(false);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
