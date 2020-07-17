package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPolicyPersistence;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyPersistenceMock implements IPasswordPolicyPersistence {
    @Override
    public List<String> getPasswordHistoryByUserId(String userId, int policyCount) {
        List<String> mockData = new ArrayList<>();
        if (userId.equals("TEST") && policyCount == 2) {
            mockData.add("test");
            mockData.add("test1");
        }
        return mockData;
    }
}
