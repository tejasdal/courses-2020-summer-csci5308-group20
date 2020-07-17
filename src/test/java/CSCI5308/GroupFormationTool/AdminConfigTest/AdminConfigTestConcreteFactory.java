package CSCI5308.GroupFormationTool.AdminConfigTest;

import CSCI5308.GroupFormationTool.AdminConfig.AdminConfigService;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigPersistence;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigService;

public class AdminConfigTestConcreteFactory implements AdminConfigTestAbstractFactory {
    @Override
    public IAdminConfigPersistence makeAdminConfigPersistence() {
        return new AdminConfigPersistenceMock();
    }

    @Override
    public IAdminConfigService makeAdminConfigService() {
        return new AdminConfigService();
    }
}
