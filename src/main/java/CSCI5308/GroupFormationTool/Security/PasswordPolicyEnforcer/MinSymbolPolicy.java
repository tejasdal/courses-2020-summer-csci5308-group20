package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.SystemConfig;

public class MinSymbolPolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_MIN_SYMBOL";
    String policyCount = "";

    @Override
    public boolean validate(String password) {
        policyCount = SystemConfig.instance().getAdminConfigService().getAllConfig().get(POLICY_NAME);
        String sybmols = password.replaceAll("[a-zA-Z0-9]", "");
        return Integer.parseInt(policyCount) <= sybmols.length();
    }

    @Override
    public String toString() {
        return "Password should contain at least " + policyCount + " symbols.";
    }
}
