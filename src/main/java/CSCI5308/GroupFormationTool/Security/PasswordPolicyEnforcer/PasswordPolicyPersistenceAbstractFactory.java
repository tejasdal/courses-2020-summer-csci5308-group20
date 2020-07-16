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

    //Create abstract Methods & implement in concrete class, remove the comment once done
}
