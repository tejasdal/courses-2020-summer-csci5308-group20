package org.dal.cs5308.t20.Project.signup;

import org.dal.cs5308.t20.Project.user.DuplicateUserException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class SignUpController {

    @GetMapping({"/", "/home"})
    public String index(Model model, Authentication authentication) {
        if (authentication != null) {
            Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
            if (roles.contains(new SimpleGrantedAuthority("ADMIN")))
                model.addAttribute("isAdmin", true);
        }
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupmodel", new SignUpModel());
        model.addAttribute("duplicateUser", false);
        return "signup";
    }

    @PostMapping("/signup")
    public String signupRequest(@ModelAttribute SignUpModel signUpModel, Model model) {
        try {
            if (signUpModel.createUser() == null) {
                throw new DuplicateUserException("Duplicate User");
            }
        } catch (DuplicateUserException e) {
            e.printStackTrace();
            model.addAttribute("signupmodel", new SignUpModel());
            model.addAttribute("duplicateUser", true);
            return "signup";
        }
        return "login";
    }
}
