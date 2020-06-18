package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.RestrictedSymbolCasePolicy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestrictedSymbolPolicyTest {
    String password = "Test123.";

    @Test
    public void RestrictedSymbolPolicyConstructorTest() {
        assertEquals("#", new RestrictedSymbolCasePolicy("#").getRestrictedSymbols());
    }

    @Test
    public void validate() {
        RestrictedSymbolCasePolicy restrictedSymbolCasePolicy = new RestrictedSymbolCasePolicy("#");
        assertTrue(restrictedSymbolCasePolicy.validate(password));

        restrictedSymbolCasePolicy = new RestrictedSymbolCasePolicy(".");
        assertFalse(restrictedSymbolCasePolicy.validate(password));
    }
}
