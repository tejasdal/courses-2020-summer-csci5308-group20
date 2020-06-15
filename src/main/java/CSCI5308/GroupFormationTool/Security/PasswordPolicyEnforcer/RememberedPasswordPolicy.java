package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.SystemConfig;

public class RememberedPasswordPolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_REMEMBERED";
    String policyCount = "";

    @Override
    public boolean validate(String password) {
        policyCount = SystemConfig.instance().getAdminConfigService().getAllConfig().get(POLICY_NAME);
        if (null != policyCount) {
            //TO DO
        }
        return true;
    }

    @Override
    public String toString() {
        return "New password should not be one of the previous " + policyCount + " passwords.";
    }
}
