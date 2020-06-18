package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.CustomExceptions.PasswordPolicyVoidException;

import java.util.List;

public interface IPasswordPolicyService {
    List<IPasswordPolicy> getListOfPasswordPolicies();

    boolean validateUsingPolicies(String password) throws PasswordPolicyVoidException;
}
