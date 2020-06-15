package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.SystemConfig;

public class MinLowerCasePolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_MIN_LOWERCASE";

    String policyCount = "";

    @Override
    public boolean validate(String password) {
        policyCount = SystemConfig.instance().getAdminConfigService().getAllConfig().get(POLICY_NAME);
        int lowerCase = 0;
        for (int i = 0; i < password.length(); i++) {
            // Check for lowercase letters.
            if (Character.isLowerCase(password.charAt(i)))
                lowerCase++;
        }
        return Integer.parseInt(policyCount) <= lowerCase;
    }

    @Override
    public String toString() {
        return "Password should contain " + policyCount + " lowercase character.";
    }
}
