package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

public abstract class PasswordPolicyPersistenceAbstractFactory {

    private static PasswordPolicyPersistenceAbstractFactory uniqueInstance = null;

    protected PasswordPolicyPersistenceAbstractFactory(){}

    public static PasswordPolicyPersistenceAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new PasswordPolicyPersistenceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract IPasswordPolicyPersistence makePersistence();
}
