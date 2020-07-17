package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicy;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicyService;

import java.util.List;

public abstract class PasswordPolicyEnforcerTestAbstractFactory {
    private static PasswordPolicyEnforcerTestAbstractFactory uniqueInstance = null;

    protected PasswordPolicyEnforcerTestAbstractFactory() {
    }

    public static PasswordPolicyEnforcerTestAbstractFactory instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PasswordPolicyEnforcerTestConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract IPasswordPolicyService makeService();

    public abstract List<IPasswordPolicy> getPolicy();

    public abstract IPasswordPolicyPersistence makePersistence();

}