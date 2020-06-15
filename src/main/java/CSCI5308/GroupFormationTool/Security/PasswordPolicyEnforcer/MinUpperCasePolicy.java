package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.SystemConfig;

public class MinUpperCasePolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_MIN_UPPERCASE";
    String policyCount = "";

    @Override
    public boolean validate(String password) {
        policyCount = SystemConfig.instance().getAdminConfigService().getAllConfig().get(POLICY_NAME);
        int upperCase = 0;
        for (int i = 0; i < password.length(); i++) {
            // Check for lowercase letters.
            if (Character.isUpperCase(password.charAt(i)))
                upperCase++;
        }
        return Integer.parseInt(policyCount) <= upperCase;
    }

    @Override
    public String toString() {
        return "Password should contain at least " + policyCount + " uppercase character.";
    }
}
