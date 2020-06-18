package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import java.util.List;

public interface IPasswordPersistence {
    List<String> getPasswordHistoryByUserId(String userId, int policyCount);
}
