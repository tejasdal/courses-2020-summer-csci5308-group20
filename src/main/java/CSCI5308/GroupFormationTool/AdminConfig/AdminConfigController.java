package CSCI5308.GroupFormationTool.AdminConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.SystemConfig;

@Controller
public class AdminConfigController {
	private static final String KEY = "config_key";
	private static final String VALUE = "config_value";

	@GetMapping("/admin/config")
	public String displayAdminConfig(Model model) {
		IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		model.addAttribute("config", adminConfigService.getAllConfig());
		return "adminconfig";
	}

	@RequestMapping(value = "/admin/config/add", method = RequestMethod.POST)
	public ModelAndView addAdminConfig(@RequestParam(name = KEY) String key, @RequestParam(name = VALUE) String value) {
		ModelAndView mav = new ModelAndView("redirect:/admin/config");
		if (key.isEmpty()) {
			mav.addObject("errorMessage", "Key is empty");
			return mav;
		}
		if (key.length() > 100) {
			mav.addObject("errorMessage", "Key is too long");
			return mav;
		}
		if (value.isEmpty()) {
			mav.addObject("errorMessage", "Value is empty");
			return mav;
		}
		if (value.length() > 100) {
			mav.addObject("errorMessage", "Value is too long");
			return mav;
		}
		IAdminConfigPersistence persistence = SystemConfig.instance().getAdminConfigPersistence();
		IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		boolean result = false;
		try {
			result = adminConfigService.addConfig(key, value, persistence);
		} catch (Exception e) {
			mav.addObject("errorMessage", e.getMessage());
		}
		mav.addObject("result", result);
		return mav;
	}

	@RequestMapping(value = "/admin/config/update", method = RequestMethod.POST)
	public ModelAndView updateAdminConfig(@RequestParam(name = KEY) String key,
			@RequestParam(name = VALUE) String value) {
		ModelAndView mav = new ModelAndView("redirect:/admin/config");
		if (key.isEmpty()) {
			mav.addObject("errorMessage", "Key is empty");
			return mav;
		}
		if (key.length() > 100) {
			mav.addObject("errorMessage", "Key is too long");
			return mav;
		}
		if (value.isEmpty()) {
			mav.addObject("errorMessage", "Value is empty");
			return mav;
		}
		if (value.length() > 100) {
			mav.addObject("errorMessage", "Value is too long");
			return mav;
		}
		IAdminConfigPersistence persistence = SystemConfig.instance().getAdminConfigPersistence();
		IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		boolean result = false;
		try {
			result = adminConfigService.updateConfig(key, value, persistence);
		} catch (Exception e) {
			mav.addObject("errorMessage", e.getMessage());
		}
		mav.addObject("result", result);
		return mav;
	}

	@RequestMapping(value = "/admin/config/delete", method = RequestMethod.POST)
	public ModelAndView deleteAdminConfig(@RequestParam(name = KEY) String key) {
		ModelAndView mav = new ModelAndView("redirect:/admin/config");
		if (key.isEmpty()) {
			mav.addObject("errorMessage", "Key is empty");
			return mav;
		}
		if (key.length() > 100) {
			mav.addObject("errorMessage", "Key is too long");
			return mav;
		}
		IAdminConfigPersistence persistence = SystemConfig.instance().getAdminConfigPersistence();
		IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		boolean result = false;
		try {
			result = adminConfigService.deleteConfig(key, persistence);
		} catch (Exception e) {
			mav.addObject("errorMessage", e.getMessage());
		}
		mav.addObject("result", result);
		return mav;
	}

}
