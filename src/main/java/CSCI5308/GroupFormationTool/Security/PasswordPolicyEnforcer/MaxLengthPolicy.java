package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.SystemConfig;

public class MaxLengthPolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_MAX_LENGTH";

    String policyCount = "";

    @Override
    public boolean validate(String password) {
        policyCount = SystemConfig.instance().getAdminConfigService().getAllConfig().get(POLICY_NAME);
        return Integer.parseInt(policyCount) >= password.length();
    }

    @Override
    public String toString() {
        return "Password should be less than " + policyCount + " character.";
    }
}
