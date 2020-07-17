package CSCI5308.GroupFormationTool.AccessControl;


public abstract class UserPersistenceAbstractFactory {

    private static UserPersistenceAbstractFactory uniqueInstance = null;

    protected UserPersistenceAbstractFactory(){}

    public static UserPersistenceAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new UserPersistenceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract IUserPersistence makeUserPersistence();
}
