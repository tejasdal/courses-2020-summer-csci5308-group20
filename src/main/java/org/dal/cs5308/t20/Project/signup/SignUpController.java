package org.dal.cs5308.t20.Project.signup;

import org.dal.cs5308.t20.Project.Factory;
import org.dal.cs5308.t20.Project.user.DuplicateUserException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signupmodel", new SignUpModel());
		model.addAttribute("duplicateUser", false);
		return "signup";
	}

	@PostMapping("/signup")
	public String signupRequest(@ModelAttribute SignUpModel signUpModel, Model model) {
		try {
			Factory.getUserService().addUser(signUpModel.getFirstName(), signUpModel.getLastName(),
					signUpModel.getEmailId(), signUpModel.getBannerId(), signUpModel.getPassword());
		} catch (DuplicateUserException e) {
			e.printStackTrace();
			model.addAttribute("signupmodel", new SignUpModel());
			model.addAttribute("duplicateUser", true);
			return "signup";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
		}
		return "login";
	}
}
