package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AdminConfig.AdminConfigPersistenceAbstractFactory;
import CSCI5308.GroupFormationTool.AdminConfig.AdminConfigServiceAbstractFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigPersistence;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigService;

@SpringBootApplication
public class GroupFormationToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupFormationToolApplication.class, args);
		final IAdminConfigService adminConfigService = AdminConfigServiceAbstractFactory.instance().makeAdminConfigService();
		final IAdminConfigPersistence adminConfigPersistence = AdminConfigPersistenceAbstractFactory.instance().makeAdminConfigPersistence();
		adminConfigService.loadAllConfig(adminConfigPersistence);
	}

}
