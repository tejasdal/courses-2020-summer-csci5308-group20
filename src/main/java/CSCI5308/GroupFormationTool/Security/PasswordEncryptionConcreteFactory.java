package CSCI5308.GroupFormationTool.Security;

public class PasswordEncryptionConcreteFactory extends PasswordEncryptionAbstractFactory {
    @Override
    public IPasswordEncryption makePasswordEncryption() {
        return new BCryptPasswordEncryption();
    }
}
