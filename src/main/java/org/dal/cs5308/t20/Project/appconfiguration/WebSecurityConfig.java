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
    protected void configure(AuthenticationManagerBuilder auth) {
        CustomAuthenticator customAuthenticator = new CustomAuthenticator();
        auth.authenticationProvider(customAuthenticator);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //roles for TA and Instructor
                .antMatchers("/course/{id}/students/register/upload-csv", "/course/{id}/ta", "/course/{id}/students")
                .access("@courseService.isTAForCourse(#id) or @courseService.isInstructorForCourse(#id)")
                //roles for Instructor
                .antMatchers("/course/{id}/user/search", "/course/{id}/ta/{userId}/assign")
                .access("@courseService.isInstructorForCourse(#id)")
                //roles for Admin
                .antMatchers("/admin", "/courseform", "/addcourse", "/delcourse", "/instructF", "/addinstructor")
                .hasAuthority(Role.ROLE_ADMIN)
                //roles for All
                .antMatchers("/", "/index", "/forgotpass", "/signup")
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
