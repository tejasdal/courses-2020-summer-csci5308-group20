//package org.dal.cs5308.t20.Project.appconfiguration;
//
//import org.dal.cs5308.t20.Project.CryptoUtil;
//import org.dal.cs5308.t20.Project.Factory;
//import org.dal.cs5308.t20.Project.appconfiguration.bo.AuthUser;
//import org.dal.cs5308.t20.Project.dd.Role;
//import org.dal.cs5308.t20.Project.user.IUserService;
//import org.dal.cs5308.t20.Project.user.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class MyUserDetailService implements UserDetailsService {
//
//    Logger log = LoggerFactory.getLogger(MyUserDetailService.class);
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        try {
//            log.error("Loading user with username: {} from the database.", username);
//            IUserService userService = Factory.getUserService();
//            User user = userService.getUserByEmail(username);
//            List<SimpleGrantedAuthority> roles = new ArrayList<>();
//            if ("B00ADMIN".equalsIgnoreCase(user.getBannerId())){
//                roles.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN));
//            }
//            roles.add(new SimpleGrantedAuthority(Role.ROLE_GUEST));
//
//            String dbPassword = userService.getPasswordByEmailId(username);
//            String password = CryptoUtil.decrypt(dbPassword);
//            return new AuthUser(user, password, roles);
//        } catch (Exception e) {
//            log.error("User not found with username: {} in the database.", username, e);
//            throw new UsernameNotFoundException("User not found for username: "+ username, e);
//        }
//    }
//}
