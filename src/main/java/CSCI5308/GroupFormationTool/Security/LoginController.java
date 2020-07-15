package CSCI5308.GroupFormationTool.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController
{
	private Logger log = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public String login(Model model)
	{
		log.info("Processing a request to load a login page.");
		return "login.html";
	}
	
	@GetMapping("/login-error")
	public String loginError(Model model)
	{
		log.info("Processing a request to load a login error page.");
		return "login-error.html";
	}
}