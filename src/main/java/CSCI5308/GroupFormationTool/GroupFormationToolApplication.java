package CSCI5308.GroupFormationTool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigPersistence;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigService;

@SpringBootApplication
public class GroupFormationToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupFormationToolApplication.class, args);
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		final IAdminConfigPersistence adminConfigPersistence = SystemConfig.instance().getAdminConfigPersistence();
		adminConfigService.loadAllConfig(adminConfigPersistence);
	}

}
