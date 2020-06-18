package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

public class MinLengthPolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_MIN_LENGTH";
    private int minLength;

    public MinLengthPolicy(String value) {
        minLength = Integer.parseInt(value);
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean validate(String password) {
        return minLength <= password.length();
    }

    @Override
    public String toString() {
        return "Password should be greater than " + minLength + " character.";
    }
}
