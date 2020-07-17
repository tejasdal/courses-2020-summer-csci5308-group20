package CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer;

public class PasswordPolicyPersistenceConcreteFactory extends PasswordPolicyPersistenceAbstractFactory {
    @Override
    public IPasswordPolicyPersistence makePersistence() {
        return new PasswordPolicyPersistence();
    }
}
