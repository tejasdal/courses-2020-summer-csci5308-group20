package CSCI5308.GroupFormationTool.AccessControl;

public class UserPersistenceConcreteFactory extends UserPersistenceAbstractFactory {
    @Override
    public IUserPersistence makeUserPersistence() {
        return new UserDB();
    }
}
