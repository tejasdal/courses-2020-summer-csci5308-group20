package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.CustomExceptions.PasswordPolicyVoidException;

public interface IPasswordPolicyService {
    boolean validateUsingPolicies(String password) throws PasswordPolicyVoidException;
}
