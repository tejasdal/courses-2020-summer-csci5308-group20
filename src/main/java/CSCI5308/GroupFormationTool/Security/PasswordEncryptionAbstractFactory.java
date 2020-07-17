package CSCI5308.GroupFormationTool.Security;

public abstract class PasswordEncryptionAbstractFactory {

    private static PasswordEncryptionAbstractFactory uniqueInstance = null;

    protected PasswordEncryptionAbstractFactory(){}

    public static PasswordEncryptionAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new PasswordEncryptionConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract IPasswordEncryption makePasswordEncryption();
}
