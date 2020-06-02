package org.dal.cs5308.t20.Project.forgotpass;

import org.dal.cs5308.t20.Project.Factory;
import org.dal.cs5308.t20.Project.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForgotPassController {

    @GetMapping("/forgotpass")
    public String forgotpass(Model model) {
        model.addAttribute("emailModel", new EmailModel());
        return "forgotpass";
    }

    @PostMapping("/forgotpass")
    public String resetpass(@ModelAttribute EmailModel emailModel, Model model) {
        User user = new User(emailModel.getEmail());
        try {
            user.resetPassword(Factory.getUserService());
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("sentSuccess",true);
        return "forgotpass";
    }
}
