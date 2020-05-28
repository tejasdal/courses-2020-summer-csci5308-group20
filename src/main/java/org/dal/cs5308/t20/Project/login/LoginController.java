package org.dal.cs5308.t20.Project.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login123")
    public String login(Model model){
        return "customlogin";
    }

}
