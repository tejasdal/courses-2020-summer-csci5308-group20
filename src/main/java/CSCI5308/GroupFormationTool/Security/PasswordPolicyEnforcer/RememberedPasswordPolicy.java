package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class RememberedPasswordPolicy implements IPasswordPolicy {

    private Logger log = LoggerFactory.getLogger(RememberedPasswordPolicy.class);
    public static final String POLICY_NAME = "PASSWORD_REMEMBERED";
    private int policyCount;

    public RememberedPasswordPolicy(String value) {
        if (value.equals("") == false) {
            policyCount = Integer.parseInt(value);
        }
    }

    public int getPolicyCount() {
        return policyCount;
    }

    public void setPolicyCount(int policyCount) {
        this.policyCount = policyCount;
    }

    @Override
    public boolean validate(String password) {
        try {
            IPasswordPolicyPersistence persistence = PasswordPolicyPersistenceAbstractFactory.instance().makePersistence();
            String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            if (username.equals("") == false) {
                List<String> list = persistence.getPasswordHistoryByUserId(username, policyCount);
                if (list.contains(password) == false) {
                    return true;
                }
            }
        } catch (Exception e) {
            log.error("Error while validating RememberedPasswordPolicy, error: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public String toString() {
        return "New password should not be one of the previous " + policyCount + " passwords.";
    }
}
