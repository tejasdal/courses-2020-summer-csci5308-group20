package org.dal.cs5308.t20.Project.index;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class IndexController {
    @GetMapping({"/", "/index"})
    public String index(Model model, Authentication authentication) {
        if (authentication != null) {
            //to show logout button
            model.addAttribute("isLoggedIn", true);
            Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
            if (roles.contains(new SimpleGrantedAuthority("ADMIN")))
                //to show adminpage button
                model.addAttribute("isAdmin", true);
        }
        return "index";
    }
}
