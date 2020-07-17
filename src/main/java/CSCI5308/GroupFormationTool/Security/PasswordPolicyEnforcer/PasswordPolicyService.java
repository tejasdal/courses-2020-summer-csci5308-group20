package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.CustomExceptions.PasswordPolicyVoidException;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyService implements IPasswordPolicyService {

    @Override
    public boolean validateUsingPolicies(String password) throws PasswordPolicyVoidException {
        List<IPasswordPolicy> enforcingPolicies = PasswordPolicyServiceAbstractFactory.instance().getPolicy();
        List<String> errors = new ArrayList<>();
        if (null != enforcingPolicies) {
            for (IPasswordPolicy policy : enforcingPolicies) {
                if (policy.validate(password) == false) {
                    errors.add(policy.toString());
                }
            }
            if (errors.isEmpty() == false) {
                throw new PasswordPolicyVoidException(errors.toString());
            }
        }
        return true;
    }
}

