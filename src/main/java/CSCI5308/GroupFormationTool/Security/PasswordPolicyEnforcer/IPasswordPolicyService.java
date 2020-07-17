package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;


public interface IPasswordPolicyService {
    boolean validateUsingPolicies(String password) throws Exception;
}
