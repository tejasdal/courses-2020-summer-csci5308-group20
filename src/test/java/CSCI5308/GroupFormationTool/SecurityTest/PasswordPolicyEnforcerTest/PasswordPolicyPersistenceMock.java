package CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyEnforcerTest;

import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.IPasswordPersistence;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyPersistenceMock implements IPasswordPersistence {
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
