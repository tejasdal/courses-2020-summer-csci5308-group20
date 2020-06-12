package CSCI5308.GroupFormationTool.AdminConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import CSCI5308.GroupFormationTool.SystemConfig;

@Controller
public class AdminConfigController {
	
	@GetMapping("/admin/config")
	public String displayAdminConfig(Model model) {
		IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		model.addAttribute("config", adminConfigService.getAllConfig());
		return "adminconfig";
	}

}
