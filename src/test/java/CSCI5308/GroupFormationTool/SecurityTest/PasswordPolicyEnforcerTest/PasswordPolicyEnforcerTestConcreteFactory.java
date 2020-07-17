package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicy;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicyService;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.PasswordPolicyServiceAbstractFactory;

import java.util.List;

public class PasswordPolicyEnforcerTestConcreteFactory extends PasswordPolicyEnforcerTestAbstractFactory {
    @Override
    public IPasswordPolicyService makeService() {
        return null;
    }

    @Override
    public List<IPasswordPolicy> getPolicy() {
        return PasswordPolicyServiceAbstractFactory.instance().getPolicy();

    }

    @Override
    public IPasswordPolicyPersistence makePersistence() {
        return new PasswordPolicyPersistenceMock();
    }
}
