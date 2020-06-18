package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

public class RestrictedSymbolCasePolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_RESTRICTED_SYMBOL";
    private String restrictedSymbols;

    public RestrictedSymbolCasePolicy(String value) {
        restrictedSymbols = value;
    }

    public String getRestrictedSymbols() {
        return restrictedSymbols;
    }

    public void setRestrictedSymbols(String restrictedSymbols) {
        this.restrictedSymbols = restrictedSymbols;
    }

    @Override
    public boolean validate(String password) {
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
