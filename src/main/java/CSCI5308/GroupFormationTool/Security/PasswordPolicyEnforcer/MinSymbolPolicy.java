package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

public class MinSymbolPolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_MIN_SYMBOL";
    private int minSymbol;

    public MinSymbolPolicy(String value) {
        minSymbol = Integer.parseInt(value);
    }

    public int getMinSymbol() {
        return minSymbol;
    }

    public void setMinSymbol(int minSymbol) {
        this.minSymbol = minSymbol;
    }

    @Override
    public boolean validate(String password) {
        String symbols = password.replaceAll("[a-zA-Z0-9]", "");
        return minSymbol <= symbols.length();
    }

    @Override
    public String toString() {
        return "Password should contain at least " + minSymbol + " symbols.";
    }
}
