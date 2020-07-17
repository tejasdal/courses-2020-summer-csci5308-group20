package CSCI5308.GroupFormationTool.AdminConfig;

public class AdminConfigServiceConcreteFactory extends AdminConfigServiceAbstractFactory {
    @Override
    public IAdminConfigService makeAdminConfigService() {
        return new AdminConfigService();
    }
}
