package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class RememberedPasswordPolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_REMEMBERED";
    private int policyCount;

    public RememberedPasswordPolicy(String value) {
        if (!value.equals("")) {
            policyCount = Integer.parseInt(value);
        }
    }

    public void setPolicyCount(int policyCount) {
        this.policyCount = policyCount;
    }

    public int getPolicyCount() {
        return policyCount;
    }

    @Override
    public boolean validate(String password) {
        try {
            IPasswordPersistence persistence = SystemConfig.instance().getPasswordPersistence();
            String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            if (!username.equals("")) {
                List<String> list = persistence.getPasswordHistoryByUserId(username, policyCount);
                if (list.contains(password))
                    return false;
            }
        } catch (Exception e) {
            //
        }
        return true;
    }

    @Override
    public String toString() {
        return "New password should not be one of the previous " + policyCount + " passwords.";
    }
}
