package CSCI5308.GroupFormationTool.ErrorHandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController
{
	private Logger log = LoggerFactory.getLogger(ErrorController.class);
	@GetMapping("/error")
	public String error(Model model)
	{
		log.info("Processing request to redirect to error page.");
		return "error";
	}
}