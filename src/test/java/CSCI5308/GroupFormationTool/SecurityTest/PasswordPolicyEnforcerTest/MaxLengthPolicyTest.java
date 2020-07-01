package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicy;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.MaxLengthPolicy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MaxLengthPolicyTest {
    String password = "Test123.";

    @Test
    public void MexLengthPolicyTest() {
        assertEquals(1, new MaxLengthPolicy("1").getMaxLength());
    }

    @Test
    public void validate() {
        IPasswordPolicy maxLengthPolicy = new MaxLengthPolicy("10");
        assertTrue(maxLengthPolicy.validate(password));

        maxLengthPolicy = new MaxLengthPolicy("1");
        assertFalse(maxLengthPolicy.validate(password));
    }
}
