package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyPersistence implements IPasswordPolicyPersistence {
    @Override
    public List<String> getPasswordHistoryByUserId(String userId, int policyCount) {
        CallStoredProcedure proc = null;
        List<String> oldPasswords = null;
        try {

            proc = new CallStoredProcedure("spPasswordHistory(?,?)");
            proc.setParameter(1, userId);
            proc.setParameter(2, policyCount);
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                oldPasswords = new ArrayList<>();
                while (results.next()) {
                    oldPasswords.add(results.getString(1));
                }
            }
        } catch (SQLException e) {
            // Logging needed.
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return oldPasswords;
    }
}
