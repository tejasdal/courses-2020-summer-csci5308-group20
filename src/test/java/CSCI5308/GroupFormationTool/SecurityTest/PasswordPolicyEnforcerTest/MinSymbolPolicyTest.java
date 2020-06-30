package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicy;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.MinSymbolPolicy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinSymbolPolicyTest {
    String password = "Test123.";

    @Test
    public void MinSymbolPolicyConstructorTest() {
        assertEquals(1, new MinSymbolPolicy("1").getMinSymbol());
    }

    @Test
    public void validate() {
        IPasswordPolicy minSymbolPolicy = new MinSymbolPolicy("1");
        assertTrue(minSymbolPolicy.validate(password));

        minSymbolPolicy = new MinSymbolPolicy("10");
        assertFalse(minSymbolPolicy.validate(password));
    }
}
