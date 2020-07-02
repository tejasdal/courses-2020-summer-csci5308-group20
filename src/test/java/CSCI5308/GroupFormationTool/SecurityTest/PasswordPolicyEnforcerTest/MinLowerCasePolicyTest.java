package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicy;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.MinLowerCasePolicy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinLowerCasePolicyTest {
    String password = "Test123.";

    @Test
    public void MinLowerCaseConstructorPolicyTest() {
        assertEquals(1, new MinLowerCasePolicy("1").getMinLowerCase());
    }

    @Test
    public void validate() {
        IPasswordPolicy minLowerCasePolicy = new MinLowerCasePolicy("1");
        assertTrue(minLowerCasePolicy.validate(password));

        minLowerCasePolicy = new MinLowerCasePolicy("10");
        assertFalse(minLowerCasePolicy.validate(password));
    }
}
