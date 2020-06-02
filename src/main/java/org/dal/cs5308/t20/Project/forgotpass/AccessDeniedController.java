package org.dal.cs5308.t20.Project.forgotpass;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {
    @GetMapping("/accessdenied")
    public String accessdenied(Model model) {
        return "accessdenied";
    }
}
