package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

<<<<<<< HEAD
import CSCI5308.GroupFormationTool.SystemConfig;
=======
import CSCI5308.GroupFormationTool.CustomExceptions.PasswordPolicyVoidException;
>>>>>>> 6e3df574c8ac3de1b2d1c56fd4a3c1e2510f3eb1

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyService implements IPasswordPolicyService {

    @Override
<<<<<<< HEAD
    public boolean validateUsingPolicies(String password) throws Exception {
        List<IPasswordPolicy> enforcingPolicies = SystemConfig.instance().getPolicy();
=======
    public boolean validateUsingPolicies(String password) throws PasswordPolicyVoidException {
        List<IPasswordPolicy> enforcingPolicies = PasswordPolicyServiceAbstractFactory.instance().getPolicy();
>>>>>>> 6e3df574c8ac3de1b2d1c56fd4a3c1e2510f3eb1
        List<String> errors = new ArrayList<>();
        if (null != enforcingPolicies) {
            for (IPasswordPolicy policy : enforcingPolicies) {
                if (policy.validate(password) == false) {
                    errors.add(policy.toString());
                }
            }
            if (errors.isEmpty() == false) {
                throw new Exception(errors.toString());
            }
        }
        return true;
    }
}

