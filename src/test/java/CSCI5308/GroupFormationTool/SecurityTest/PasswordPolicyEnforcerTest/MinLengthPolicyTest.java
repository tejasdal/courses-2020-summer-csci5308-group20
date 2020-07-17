package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicy;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.MinLengthPolicy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinLengthPolicyTest {
    private final String password = "Test123.";

    @Test
    public void MinLengthPolicyConstructorTest() {
        assertEquals(1, new MinLengthPolicy("1").getMinLength());
    }

    @Test
    public void validate() {
        IPasswordPolicy minLengthPolicy = new MinLengthPolicy("1");
        assertTrue(minLengthPolicy.validate(password));

        minLengthPolicy = new MinLengthPolicy("10");
        assertFalse(minLengthPolicy.validate(password));
    }
}
