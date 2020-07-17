package CSCI5308.GroupFormationTool.AdminConfig;

public abstract class AdminConfigServiceAbstractFactory {

    private static AdminConfigServiceAbstractFactory uniqueInstance = null;

    protected AdminConfigServiceAbstractFactory(){}

    public static AdminConfigServiceAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new AdminConfigServiceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract IAdminConfigService makeAdminConfigService();
}
