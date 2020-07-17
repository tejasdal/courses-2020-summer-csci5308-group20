package CSCI5308.GroupFormationTool.AdminConfig;

public abstract class AdminConfigPersistenceAbstractFactory {

    private static AdminConfigPersistenceAbstractFactory uniqueInstance = null;

    protected AdminConfigPersistenceAbstractFactory(){}

    public static AdminConfigPersistenceAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new AdminConfigPersistenceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract IAdminConfigPersistence makeAdminConfigPersistence();

}
