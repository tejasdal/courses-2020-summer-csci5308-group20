package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

public abstract class PasswordPolicyServiceAbstractFactory {

    private static PasswordPolicyServiceAbstractFactory uniqueInstance = null;

    protected PasswordPolicyServiceAbstractFactory(){}

    public static PasswordPolicyServiceAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new PasswordPolicyServiceConcreteFactory();
        }
        return uniqueInstance;
    }

    //Create abstract Methods & implement in concrete class, remove the comment once done
}
