package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

public class MinLowerCasePolicy implements IPasswordPolicy {
    public static final String POLICY_NAME = "PASSWORD_MIN_LOWERCASE";
    private int minLowerCase;

    public MinLowerCasePolicy(String value) {
        minLowerCase = Integer.parseInt(value);
    }

    public int getMinLowerCase() {
        return minLowerCase;
    }

    public void setMinLowerCase(int minLowerCase) {
        this.minLowerCase = minLowerCase;
    }

    @Override
    public boolean validate(String password) {
        int lowerCase = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                lowerCase++;
            }
        }
        return minLowerCase <= lowerCase;
    }

    @Override
    public String toString() {
        return "Password should contain " + minLowerCase + " lowercase character.";
    }
}
