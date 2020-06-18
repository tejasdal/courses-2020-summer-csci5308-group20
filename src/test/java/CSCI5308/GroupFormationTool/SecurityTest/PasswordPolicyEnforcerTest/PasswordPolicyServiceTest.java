package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigService;
import CSCI5308.GroupFormationTool.AdminConfigTest.AdminConfigPersistenceMock;
import CSCI5308.GroupFormationTool.CustomExceptions.DuplicateKeyException;
import CSCI5308.GroupFormationTool.CustomExceptions.KeyNotFoundException;
import CSCI5308.GroupFormationTool.CustomExceptions.PasswordPolicyVoidException;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicy;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicyService;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordPolicyServiceTest {
    String minPasswordPolicy = "PASSWORD_MIN_LENGTH";
    String minPasswordPolicyCount = "2";

    @Test
    private void getListOfPasswordPoliciesTest() throws DuplicateKeyException, KeyNotFoundException {
        IAdminConfigService configService = SystemConfig.instance().getAdminConfigService();
        configService.addConfig(minPasswordPolicy, minPasswordPolicyCount, new AdminConfigPersistenceMock());

        IPasswordPolicyService passwordPolicyService = SystemConfig.instance().getPasswordPolicyService();
        List<IPasswordPolicy> policies = passwordPolicyService.getListOfPasswordPolicies();

        assertTrue(policies.contains(SystemConfig.instance().getPolicy(minPasswordPolicy, minPasswordPolicyCount)));
        configService.deleteConfig(minPasswordPolicy, new AdminConfigPersistenceMock());
        assertFalse(policies.contains(SystemConfig.instance().getPolicy(minPasswordPolicy, minPasswordPolicyCount)));
    }

    @Test
    public void validateUsingPolicies() throws DuplicateKeyException, PasswordPolicyVoidException {
        IAdminConfigService configService = SystemConfig.instance().getAdminConfigService();
        configService.addConfig(minPasswordPolicy, minPasswordPolicyCount, new AdminConfigPersistenceMock());

        IPasswordPolicyService passwordPolicyService = SystemConfig.instance().getPasswordPolicyService();

        assertTrue(passwordPolicyService.validateUsingPolicies("TestPassword"));
    }
}