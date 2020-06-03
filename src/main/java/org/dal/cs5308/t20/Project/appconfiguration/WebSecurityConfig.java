package org.dal.cs5308.t20.Project.appconfiguration;

import org.dal.cs5308.t20.Project.dd.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserDetailsService userDetailService;

    @Autowired
    private CustomAuthenticator customAuthenticator;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticator);
//        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/course/{id}/students/register/upload-csv", "/course/{id}/students")
                    .access("@courseService.isInstructorForCourse(#id) or @courseService.isInstructorForCourse(#id)")
                .antMatchers("/course/{id}/user/search", "/course/{id}/ta", "/course/{id}/ta/{userId}/assign")
                    .access("@courseService.isInstructorForCourse(#id)")
                .antMatchers("/admin", "/courseform", "/addcourse", "/delcourse", "/instructF", "/addinstructor")
                    .hasAuthority(Role.ROLE_ADMIN)
                .antMatchers("/","/login", "/forgotpass", "/signup")
                    .permitAll()
                .anyRequest()
                    .authenticated()
//                .and()
//                    .formLogin();
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessdenied");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
