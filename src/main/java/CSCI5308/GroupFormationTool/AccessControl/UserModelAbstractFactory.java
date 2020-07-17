package CSCI5308.GroupFormationTool.AccessControl;

public abstract class UserModelAbstractFactory {

    private static UserModelAbstractFactory uniqueInstance = null;

    protected UserModelAbstractFactory(){}

    public static UserModelAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new UserModelConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract IUser makeUser();
}
