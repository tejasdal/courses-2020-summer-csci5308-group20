package org.dal.cs5308.t20.Project.appconfiguration;

import org.dal.cs5308.t20.Project.dd.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        CustomAuthenticator customAuthenticator = new CustomAuthenticator();
        auth.authenticationProvider(customAuthenticator);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //for Instructor
                .antMatchers("/course/{id}/students/register/upload-csv", "/course/{id}/students")
                .access("@courseService.isInstructorForCourse(#id) or @courseService.isInstructorForCourse(#id)")
                //for TA
                .antMatchers("/course/{id}/user/search", "/course/{id}/ta", "/course/{id}/ta/{userId}/assign")
                .access("@courseService.isInstructorForCourse(#id)")
                //for Admin
                .antMatchers("/admin", "/courseform", "/addcourse", "/delcourse", "/instructF", "/addinstructor")
                .hasAuthority(Role.ROLE_ADMIN)
                //for All
                .antMatchers("/", "/home", "/forgotpass", "/signup")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessdenied");
    }
}
