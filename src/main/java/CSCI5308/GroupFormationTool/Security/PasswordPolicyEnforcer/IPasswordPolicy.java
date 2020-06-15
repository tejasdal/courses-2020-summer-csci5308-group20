package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

public interface IPasswordPolicy {
    boolean validate(String string);
}
