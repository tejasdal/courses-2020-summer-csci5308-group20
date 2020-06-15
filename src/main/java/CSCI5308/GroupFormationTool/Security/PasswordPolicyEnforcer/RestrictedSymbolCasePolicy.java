package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.SystemConfig;

public class RestrictedSymbolCasePolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_RESTRICTED_SYMBOL";
    String restrictedSymbols = "";

    @Override
    public boolean validate(String password) {
        restrictedSymbols = SystemConfig.instance().getAdminConfigService().getAllConfig().get(POLICY_NAME);
        for (char i : restrictedSymbols.toCharArray()) {
            if (password.contains(i + "")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Password should not contain following symbols " + restrictedSymbols;
    }
}
