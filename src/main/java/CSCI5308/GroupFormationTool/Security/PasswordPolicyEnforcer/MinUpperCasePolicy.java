package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

public class MinUpperCasePolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_MIN_UPPERCASE";
    private int minUpperCase;

    public MinUpperCasePolicy(String value) {
        minUpperCase = Integer.parseInt(value);
    }

    public int getMinUpperCase() {
        return minUpperCase;
    }

    public void setMinUpperCase(int minUpperCase) {
        this.minUpperCase = minUpperCase;
    }

    @Override
    public boolean validate(String password) {
        int upperCase = 0;
        for (int i = 0; i < password.length(); i++) {
            // Check for lowercase letters.
            if (Character.isUpperCase(password.charAt(i)))
                upperCase++;
        }
        return minUpperCase <= upperCase;
    }

    @Override
    public String toString() {
        return "Password should contain at least " + minUpperCase + " uppercase character.";
    }
}
