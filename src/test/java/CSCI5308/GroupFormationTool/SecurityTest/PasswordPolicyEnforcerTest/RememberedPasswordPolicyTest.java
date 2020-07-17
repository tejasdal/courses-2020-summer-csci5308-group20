package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.RememberedPasswordPolicy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RememberedPasswordPolicyTest {
    String password = "Test123.";

    @Test
    public void RememberedPasswordPolicyConstructorTest() {
        assertEquals(1, new RememberedPasswordPolicy("1").getPolicyCount());
    }

    @Test
    public void validate() {
        IPasswordPolicyPersistence mock = PasswordPolicyEnforcerTestAbstractFactory.instance().makePersistence();
        List<String> oldPasswords = mock.getPasswordHistoryByUserId("TEST", 2);
        boolean passwordPresent = false;
        for (String oldPass : oldPasswords) {
            if (password.equals(oldPass)) {
                passwordPresent = true;
                break;
            }
        }
        assertFalse(passwordPresent);

        password = "test";
        passwordPresent = false;
        for (String oldPass : oldPasswords) {
            if (password.equals(oldPass)) {
                passwordPresent = true;
                break;
            }
        }
        assertTrue(passwordPresent);
    }
}
