package CSCI5308.GroupFormationTool.AdminConfig;

public class AdminConfigPersistenceConcreteFactory extends AdminConfigPersistenceAbstractFactory {
    @Override
    public IAdminConfigPersistence makeAdminConfigPersistence() {
        return new AdminConfigPersistence();
    }
}
