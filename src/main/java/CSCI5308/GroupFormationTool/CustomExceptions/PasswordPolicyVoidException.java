package CSCI5308.GroupFormationTool.CustomExceptions;

@SuppressWarnings("serial")
public class PasswordPolicyVoidException extends Exception {
    public PasswordPolicyVoidException(String message) {
        super(message);
    }
}
