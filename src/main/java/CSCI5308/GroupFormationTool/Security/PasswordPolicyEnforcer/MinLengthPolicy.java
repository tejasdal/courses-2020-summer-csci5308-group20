package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.SystemConfig;

public class MinLengthPolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_MIN_LENGTH";

    String policyCount = "";

    @Override
    public boolean validate(String password) {
        policyCount = SystemConfig.instance().getAdminConfigService().getAllConfig().get(POLICY_NAME);
        return Integer.parseInt(policyCount) <= password.length();
    }

    @Override
    public String toString() {
        return "Password should be greater than " + policyCount + " character.";
    }
}
