package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyPersistence implements IPasswordPolicyPersistence {

    private Logger log = LoggerFactory.getLogger(PasswordPolicyPersistence.class);

    @Override
    public List<String> getPasswordHistoryByUserId(String userId, int policyCount) {
        log.trace("Fetching password history for user with ID: {} with policy count: {}", userId, policyCount);
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
            log.error("Error while fetching password history for user with ID: {}, error: {}", userId, e.getMessage());
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return oldPasswords;
    }
}
