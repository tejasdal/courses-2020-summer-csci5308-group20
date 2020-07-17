package CSCI5308.GroupFormationTool.AccessControl;

public class UserModelConcreteFactory extends UserModelAbstractFactory {
    @Override
    public IUser makeUser() {
        return new User();
    }
}
