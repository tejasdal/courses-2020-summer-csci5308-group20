package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.AdminConfig.AdminConfigServiceAbstractFactory;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigService;
import CSCI5308.GroupFormationTool.AdminConfigTest.AdminConfigPersistenceMock;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicyService;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.PasswordPolicyServiceAbstractFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordPolicyServiceTest {
    String minPasswordPolicy = "PASSWORD_MIN_LENGTH";
    String minPasswordPolicyCount = "2";

    @Test
    public void validateUsingPolicies() throws Exception {
        IAdminConfigService configService = AdminConfigServiceAbstractFactory.instance().makeAdminConfigService();
        configService.addConfig(minPasswordPolicy, minPasswordPolicyCount, new AdminConfigPersistenceMock());
        IPasswordPolicyService passwordPolicyService = PasswordPolicyServiceAbstractFactory.instance().makeService();
        assertTrue(passwordPolicyService.validateUsingPolicies("TestPassword"));
        try {
            passwordPolicyService.validateUsingPolicies("A");
        } catch (Exception e) {
            assertEquals(Exception.class.getName(), e.getClass().getName());
        }

    }
}