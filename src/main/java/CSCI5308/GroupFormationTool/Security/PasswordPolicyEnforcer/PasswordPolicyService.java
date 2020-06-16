package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.CustomExceptions.PasswordPolicyVoidException;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PasswordPolicyService implements IPasswordPolicyService {

    private List<IPasswordPolicy> getListOfPasswordPolicies() {
        Map<String, String> allConfig = SystemConfig.instance().getAdminConfigService().getAllConfig();
        List<IPasswordPolicy> policies = new ArrayList<>();
        for (Map.Entry<String, String> entry : allConfig.entrySet()) {
            if (entry.getKey().startsWith("PASSWORD_")) {
                policies.add(SystemConfig.instance().getPolicy(entry.getKey(), entry.getValue()));
            }
        }
        if (policies.isEmpty()) {
            return null;
        }
        return policies;

    }

    @Override
    public boolean validateUsingPolicies(String password) throws PasswordPolicyVoidException {
        List<IPasswordPolicy> enforcingPolicies = getListOfPasswordPolicies();
        List<String> errors = new ArrayList<>();
        if (null != enforcingPolicies) {
            for (IPasswordPolicy policy : enforcingPolicies) {
                if (!policy.validate(password)) {
                    errors.add(policy.toString());
                }
            }
            if (!errors.isEmpty()) {
                throw new PasswordPolicyVoidException(errors.toString());
            }
        }
        return true;
    }
}

