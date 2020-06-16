package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.AdminConfigTest.AdminConfigPersistenceMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordPolicyServiceTest {
    String minPasswordPolicy = "PASSWORD_MIN_LENGTH";

    @Test
    private void getListOfPasswordPoliciesTest() {
        String minPasswordPolicy = "PASSWORD_MIN_LENGTH";
        AdminConfigPersistenceMock mock = new AdminConfigPersistenceMock();
        mock.addConfig(minPasswordPolicy, "2");

        List<String> policies = new ArrayList<>();
        for (Map.Entry<String, String> entry : mock.getAllConfig().entrySet()) {
            if (entry.getKey().startsWith("PASSWORD_")) {
                policies.add(entry.getKey());
            }
        }
        List<String> test = new ArrayList<>();
        test.add("2");
        assertEquals(test, policies);
    }

    @Test
    public void validateUsingPolicies() {
        List<String> enforcingPolicies = new ArrayList<>();
        enforcingPolicies.add(minPasswordPolicy);
        for (String policy : enforcingPolicies) {
            assertEquals(policy, minPasswordPolicy);
        }
    }
}