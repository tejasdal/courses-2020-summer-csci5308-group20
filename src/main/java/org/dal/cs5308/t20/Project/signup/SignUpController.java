package org.dal.cs5308.t20.Project.signup;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    @GetMapping("/signup")
    public String forgotpass(Model model) {
        model.addAttribute("signupmodel", new SignUpModel());
        return "signup";
    }

    @PostMapping("/signup")
    public String resetpass(@ModelAttribute SignUpModel signUpModel, Model model) {
        try {
            if (signUpModel.createUser() == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "signup";
    }
}
