package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import java.util.List;

public abstract class PasswordPolicyServiceAbstractFactory {

    private static PasswordPolicyServiceAbstractFactory uniqueInstance = null;

    protected PasswordPolicyServiceAbstractFactory() {
    }

    public static PasswordPolicyServiceAbstractFactory instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PasswordPolicyServiceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract IPasswordPolicyService makeService();

    public abstract List<IPasswordPolicy> getPolicy();
}
