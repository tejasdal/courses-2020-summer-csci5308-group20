package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

public class MaxLengthPolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_MAX_LENGTH";


    private int maxLength;

    public MaxLengthPolicy(String value) {
        maxLength = Integer.parseInt(value);
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean validate(String password) {
        return maxLength >= password.length();
    }

    @Override
    public String toString() {
        return "Password should be less than " + maxLength + " character.";
    }
}
