package CSCI5308.GroupFormationTool.AdminConfigTest;

import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigPersistence;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigService;

public interface AdminConfigTestAbstractFactory {

    public IAdminConfigPersistence makeAdminConfigPersistence();
    public IAdminConfigService makeAdminConfigService();
}
