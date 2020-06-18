package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.MinUpperCasePolicy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinUpperCasePolicyTest {
    String password = "Test123.";

    @Test
    public void MinUpperCasePolicyConstructorTest() {
        assertEquals(1, new MinUpperCasePolicy("1").getMinUpperCase());
    }

    @Test
    public void validate() {
        MinUpperCasePolicy minUpperCasePolicy = new MinUpperCasePolicy("1");
        assertTrue(minUpperCasePolicy.validate(password));

        minUpperCasePolicy = new MinUpperCasePolicy("10");
        assertFalse(minUpperCasePolicy.validate(password));
    }
}
