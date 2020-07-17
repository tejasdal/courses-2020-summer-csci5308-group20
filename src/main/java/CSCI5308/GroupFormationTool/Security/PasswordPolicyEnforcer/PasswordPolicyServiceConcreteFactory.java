package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

import CSCI5308.GroupFormationTool.AdminConfig.AdminConfigServiceAbstractFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PasswordPolicyServiceConcreteFactory extends PasswordPolicyServiceAbstractFactory {
    @Override
    public IPasswordPolicyService makeService() {
        return new PasswordPolicyService();

    }

    public List<IPasswordPolicy> getPolicy() {
        Map<String, String> allConfig = AdminConfigServiceAbstractFactory.instance().makeAdminConfigService().getAllConfig();
        List<IPasswordPolicy> policies = new ArrayList<>();
        for (Map.Entry<String, String> entry : allConfig.entrySet()) {
            if (entry.getKey().startsWith("PASSWORD_")) {
                switch (entry.getKey()) {
                    case MinLengthPolicy.POLICY_NAME:
                        policies.add(new MinLengthPolicy(entry.getValue()));
                        break;
                    case MaxLengthPolicy.POLICY_NAME:
                        policies.add(new MaxLengthPolicy(entry.getValue()));
                        break;
                    case MinLowerCasePolicy.POLICY_NAME:
                        policies.add(new MinLowerCasePolicy(entry.getValue()));
                        break;
                    case MinSymbolPolicy.POLICY_NAME:
                        policies.add(new MinSymbolPolicy(entry.getValue()));
                        break;
                    case MinUpperCasePolicy.POLICY_NAME:
                        policies.add(new MinUpperCasePolicy(entry.getValue()));
                        break;
                    case RestrictedSymbolCasePolicy.POLICY_NAME:
                        policies.add(new RestrictedSymbolCasePolicy(entry.getValue()));
                        break;
                    case RememberedPasswordPolicy.POLICY_NAME:
                        policies.add(new RememberedPasswordPolicy(entry.getValue()));
                        break;
                    default:
                        return null;
                }
            }
        }
        if (policies.isEmpty()) {
            return null;
        }
        return policies;
    }
}
